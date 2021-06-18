package com.tarigma.ipssettings.parser.container.parameter;

import com.tarigma.ipssettings.model.parameter.Parameter;
import com.tarigma.ipssettings.model.parameter.ParameterDataType;
import com.tarigma.ipssettings.parser.Parser;

public interface ParameterParser<T extends Parameter<ParameterDataType>> extends Parser<String, Parameter<ParameterDataType>> {
}
