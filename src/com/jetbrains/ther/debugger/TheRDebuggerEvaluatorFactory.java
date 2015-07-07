package com.jetbrains.ther.debugger;

import com.jetbrains.ther.debugger.data.TheRFunction;
import com.jetbrains.ther.debugger.interpreter.TheRProcess;
import com.jetbrains.ther.debugger.utils.TheRLoadableVarHandler;
import org.jetbrains.annotations.NotNull;

public interface TheRDebuggerEvaluatorFactory {

  @NotNull
  TheRDebuggerEvaluator getEvaluator(@NotNull final TheRProcess process,
                                     @NotNull final TheRFunctionDebuggerHandler debuggerHandler,
                                     @NotNull final TheRFunctionResolver functionResolver,
                                     @NotNull final TheRLoadableVarHandler varHandler,
                                     @NotNull final TheRFunction function);
}
