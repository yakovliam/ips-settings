package com.tarigma.ipssettings.parser.sel.container.parameter;

import com.tarigma.ipssettings.model.parameter.Parameter;
import com.tarigma.ipssettings.model.parameter.ParameterDataType;
import com.tarigma.ipssettings.parser.container.parameter.ParameterParser;

import java.util.regex.Pattern;

public class SELParameterParser implements ParameterParser<Parameter<ParameterDataType>> {

    /**
     * Units pattern
     * <p>
     * Example {@code (1-8)} or {@code (SEL,LMD,DNP,DNPE..)}
     */
    private static final Pattern UNITS_PATTERN = Pattern.compile("\\(.*\\)");

    @Override
    public Parameter<ParameterDataType> parse(String s) {
        // split into comma separated values
        String[] data = s.split(",");

        String name;
        String valueAsString;
        String descriptionAndUnits;
        try {
            name = data[0];
            valueAsString = data[1].replace("\"", "");
            descriptionAndUnits = data[5];
        } catch (IndexOutOfBoundsException ignored) {
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
        ParameterDataType parameterDataType = null;

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
