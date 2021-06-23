package com.tarigma.ipssettings.parser.ge;

import com.tarigma.ipssettings.model.Block;
import com.tarigma.ipssettings.model.RSEIContainer;
import com.tarigma.ipssettings.model.parameter.ParameterSet;
import com.tarigma.ipssettings.parser.RSEIParser;
import com.tarigma.ipssettings.parser.ge.container.GEBlocksParser;
import com.tarigma.ipssettings.parser.ge.container.GEHeaderParser;
import com.tarigma.ipssettings.parser.ge.container.parameter.GEParameterSetParser;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Parses SEL format input (comma separated) into the RSEI class structure
 */
public class GEParser implements RSEIParser {

    private static final int LINES_IN_HEADER = 8;

    @Override
    public RSEIContainer parse(List<String> strings) {

        // first 7 lines are header information
        List<String> headerData = strings.subList(0, LINES_IN_HEADER);
        // parse
        RSEIContainer rseiContainer = new GEHeaderParser().parse(headerData);

        // parsable data
        List<String> parsableData = strings.subList(LINES_IN_HEADER, strings.size());

        // parse blocks
        List<Block> blocks = new GEBlocksParser().parse(parsableData);
        rseiContainer.setBlocks(blocks);

        // map blocks by name for quick lookup
        Map<String, Block> blocksByPath = blocks.stream()
                .collect(Collectors.toMap(Block::getBlockPath, Function.identity()));

        // parse parameters
        // TODO - split leading block path from remainder of line, then group by path
        ParameterSet parameterSet = new GEParameterSetParser().parse(Collections.singletonMap("all-lines", parsableData), blocksByPath);
        rseiContainer.setParameterSet(parameterSet);

        return rseiContainer;
    }
}
