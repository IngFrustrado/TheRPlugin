package com.jetbrains.ther.debugger.data;

import org.jetbrains.annotations.NotNull;

public final class TheRDebugConstants {

  @NotNull
  public static final String SERVICE_FUNCTION_PREFIX = "jetbrains_ther_";

  @NotNull
  public static final String SERVICE_ENTER_FUNCTION_SUFFIX = "_enter";

  @NotNull
  public static final String MAIN_FUNCTION_NAME = SERVICE_FUNCTION_PREFIX + "main";
}
