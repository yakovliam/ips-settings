package com.tarigma.ipssettings.detector;

/**
 * This is an enumeration of strings
 * <p>
 * Each corresponding string to enum key represents what the expected file
 * will start with. For example, an SEL file is expected to start with the text {@code [INFO]} while
 * a GE file is expected to start with the text {@code Serial Number:}.
 */
public enum InputTypeRelation {

  SEL("[INFO]"),
  GE("Serial Number:"),

  SIEMENS("[General.Device]");

  private final String startLineRelation;

  /**
   * Construct input type relation
   *
   * @param startLineRelation start line relation
   */
  InputTypeRelation(String startLineRelation) {
    this.startLineRelation = startLineRelation;
  }

  /**
   * Return the start line relation
   *
   * @return start line relation
   */
  public String getStartLineRelation() {
    return this.startLineRelation;
  }
}
