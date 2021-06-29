package com.tarigma.ipssettings.parser.ge;

import com.tarigma.ipssettings.model.Block;
import com.tarigma.ipssettings.model.RSEIContainer;
import com.tarigma.ipssettings.model.parameter.ParameterSet;
import com.tarigma.ipssettings.parser.RSEIParser;
import com.tarigma.ipssettings.parser.ge.container.GEBlocksParser;
import com.tarigma.ipssettings.parser.ge.container.GEHeaderParser;
import com.tarigma.ipssettings.parser.ge.container.parameter.GEParameterSetParser;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * Parses SEL format input (comma separated) into the RSEI class structure
 */
public class GEParser implements RSEIParser {
    
    private static final Logger LOG = LoggerFactory.getLogger(GEParser.class);

    private static final int LINES_IN_HEADER = 8;
    
    private static final Pattern BLOCK_PREFIX_PATTERN = Pattern.compile("(.*?)([^:]*:[^:]*)");

    @Override
    public RSEIContainer parse(List<String> strings) {

        // first 7 lines are header information
        List<String> headerData = strings.subList(0, LINES_IN_HEADER);
        // parse
        RSEIContainer rseiContainer = new GEHeaderParser().parse(headerData);

        // parsable data
        List<String> parsableData = strings.subList(LINES_IN_HEADER, strings.size());

        // parse blocks
        Collection<Block> blocks = new GEBlocksParser().parse(parsableData);
        rseiContainer.setBlocks(blocks);

        // map blocks by name for quick lookup
        Map<String, Block> blocksByPath = blocks.stream()
                .collect(Collectors.toMap(Block::getBlockPath, Function.identity()));

        Map<String, List<String>> parameterLinesByBlockPath = splitBlocksFromParameters(parsableData);

        // parse parameters
        ParameterSet parameterSet = new GEParameterSetParser().parse(parameterLinesByBlockPath, blocksByPath);
        rseiContainer.setParameterSet(parameterSet);

        return rseiContainer;
    }

    private Map<String, List<String>> splitBlocksFromParameters(List<String> parsableData) {
        MultiValueMap<String, String> result = new LinkedMultiValueMap<>();
        
        parsableData.forEach(line -> {
            Matcher m = BLOCK_PREFIX_PATTERN.matcher(line);
            if (m.matches()) {
                if (m.group(1).length() > 0) {
                    String blockPath = m.group(1).replaceAll("\\s*:\\s*", "/").strip();
                    result.add(blockPath, m.group(2).strip());
                } else {
                    result.add("", line.strip());
                }
            } else {
                LOG.warn("could not parse parameter from line: '{}'", line);
            }
        });
        
        return result;
    }
}
