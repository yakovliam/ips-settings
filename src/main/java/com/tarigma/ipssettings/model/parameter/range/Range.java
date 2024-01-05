package com.tarigma.ipssettings.model.parameter.range;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Range {

  /**
   * Represents `MinValue`
   */
  @JacksonXmlProperty(localName = "MinValue")
  private String minValue;

  /**
   * Represents `MaxValue`
   */
  @JacksonXmlProperty(localName = "MaxValue")
  private String maxValue;

  /**
   * Represents `Step`
   */
  @JacksonXmlProperty(localName = "Step")
  private String step;

  public String getMaxValue() {
    return maxValue;
  }

  public Range setMaxValue(String maxValue) {
    this.maxValue = maxValue;
    return this;
  }

  public String getMinValue() {
    return minValue;
  }

  public Range setMinValue(String minValue) {
    this.minValue = minValue;
    return this;
  }

  public String getStep() {
    return step;
  }

  public Range setStep(String step) {
    this.step = step;
    return this;
  }
}
