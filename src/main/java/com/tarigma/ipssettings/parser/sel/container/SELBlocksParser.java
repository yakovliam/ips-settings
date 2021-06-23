package com.tarigma.ipssettings.parser.sel.container;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.tarigma.ipssettings.model.Block;
import com.tarigma.ipssettings.parser.container.BlocksParser;

public class SELBlocksParser implements BlocksParser {

    @Override
    public List<Block> parse(List<String> lines) {
        
        return lines.stream()
                .map(line -> {
                    String[] parts = line.split(",");
                    String blockName = parts[0].replace("\"", "");
                    String blockDescription = parts[1].replace("\"", "");
                    
                    Block block = new Block();
                    block.setName(blockName);
                    block.setDescription(blockDescription);
                    // generate ID based on hash of name
                    block.setBlockID(UUID.nameUUIDFromBytes(blockName.getBytes()));
                    return block;
                })
                .collect(Collectors.toList());
    }

}
