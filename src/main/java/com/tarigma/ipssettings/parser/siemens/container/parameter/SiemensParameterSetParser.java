package com.tarigma.ipssettings.parser.siemens.container.parameter;

import com.tarigma.ipssettings.model.Block;
import com.tarigma.ipssettings.model.parameter.Parameter;
import com.tarigma.ipssettings.model.parameter.ParameterDataType;
import com.tarigma.ipssettings.model.parameter.ParameterSet;
import com.tarigma.ipssettings.parser.container.parameter.ParameterSetParser;
import java.util.List;
import java.util.Map;

public class SiemensParameterSetParser implements ParameterSetParser {

  @Override
  public ParameterSet parse(Map<String, List<String>> parameterLinesByBlockPath,
                            Map<String, Block> blocksByPath) {
    // parameter parser is stateless and reusable
    var paramParser = new SiemensParameterParser();

    ParameterSet parameterSet = new ParameterSet();

    // iterate through each line
    for (var entry : parameterLinesByBlockPath.entrySet()) {
      // lookup possible block
      String blockPath = entry.getKey();
      Block block = blocksByPath.get(blockPath);

      for (String current : entry.getValue()) {
        Parameter<ParameterDataType> parameter = paramParser.parse(current);
        if (parameter != null) {
          if (block != null) {
            parameter.setBlockId(block.getBlockID());
          }
          // add to set
          parameterSet.addToParameterSet(parameter);
        }
      }
    }

    return parameterSet;
  }
}