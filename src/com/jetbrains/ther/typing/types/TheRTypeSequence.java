package com.jetbrains.ther.typing.types;

import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.Function;
import com.jetbrains.ther.typing.TheRTypeEnvironment;

import java.util.List;

public class TheRTypeSequence extends TheRType {
  private List<TheRType> myTypes;

  public TheRTypeSequence(List<TheRType> types) {
    myTypes = types;
  }

  @Override
  public String getCanonicalName() {
    return "type sequence: " + StringUtil.join(myTypes, new Function<TheRType, String>() {
      @Override
      public String fun(TheRType type) {
        return type.getName();
      }
    }, ",");
  }

  @Override
  public TheRType resolveType(TheRTypeEnvironment env) {
    return TheRType.getMaxType(myTypes, env);
  }
}
