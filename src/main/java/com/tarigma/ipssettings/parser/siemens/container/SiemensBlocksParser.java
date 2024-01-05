package com.tarigma.ipssettings.parser.siemens.container;

import com.tarigma.ipssettings.model.Block;
import com.tarigma.ipssettings.parser.container.BlocksParser;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SiemensBlocksParser implements BlocksParser {

  private static final Logger LOG = LoggerFactory.getLogger(SiemensBlocksParser.class);

  private final List<String> blockNames;

  public SiemensBlocksParser(List<String> blockNames) {
    this.blockNames = blockNames;
  }

  @Override
  public Collection<Block> parse(List<String> lines) {
    Map<String, Block> blocksByPath = new HashMap<>();
    blockNames.forEach(thisBlockName -> {
      String thisBlockPath = thisBlockName + "/";

      Block block = new Block(thisBlockName, thisBlockName, null);
      blocksByPath.put(thisBlockPath, block);
      if (LOG.isDebugEnabled()) {
        LOG.debug("parsed block: {}", thisBlockPath);
      }
    });

    return blocksByPath.values();
  }
}