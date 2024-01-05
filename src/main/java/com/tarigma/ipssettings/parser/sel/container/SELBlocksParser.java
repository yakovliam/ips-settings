package com.tarigma.ipssettings.parser.sel.container;

import com.tarigma.ipssettings.model.Block;
import com.tarigma.ipssettings.parser.container.BlocksParser;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class SELBlocksParser implements BlocksParser {

  @Override
  public Collection<Block> parse(List<String> lines) {
    return lines.stream().map(line -> {
      String[] parts = line.split(",");
      String blockName = parts[0].replace("\"", "");
      String blockDescription = parts[1].replace("\"", "");

      if (!blockDescription.isEmpty()) {
        return new Block(blockName, blockDescription, null);
      } else {
        return new Block(blockName, null, null);
      }
    }).collect(Collectors.toList());
  }

}
