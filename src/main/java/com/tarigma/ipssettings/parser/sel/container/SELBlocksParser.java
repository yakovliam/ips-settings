package com.tarigma.ipssettings.parser.sel.container;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.tarigma.ipssettings.model.Block;
import com.tarigma.ipssettings.parser.container.BlocksParser;

public class SELBlocksParser implements BlocksParser {

    @Override
    public Collection<Block> parse(List<String> lines) {

        return lines.stream()
                .map(line -> {
                    String[] parts = line.split(",");
                    String blockName = parts[0].replace("\"", "");
                    String blockDescription = parts[1].replace("\"", "");

                    Block block = new Block(blockName, null);
                    if (!blockDescription.isEmpty()) {
                        block.setDescription(blockDescription);
                    }
                    return block;
                })
                .collect(Collectors.toList());
    }

}
