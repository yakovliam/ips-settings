package com.tarigma.ipssettings.detector;

import com.beust.jcommander.IStringConverter;

public class InputTypeRelationConverter implements IStringConverter<InputTypeRelation> {
  @Override
  public InputTypeRelation convert(String s) {
    return InputTypeRelation.valueOf(s.toUpperCase());
  }
}
