package com.tarigma.ipssettings.parser.ge.container.parameter;

import com.tarigma.ipssettings.model.parameter.Parameter;
import com.tarigma.ipssettings.model.parameter.ParameterDataType;
import com.tarigma.ipssettings.parser.container.parameter.ParameterParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GEParameterParser implements ParameterParser<Parameter<ParameterDataType>> {

    @Override
    public Parameter<ParameterDataType> parse(String s) {
        // get data
        int lastIndexOf = s.lastIndexOf(":") == -1 ?
                s.length() : s.lastIndexOf(":");
        String key = s.substring(0, lastIndexOf);
        String valueAsStringWithUnits;

        try {
            valueAsStringWithUnits = s.substring(lastIndexOf + 1).trim();
        } catch (IndexOutOfBoundsException e) {
            valueAsStringWithUnits = "";
        }

        // strip units
        String valueAsString = valueAsStringWithUnits.contains(" ") ?
                valueAsStringWithUnits.split(" ")[0] : valueAsStringWithUnits;

        // detect value type
        ParameterDataType parameterDataType = determineValueDataType(valueAsString);

        Parameter<ParameterDataType> parameter = Parameter.with(parameterDataType);

        return parameter.setDataType(parameterDataType)
                .setDescription(key)
                .setName(key)
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
