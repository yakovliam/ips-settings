package com.tarigma.ipssettings.parser.container.parameter;

import com.tarigma.ipssettings.model.Block;
import com.tarigma.ipssettings.model.parameter.ParameterSet;

import java.util.List;
import java.util.Map;

public interface ParameterSetParser {
    ParameterSet parse(Map<String, List<String>> parameterLinesByBlockPath, Map<String, Block> blocksByPath);
}
