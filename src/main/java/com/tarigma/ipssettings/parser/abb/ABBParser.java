package com.tarigma.ipssettings.parser.abb;

import com.tarigma.ipssettings.model.Block;
import com.tarigma.ipssettings.model.RSEIContainer;
import com.tarigma.ipssettings.model.parameter.ParameterSet;
import com.tarigma.ipssettings.parser.RSEIParser;
import com.tarigma.ipssettings.parser.abb.container.ABBBlocksParser;
import com.tarigma.ipssettings.parser.abb.container.ABBHeaderParser;
import com.tarigma.ipssettings.parser.abb.container.parameter.ABBParameterSetParser;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ABBParser implements RSEIParser {

  private static final Logger LOG =
      LoggerFactory.getLogger(com.tarigma.ipssettings.parser.ge.GEParser.class);

  private static final Pattern BLOCK_HEADER = Pattern.compile("\\[(.*)\\]");

  @Override
  public RSEIContainer parse(List<String> lines) {
    // blocks will maintain insertion order
    Map<String, List<String>> linesByBlockName = new LinkedHashMap<>();

    String currentBlockName = null;
    for (String currentLine : lines) {
      Matcher m = BLOCK_HEADER.matcher(currentLine);
      if (m.matches()) {
        // line is a block header
        currentBlockName = m.group(1).trim();
        linesByBlockName.put(currentBlockName, new ArrayList<>());
      } else {
        linesByBlockName.get(currentBlockName).add(currentLine.strip());
      }
    }

    // remove lines that are empty
    linesByBlockName.values().forEach(linesList -> linesList.removeIf(String::isEmpty));

    // parse info
    RSEIContainer rseiContainer = new ABBHeaderParser().parse(Collections.emptyList());

    List<String> blockNames = new ArrayList<>(linesByBlockName.keySet());
    // parse blocks
    Collection<Block> blocks = new ABBBlocksParser(blockNames).parse(lines);
    rseiContainer.setBlocks(blocks);

    // map blocks by name for quick lookup
    Map<String, Block> blocksByPath =
        blocks.stream().collect(Collectors.toMap(Block::getBlockPath, Function.identity()));

    Map<String, List<String>> parameterLinesByBlockPath = splitBlocksFromParameters(lines);

    // parse parameters
    ParameterSet parameterSet =
        new ABBParameterSetParser().parse(parameterLinesByBlockPath, blocksByPath);
    rseiContainer.setParameterSet(parameterSet);

    return rseiContainer;
  }

  private Map<String, List<String>> splitBlocksFromParameters(List<String> parsableData) {
    Map<String, List<String>> result = new LinkedHashMap<>();

    // go through each line
    // the keys of the result map will be the block paths
    // and the values will be the lines of the parameters for that block

    var iterator = parsableData.iterator();
    List<String> currentBlockLines = new ArrayList<>();
    while (iterator.hasNext()) {
      String currentLine = iterator.next();
      Matcher m = BLOCK_HEADER.matcher(currentLine);
      if (m.matches()) {
        String currentBlockName = m.group(1);
        String currentBlockPath = currentBlockName + "/";

        if (!currentBlockLines.isEmpty()) {
          // we've reached the next block, so add the current block to the result
          result.put(currentBlockPath, currentBlockLines);
          currentBlockLines = new ArrayList<>();
        } else {
          // line is a block header
          result.put(currentBlockPath, new ArrayList<>());
        }
      } else {
        if (!currentLine.isEmpty()) {
          currentBlockLines.add(currentLine.strip());
        }
      }
    }

    return result;
  }
}