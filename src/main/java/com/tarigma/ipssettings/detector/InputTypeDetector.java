package com.tarigma.ipssettings.detector;

import java.util.List;
import java.util.Optional;

public class InputTypeDetector {

  /**
   * Returns the expected input type relation based on the file data input
   * <p>
   * Each corresponding string to enum key represents what the expected file
   * will start with. For example, an SEL file is expected to start with the text {@code [INFO]} while
   * a GE file is expected to start with the text {@code Serial Number:}.
   *
   * @param fileData file data
   * @return input type
   */
  public static InputTypeRelation findInputType(List<String> fileData) {
    String firstLine = fileData.get(0);
    InputTypeRelation[] relations = InputTypeRelation.values();

    for (InputTypeRelation relation : relations) {
      Optional<List<String>> relationLines = relation.getStartLineRelations();
      if(relationLines.isEmpty()) {
        return null;
      }

      List<String> actualRelations = relationLines.get();
      if (actualRelations.stream().anyMatch(firstLine::startsWith)) {
        return relation;
      }
    }

    return null;
  }
}
