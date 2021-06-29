package com.tarigma.ipssettings.parser.ge.container;

import com.tarigma.ipssettings.model.Block;
import com.tarigma.ipssettings.parser.container.BlocksParser;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GEBlocksParser implements BlocksParser {

    private static final Logger LOG = LoggerFactory.getLogger(GEBlocksParser.class);

    private static final Pattern BLOCK_PREFIX_PATTERN = Pattern.compile("(.*?)([^:]*:[^:]*)");

    @Override
    public Collection<Block> parse(List<String> lines) {

        List<String> allBlockPaths = lines.stream()
            .map(BLOCK_PREFIX_PATTERN::matcher)
            .filter(Matcher::matches)
            .filter(m -> m.group(1).length() > 0)
            .map(m -> m.group(1).replaceAll("\\s*:\\s*", "/").strip())
            .distinct()
            .collect(Collectors.toList());
        
        Map<String, Block> blocksByPath = new HashMap<>();
        
        allBlockPaths.forEach(fullBlockPath -> {
            
            // create block's parents, then create block
            String[] parts = fullBlockPath.split("/");
            for (int i = 0; i < parts.length; i++) {
                String thisBlockName = parts[i];
                String[] parentBlockNames = Arrays.copyOfRange(parts, 0, i);
                String parentBlockPath = parentBlockNames.length == 0 ? "" : String.join("/", parentBlockNames) + "/";
                String thisBlockPath = parentBlockPath + thisBlockName + "/";
                if (blocksByPath.containsKey(thisBlockPath)) {
                    // block already exists, don't recreate
                    continue;
                }
                
                // attach parent; may be null
                Block parentBlock = blocksByPath.get(parentBlockPath);
                Block block = new Block(thisBlockName, thisBlockName, parentBlock);
                blocksByPath.put(thisBlockPath, block);
                if (LOG.isDebugEnabled()) {
                    LOG.debug("parsed block: {}", thisBlockPath);
                }
            }
        });
        
        return blocksByPath.values();
    }

}
