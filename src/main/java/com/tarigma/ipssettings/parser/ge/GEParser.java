package com.tarigma.ipssettings.parser.ge;

import com.tarigma.ipssettings.model.RSEIContainer;
import com.tarigma.ipssettings.model.parameter.ParameterSet;
import com.tarigma.ipssettings.parser.RSEIParser;
import com.tarigma.ipssettings.parser.ge.container.GEHeaderParser;
import com.tarigma.ipssettings.parser.ge.container.parameter.GEParameterSetParser;

import java.util.Collections;
import java.util.List;

/**
 * Parses SEL format input (comma separated) into the RSEI class structure
 */
public class GEParser implements RSEIParser {

    @Override
    public RSEIContainer parse(List<String> strings) {

        // first 7 lines are header information
        List<String> headerData = strings.subList(0, 7);
        // parse
        RSEIContainer rseiContainer = new GEHeaderParser().parse(headerData);

        // parsable data
        List<String> parsableData = strings.subList(7, strings.size());

        // parse set
        ParameterSet parameterSet = new GEParameterSetParser().parse(Collections.singletonMap("all-lines", parsableData), Collections.emptyMap());

        // set container
        rseiContainer.setParameterSet(parameterSet);

        return rseiContainer;
    }
}
