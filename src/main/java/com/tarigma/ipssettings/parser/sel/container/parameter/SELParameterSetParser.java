package com.tarigma.ipssettings.parser.sel.container.parameter;

import com.tarigma.ipssettings.model.parameter.Parameter;
import com.tarigma.ipssettings.model.parameter.ParameterDataType;
import com.tarigma.ipssettings.model.parameter.ParameterSet;
import com.tarigma.ipssettings.parser.container.parameter.ParameterSetParser;

import java.util.List;
import java.util.regex.Pattern;

public class SELParameterSetParser implements ParameterSetParser {

    /**
     * Ignore block pattern
     * <p>
     * Example: {@code [CLASSES]}
     */
    private static final Pattern IGNORE_BLOCK_PATTERN = Pattern.compile("\\[.*\\]");

    @Override
    public ParameterSet parse(List<String> strings) {
        ParameterSet parameterSet = new ParameterSet();

        // iterate through each line
        for (String current : strings) {
            // if the line matches the ignore block pattern, then we can't parse it...
            // so continue (next item, please)!
            if (current.matches(IGNORE_BLOCK_PATTERN.pattern())) {
                continue;
            }

            Parameter<ParameterDataType> parameter = new SELParameterParser().parse(current);
            if(parameter != null) {
                // add to set
                parameterSet.addToParameterSet(parameter);
            }
        }

        return parameterSet;
    }
}
