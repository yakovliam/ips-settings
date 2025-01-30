package com.tarigma.ipssettings.parser.abb.container.parameter;

import com.tarigma.ipssettings.model.Block;
import com.tarigma.ipssettings.model.parameter.Parameter;
import com.tarigma.ipssettings.model.parameter.ParameterDataType;
import com.tarigma.ipssettings.model.parameter.ParameterSet;
import com.tarigma.ipssettings.parser.container.parameter.ParameterSetParser;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ABBParameterSetParser implements ParameterSetParser {

  private static final Logger LOG = LoggerFactory.getLogger(ABBParameterSetParser.class);

  private static final Pattern FULL_PARAMETER_PATTERN =
      Pattern.compile("(.*) = (.* )(?:(flag:\\dx\\d+)|)");

  private static final Pattern OPTIONAL_SETTINGS_GROUPS_PARAMETER_PATTERN =
      Pattern.compile("(\\[\\d\\]):(([^ ]+))");

  @Override
  public ParameterSet parse(Map<String, List<String>> parameterLinesByBlockPath,
                            Map<String, Block> blocksByPath) {
    ParameterSet parameterSet = new ParameterSet();

    // iterate through each line
    for (Map.Entry<String, List<String>> entry : parameterLinesByBlockPath.entrySet()) {
      // lookup possible block
      String blockPath = entry.getKey();
      Block block = blocksByPath.get(blockPath);

      for (String current : entry.getValue()) {
        List<Parameter<ParameterDataType>> parameters =
            parseLineIntoParameterOrPossibleSettingsGroupParameters(current);

        for (Parameter<ParameterDataType> parameter : parameters) {
          if (parameter != null) {
            if (block != null) {
              parameter.setBlockId(block.getBlockID());
            }
            // add to set
            parameterSet.addToParameterSet(parameter);
          }
        }
      }
    }

    return parameterSet;
  }

  private List<Parameter<ParameterDataType>> parseLineIntoParameterOrPossibleSettingsGroupParameters(
      String line) {
    // first, parse the entire line and get the key/value pair
    // the value might be a list of settings groups, so we need to test for that
    String key;
    String value;
    String flag;

    Matcher matcher = FULL_PARAMETER_PATTERN.matcher(line);

    // use FULL_PARAMETER_PATTERN to match the key, value, and flag
    if (matcher.find()) {
      key = matcher.group(1);
      value = matcher.group(2);
      flag = matcher.group(3);
    } else {
      // uh oh, this line doesn't match the expected pattern
      LOG.error("Parameter doesn't match expected pattern: {}", line);
      return null;
    }

    // test if the value adheres to the OPTIONAL_SETTINGS_GROUPS_PARAMETER_PATTERN
    Matcher optionalSettingsGroupsMatcher =
        OPTIONAL_SETTINGS_GROUPS_PARAMETER_PATTERN.matcher(value);

    // if it matches, then we need to return MULTIPLE parameters because the value is a list of
    // settings groups
    List<Parameter<ParameterDataType>> parameters = new ArrayList<>();

    ABBParameterParser parameterParser = new ABBParameterParser();

    if (optionalSettingsGroupsMatcher.results().findAny().isPresent()) {

      while (optionalSettingsGroupsMatcher.find()) {
        for (int i = 0; i < optionalSettingsGroupsMatcher.groupCount(); i += 3) {
          String num = optionalSettingsGroupsMatcher.group(i + 1);
          String val = optionalSettingsGroupsMatcher.group(i + 2);
          Parameter<ParameterDataType> parameter = parameterParser.parse(val);
          parameter.setName(key + num);

          parameters.add(parameter);
        }
      }


    } else {
      // the value is not a list of settings groups, so we can just create a single parameter
      Parameter<ParameterDataType> parameter = parameterParser.parse(matcher.group(2));
      parameter.setName(key);

      parameters.add(parameter);
    }

    return parameters;
  }
}