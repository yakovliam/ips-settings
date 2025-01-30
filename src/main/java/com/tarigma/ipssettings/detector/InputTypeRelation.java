package com.tarigma.ipssettings.detector;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * This is an enumeration of strings
 * <p>
 * Each corresponding string to enum key represents what the expected file
 * will start with. For example, an SEL file is expected to start with the text {@code [INFO]} while
 * a GE file is expected to start with the text {@code Serial Number:}.
 */
public enum InputTypeRelation {

  SEL(Collections.singletonList("[INFO]")), GE(List.of("Serial Number:", "Product Version:")),
  SIEMENS(Collections.singletonList("[General.Device]")), ABB(null);

  private final List<String> startLineRelations;

  /**
   * Construct input type relation
   *
   * @param startLineRelations start line relations
   */
  InputTypeRelation(List<String> startLineRelations) {
    this.startLineRelations = startLineRelations;
  }

  /**
   * Return the start line relation
   *
   * @return start line relation
   */
  public Optional<List<String>> getStartLineRelations() {
    return Optional.ofNullable(this.startLineRelations);
  }
}
