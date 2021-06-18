package com.tarigma.ipssettings.parser.sel.container.parameter;

import com.tarigma.ipssettings.model.parameter.Parameter;
import com.tarigma.ipssettings.model.parameter.ParameterDataType;
import com.tarigma.ipssettings.parser.container.parameter.ParameterParser;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
     * SER1,
     */
    private static final Pattern NAME_COMMA_PATTERN = Pattern.compile("^(.+?),");

    /**
     * Extracts the split group of the data (csv-like format)
     */
    private static final Pattern SPLIT_GROUP_PATTERN = Pattern.compile("([\"])(?:(?=(\\\\?))\\2.)*?\\1");

    @Override
    public Parameter<ParameterDataType> parse(String s) {
        // split into comma separated values
        Matcher nameMatcher = NAME_COMMA_PATTERN.matcher(s);
        if (!nameMatcher.find()) {
            return null;
        }

        // name
        String name = nameMatcher.group().replace(",", "");

        String rest = s.replaceAll(NAME_COMMA_PATTERN.pattern(), "");

        // define
        String valueAsString = null;
        String descriptionAndUnits = null;

        // matcher
        Matcher matcher = SPLIT_GROUP_PATTERN.matcher(rest);

        int index = 0;
        while (matcher.find()) {
            String c = matcher.group();

            if (index == 0) {
                valueAsString = c.replace("\"", "");
            } else if (index == 4) {
                descriptionAndUnits = c.replace("\"", "");
            }// fall through

            index++;
        }

        if (valueAsString == null || descriptionAndUnits == null) {
            return null;
        }

        // detect value type
        ParameterDataType parameterDataType = determineValueDataType(valueAsString);

        Parameter<ParameterDataType> parameter = Parameter.with(parameterDataType);

        return parameter.setDataType(parameterDataType)
                .setDescription(descriptionAndUnits)
                .setName(name)
                .setValue(valueAsString);
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
}
