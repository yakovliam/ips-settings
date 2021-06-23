package com.tarigma.ipssettings.parser.ge.container.parameter;

import com.tarigma.ipssettings.model.Block;
import com.tarigma.ipssettings.model.parameter.Parameter;
import com.tarigma.ipssettings.model.parameter.ParameterDataType;
import com.tarigma.ipssettings.model.parameter.ParameterSet;
import com.tarigma.ipssettings.parser.container.parameter.ParameterSetParser;

import java.util.List;
import java.util.Map;

public class GEParameterSetParser implements ParameterSetParser {

    @Override
    public ParameterSet parse(Map<String, List<String>> parameterLinesByBlockName, Map<String, Block> blocksByName) {
        // parameter parser is stateless and reusable
        var paramParser = new GEParameterParser();

        ParameterSet parameterSet = new ParameterSet();

        // iterate through each line
        for (var entry : parameterLinesByBlockName.entrySet()) {
            for (String current : entry.getValue()) {
                Parameter<ParameterDataType> parameter = paramParser.parse(current);
                if(parameter != null) {
                    // add to set
                    parameterSet.addToParameterSet(parameter);
                }
            }
        }

        return parameterSet;
    }
}
