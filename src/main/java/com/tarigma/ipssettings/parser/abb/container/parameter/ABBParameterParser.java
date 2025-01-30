package com.tarigma.ipssettings.parser.abb.container.parameter;


import com.tarigma.ipssettings.model.parameter.Parameter;
import com.tarigma.ipssettings.model.parameter.ParameterDataType;
import com.tarigma.ipssettings.parser.container.parameter.ParameterParser;

public class ABBParameterParser implements ParameterParser<Parameter<ParameterDataType>> {

  @Override
  public Parameter<ParameterDataType> parse(String value) {
    // determine data type of the value
    ParameterDataType determinedParameterDataType =
        ParameterDataType.determineDataTypeByValue(value);
    // create a new parameter object that has the data type we determined
    Parameter<ParameterDataType> parameter = Parameter.with(determinedParameterDataType);

    // if available, set value
    if (value != null && !value.isEmpty()) {
      parameter.setValue(value.strip());
    }

    return parameter;
  }
}
