package com.tarigma.ipssettings.parser.ge.container.parameter;

import com.tarigma.ipssettings.model.parameter.Parameter;
import com.tarigma.ipssettings.model.parameter.ParameterDataType;
import com.tarigma.ipssettings.model.parameter.Unit;
import com.tarigma.ipssettings.model.parameter.localization.Localization;
import com.tarigma.ipssettings.parser.container.parameter.ParameterParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GEParameterParser implements ParameterParser<Parameter<ParameterDataType>> {

    /**
     * A pattern to match the key colon split pattern
     */
    private static final Pattern KEY_VALUE_COLON_SPLIT_PATTERN = Pattern.compile(":([+-]?(?=\\.\\d|\\d)(?:\\d+)?(?:\\.?\\d*))(?:[eE]([+-]?\\d+))?\\s+:1$");

    /**
     * Parses an input parameter string into a parameter
     * <p>
     * The input string usually looks like {@code <Key>: <Value> <Unit>}
     *
     * @param s input string
     * @return parameter
     */
    @Override
    public Parameter<ParameterDataType> parse(String s) {
        // compute index of colon separating the key and value

        int KVColonIndex = determineIndexOfColonBetweenKeyAndValue(s);
        boolean matchesRatioType = matchesRatioType(s);

        // if there is no detected colon, set the last index to the
        // end of the string, so we can just handle the entire thing
        if (KVColonIndex == -1) {
            KVColonIndex = s.length();
        }

        // first part (<Key>)
        String key = s.substring(0, KVColonIndex);

        // second part (<Value> <Unit>)
        String valueWithUnits;

        try {
            // try to get the second part
            valueWithUnits = s.substring(KVColonIndex + 1).trim();
        } catch (IndexOutOfBoundsException e) {
            // unable to get the second part, so that means that there's no value
            valueWithUnits = "";
        }

        // remove units from the string that contains the value and units
        String value;
        String units;

        // remove units from the string that contains the value and units by checking if it contains a space.
        // If so, that means the units are separated by a space
        String[] parts = valueWithUnits.split(" ");

        if (valueWithUnits.contains(" ") && !matchesRatioType && parts.length == 2 && parts[1].length() <= 7) {
            value = parts[0];
            units = parts[1];
        } else {
            // no space (no units), so that means we can just set the value with no units
            value = valueWithUnits;
            units = null;
        }

        // determine data type of the value
        ParameterDataType determinedParameterDataType = ParameterDataType.determineDataTypeByValue(value);
        // create a new parameter object that has the data type we determined
        Parameter<ParameterDataType> parameter = Parameter.with(determinedParameterDataType);

        // if available, set value
        if (value != null && !value.isEmpty()) {
            parameter.setValue(value);
        }

        if (units != null && !units.isEmpty()) {
            parameter.setUnit(new Unit(units));
        }

        // // create localization
        // Localization localization = new Localization()
        //         .setEnuLang3Description(key)
        //         .setEnuLang3Name(key);

        return parameter.setDescription(key) // set the description to the key
//                .setLocalization(localization)
                .setName(key); // set the name to tge key
    }

    /**
     * Determines the index of a colon between the key and value of a line
     *
     * @param line line
     * @return index of colon
     */
    private int determineIndexOfColonBetweenKeyAndValue(String line) {
        if (matchesRatioType(line)) {
            return nthLastIndexOf(2, ":", line);
        } else {
            return line.lastIndexOf(":");
        }
    }

    /**
     * If the given line matches the key value ratio type
     *
     * @param line line
     * @return if matches
     */
    private boolean matchesRatioType(String line) {
        return KEY_VALUE_COLON_SPLIT_PATTERN.matcher(line).find();
    }

    private int nthLastIndexOf(int nth, String ch, String string) {
        if (nth <= 0) return string.length();
        return nthLastIndexOf(--nth, ch, string.substring(0, string.lastIndexOf(ch)));
    }
}
