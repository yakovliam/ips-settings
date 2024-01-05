package com.tarigma.ipssettings.model.parameter.localization;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

public class LocalizationName {

  /**
   * Represents attribute `Lang3`
   */
  @JacksonXmlProperty(isAttribute = true, localName = "Lang3")
  private String lang3;

  /**
   * Represents the value of the name element / node
   */
  @JacksonXmlText
  private String value;

  /**
   * Construct localization name
   *
   * @param lang3 lang3
   * @param value value
   */
  public LocalizationName(String lang3, String value) {
    this.lang3 = lang3;
    this.value = value;
  }

  public String getLang3() {
    return lang3;
  }

  public LocalizationName setLang3(String lang3) {
    this.lang3 = lang3;
    return this;
  }

  public String getValue() {
    return value;
  }

  public LocalizationName setValue(String value) {
    this.value = value;
    return this;
  }
}
