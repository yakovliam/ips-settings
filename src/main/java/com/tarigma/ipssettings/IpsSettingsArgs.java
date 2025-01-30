package com.tarigma.ipssettings;

import com.beust.jcommander.Parameter;
import com.tarigma.ipssettings.detector.InputTypeRelation;
import com.tarigma.ipssettings.detector.InputTypeRelationConverter;

public class IpsSettingsArgs {

  @Parameter(names = {"-file"})
  private String file;

  @Parameter(names = {"-man"}, converter = InputTypeRelationConverter.class)
  private InputTypeRelation man;

  public String file() {
    return file;
  }

  public InputTypeRelation man() {
    return man;
  }
}
