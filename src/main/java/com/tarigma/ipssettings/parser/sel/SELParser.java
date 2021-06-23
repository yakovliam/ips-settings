package com.tarigma.ipssettings.parser.sel;

import com.tarigma.ipssettings.model.Block;
import com.tarigma.ipssettings.model.RSEIContainer;
import com.tarigma.ipssettings.model.parameter.ParameterSet;
import com.tarigma.ipssettings.parser.RSEIParser;
import com.tarigma.ipssettings.parser.sel.container.SELBlocksParser;
import com.tarigma.ipssettings.parser.sel.container.SELHeaderParser;
import com.tarigma.ipssettings.parser.sel.container.parameter.SELParameterSetParser;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Parses SEL format input (colon separated) into the RSEI class structure
 */
public class SELParser implements RSEIParser {

    /**
     * INFO block header
     */
    private static final String INFO_BLOCK_HEADER = "INFO";

    /**
     * CLASSES block header
     */
    private static final String CLASSES_BLOCK_HEADER = "CLASSES";

    /**
     * Arbitrary block header
     */
    private static final Pattern BLOCK_HEADER = Pattern.compile("\\[(.*)\\]");

    @Override
    public RSEIContainer parse(List<String> strings) {
        // blocks will maintain insertion order
        Map<String, List<String>> linesByBlockName = new LinkedHashMap<>();
        
        String currentBlockName = null;
        for (String currentLine : strings) {
            Matcher m = BLOCK_HEADER.matcher(currentLine);
            if (m.matches()) {
                // line is a block header
                currentBlockName = m.group(1);
                linesByBlockName.put(currentBlockName, new ArrayList<>());
            } else {
                linesByBlockName.get(currentBlockName).add(currentLine);
            }
        }
        
        // parse info
        RSEIContainer rseiContainer = new SELHeaderParser().parse(linesByBlockName.get(INFO_BLOCK_HEADER));

        // parse blocks
        List<Block> blocks = new SELBlocksParser().parse(linesByBlockName.get(CLASSES_BLOCK_HEADER));
        rseiContainer.setBlocks(blocks);

        // get iterator that will begin immediately after "CLASSES" block 
        var iter = linesByBlockName.entrySet().iterator();
        while (iter.hasNext() && !CLASSES_BLOCK_HEADER.equals(iter.next().getKey()));
        
        // construct new map of all lines after "CLASSES" block;
        // this is not space efficient
        Map<String, List<String>> remainingLinesByBlockName = new LinkedHashMap<>();
        iter.forEachRemaining(entry -> remainingLinesByBlockName.put(entry.getKey(), entry.getValue()));
        
        // map blocks by name for quick lookup
        Map<String, Block> blocksByName = blocks.stream()
                .collect(Collectors.toMap(Block::getName, Function.identity()));

        // parse parameter set
        ParameterSet parameterSet = new SELParameterSetParser().parse(remainingLinesByBlockName, blocksByName);
        rseiContainer.setParameterSet(parameterSet);

        return rseiContainer;
    }
}
