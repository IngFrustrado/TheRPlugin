package com.jetbrains.ther.debugger.frame;

import org.jetbrains.annotations.NotNull;

public interface TheRValueModifier {

  boolean isEnabled();

  void setValue(@NotNull final String name, @NotNull final String value, @NotNull final Listener listener);

  interface Listener {

    void onSuccess();

    void onError(@NotNull final String error);

    void onError(@NotNull final Exception e);
  }
}
