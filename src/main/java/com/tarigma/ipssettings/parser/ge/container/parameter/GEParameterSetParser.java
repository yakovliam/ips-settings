package com.tarigma.ipssettings.parser.ge.container.parameter;

import com.tarigma.ipssettings.model.parameter.Parameter;
import com.tarigma.ipssettings.model.parameter.ParameterDataType;
import com.tarigma.ipssettings.model.parameter.ParameterSet;
import com.tarigma.ipssettings.parser.container.parameter.ParameterSetParser;

import java.util.List;
import java.util.regex.Pattern;

public class GEParameterSetParser implements ParameterSetParser {

    @Override
    public ParameterSet parse(List<String> strings) {
        ParameterSet parameterSet = new ParameterSet();

        // iterate through each line
        for (String current : strings) {
            Parameter<ParameterDataType> parameter = new GEParameterParser().parse(current);
            if(parameter != null) {
                // add to set
                parameterSet.addToParameterSet(parameter);
            }
        }

        return parameterSet;
    }
}
