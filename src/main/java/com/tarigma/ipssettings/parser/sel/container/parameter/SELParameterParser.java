package com.tarigma.ipssettings.parser.sel.container.parameter;

import com.tarigma.ipssettings.model.parameter.Parameter;
import com.tarigma.ipssettings.model.parameter.ParameterDataType;
import com.tarigma.ipssettings.model.parameter.Unit;
import com.tarigma.ipssettings.model.parameter.range.Range;
import com.tarigma.ipssettings.parser.container.parameter.ParameterParser;
import com.tarigma.ipssettings.parser.sel.container.parameter.range.SELRangeParser;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SELParameterParser implements ParameterParser<Parameter<ParameterDataType>> {

    /**
     * Units pattern
     * <p>
     * Example {@code (1-8)} or {@code (SEL,LMD,DNP,DNPE..)}
     */
    private static final Pattern UNITS_PATTERN = Pattern.compile("\\(.*\\)");

    /**
     * Extracts the name before the comma
     * <p>
     * Example: SER1,"IN101,50P1,51P1,51N1,51P2,59S1,TRIP,TRGTR,LOP","","","","24 elements max.(enter NA to null)",""
     * turns into
     * SER1
     */
    private static final Pattern NAME_PATTERN = Pattern.compile("^[^,]+");

    /**
     * Extracts the split group of the data (csv-like format)
     * <p>
     * Define a field as either double-quote-wrapped, or not containing a comma. Include leading comma.
     */
    private static final Pattern SPLIT_GROUP_PATTERN = Pattern.compile(",(\"[^\"]*?\"|[^,]*)");

    @Override
    public Parameter<ParameterDataType> parse(String s) {
        // split into comma separated values
        Matcher nameMatcher = NAME_PATTERN.matcher(s);
        if (!nameMatcher.find()) {
            return null;
        }

        // name
        String name = nameMatcher.group().replace("\"", "");

        String rest = s.substring(nameMatcher.end());

        // define
        String valueAsString = null;
        String descriptionAndUnits = null;
        String rangeAsString = null;
        String dataTypeAsString = null;
        String unitsAsString = null;

        // matcher
        Matcher matcher = SPLIT_GROUP_PATTERN.matcher(rest);

        int index = 0;
        while (matcher.find()) {
            String c = matcher.group(1) // omit leading comma
                    .replace("\"", ""); // replace any quotes

            switch (index) {
                case 0:
                    valueAsString = c;
                    break;
                case 1:
                    rangeAsString = c;
                    break;
                case 2:
                    dataTypeAsString = c;
                    break;
                case 3:
                    unitsAsString = c;
                    break;
                case 4:
                    descriptionAndUnits = c;
                    break;
            }

            index++;
        }

        if (valueAsString == null || descriptionAndUnits == null) {
            return null;
        }

        // detect value type
        ParameterDataType parameterDataType;
        if (dataTypeAsString != null && !dataTypeAsString.isEmpty()) {
            parameterDataType = determineValueDataTypeByGiven(dataTypeAsString);

            if (parameterDataType == null) {
                // fall back to string, since we can't figure out what it is and if it was given
                // and it's not defined as an F for float, then it must be some type of enum,
                // and we want to parse that as a string
                parameterDataType = ParameterDataType.STRING;
            }
        } else {
            parameterDataType = determineValueDataType(valueAsString);
        }

        // initialize parameter
        Parameter<ParameterDataType> parameter = Parameter.with(parameterDataType);

        // create range
        Range range = (rangeAsString != null && !rangeAsString.isEmpty() && rangeAsString.contains("~"))
                ? new SELRangeParser().parse(rangeAsString) : null;

        // create unit
        Unit unit = unitsAsString != null && !unitsAsString.isEmpty() ? new Unit(unitsAsString) : null;

        // if not null, set it
        if (unit != null) {
            parameter.setUnit(unit);
        }

        if (!descriptionAndUnits.isEmpty()) {
            parameter.setDescription(descriptionAndUnits);
        }

        // Localization localization = new Localization()
        //         .setEnuLang3Description(name)
        //         .setEnuLang3Name(descriptionAndUnits);

        parameter.setDataType(parameterDataType)
                .setName(name)
//                .setLocalization(localization)
                .setValue(valueAsString)
                .setRange(range);

        return parameter;
    }

    /**
     * Determines the parameter data type of the given value
     *
     * @param value value
     * @return data type
     */
    private ParameterDataType determineValueDataType(String value) {
        try {
            Double.parseDouble(value);
            return ParameterDataType.DOUBLE;
        } catch (NumberFormatException ignored) {
        }

        try {
            Integer.parseInt(value);
            return ParameterDataType.DOUBLE;
        } catch (NumberFormatException ignored) {
        }

        return ParameterDataType.STRING;
    }

    /**
     * Determines the parameter data type of the given value based on the given data type string
     * <p>
     * For example, given "F" we return the type of 'Double' (because F means float, and double is the currently
     * supported RSEI format with a similar type)
     *
     * @param givenDatatypeString givenDatatypeString
     * @return data type
     */
    private ParameterDataType determineValueDataTypeByGiven(String givenDatatypeString) {
        return Arrays.stream(ParameterDataType.values())
                .filter(dataType -> Arrays.stream(dataType.getHandle())
                        .anyMatch(d -> d.equalsIgnoreCase(givenDatatypeString)))
                .collect(Collectors.toList())
                .stream()
                .findFirst()
                .orElse(null);
    }
}
