package com.tarigma.ipssettings.parser.ge.container.parameter;

import com.tarigma.ipssettings.model.parameter.Parameter;
import com.tarigma.ipssettings.model.parameter.ParameterDataType;
import com.tarigma.ipssettings.model.parameter.localization.Localization;
import com.tarigma.ipssettings.parser.container.parameter.ParameterParser;

public class GEParameterParser implements ParameterParser<Parameter<ParameterDataType>> {

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
        int lastIndexOfColon = s.lastIndexOf(":");

        // if there is no detected colon, set the last index to the
        // end of the string, so we can just handle the entire thing
        if (lastIndexOfColon == -1) {
            lastIndexOfColon = s.length();
        }

        // first part (<Key>)
        String key = s.substring(0, lastIndexOfColon);

        // second part (<Value> <Unit>)
        String valueWithUnits;

        try {
            // try to get the second part
            valueWithUnits = s.substring(lastIndexOfColon + 1).trim();
        } catch (IndexOutOfBoundsException e) {
            // unable to get the second part, so that means that there's no value
            valueWithUnits = "";
        }

        // remove units from the string that contains the value and units
        String value;

        // remove units from the string that contains the value and units by checking if it contains a space.
        // If so, that means the units are separated by a space
        if (valueWithUnits.contains(" ")) {
            value = valueWithUnits.split(" ")[0];
        } else {
            // no space (no units), so that means we can just set the value with no units
            value = valueWithUnits;
        }

        // determine data type of the value
        ParameterDataType determinedParameterDataType = ParameterDataType.determineDataTypeByValue(value);
        // create a new parameter object that has the data type we determined
        Parameter<ParameterDataType> parameter = Parameter.with(determinedParameterDataType);

        // if available, set value
        if (value != null && !value.isEmpty()) {
            parameter.setValue(value);
        }

        // // create localization
        // Localization localization = new Localization()
        //         .setEnuLang3Description(key)
        //         .setEnuLang3Name(key);

        return parameter.setDescription(key) // set the description to the key
//                .setLocalization(localization)
                .setName(key); // set the name to tge key
    }
}
