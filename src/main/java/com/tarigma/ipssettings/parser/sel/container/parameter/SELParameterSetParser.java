package com.tarigma.ipssettings.parser.sel.container.parameter;

import com.tarigma.ipssettings.model.Block;
import com.tarigma.ipssettings.model.parameter.Parameter;
import com.tarigma.ipssettings.model.parameter.ParameterDataType;
import com.tarigma.ipssettings.model.parameter.ParameterSet;
import com.tarigma.ipssettings.parser.container.parameter.ParameterSetParser;

import java.util.List;
import java.util.Map;

public class SELParameterSetParser implements ParameterSetParser {

    @Override
    public ParameterSet parse(Map<String, List<String>> linesByBlockName, Map<String, Block> blocksByName) {
        // parameter parser is stateless and reusable
        var paramParser = new SELParameterParser();
        
        ParameterSet parameterSet = new ParameterSet();
        
        linesByBlockName.forEach((blockName, lines) -> {
            // check for matching block definition
            Block block = blocksByName.get(blockName);

            lines.forEach(line -> {
                Parameter<ParameterDataType> parameter = paramParser.parse(line);
                if (parameter != null) {
                    if (block != null) {
                        parameter.setBlockId(block.getBlockID());
                    }
                    
                    // add to set
                    parameterSet.addToParameterSet(parameter);
                }
            });
        });
        
        return parameterSet;
    }
}
