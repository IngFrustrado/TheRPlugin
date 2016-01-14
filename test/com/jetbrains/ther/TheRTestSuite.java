package com.jetbrains.ther;

import com.jetbrains.ther.debugger.TheRDebuggerStringUtilsTest;
import com.jetbrains.ther.debugger.TheRDebuggerTest;
import com.jetbrains.ther.debugger.TheRForcedFunctionDebuggerHandlerTest;
import com.jetbrains.ther.debugger.evaluator.TheRDebuggerEvaluatorImplTest;
import com.jetbrains.ther.debugger.evaluator.TheRExpressionHandlerImplTest;
import com.jetbrains.ther.debugger.executor.TheRExecutionResultCalculatorImplTest;
import com.jetbrains.ther.debugger.executor.TheRExecutorUtilsTest;
import com.jetbrains.ther.debugger.frame.TheRValueModifierHandlerImplTest;
import com.jetbrains.ther.debugger.frame.TheRValueModifierImplTest;
import com.jetbrains.ther.debugger.frame.TheRVarsLoaderImplTest;
import com.jetbrains.ther.debugger.function.TheRBraceFunctionDebuggerTest;
import com.jetbrains.ther.debugger.function.TheRFunctionDebuggerFactoryImplTest;
import com.jetbrains.ther.debugger.function.TheRTraceAndDebugUtilsTest;
import com.jetbrains.ther.debugger.function.TheRUnbraceFunctionDebuggerTest;
import com.jetbrains.ther.inspections.TheRTypeCheckerInspectionTest;
import com.jetbrains.ther.inspections.TheRUnresolvedReferenceInspectionTest;
import com.jetbrains.ther.inspections.TheRUnusedInspectionTest;
import com.jetbrains.ther.lexer.TheRHighlightingLexerTest;
import com.jetbrains.ther.parser.TheRParsingTest;
import com.jetbrains.ther.rename.TheRRenameTest;
import com.jetbrains.ther.ui.graphics.TheRGraphicsPanelTest;
import com.jetbrains.ther.ui.graphics.TheRGraphicsStateImplTest;
import com.jetbrains.ther.xdebugger.stack.TheRXPresentationUtilsTest;
import com.jetbrains.ther.xdebugger.stack.TheRXStackFrameTest;
import com.jetbrains.ther.xdebugger.stack.TheRXStackTest;
import com.jetbrains.ther.xdebugger.stack.TheRXSuspendContextTest;
import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.jetbrains.annotations.NotNull;

public class TheRTestSuite extends TestCase {

  @NotNull
  public static Test suite() {
    final TestSuite suite = new TestSuite("AllTest");

    suite.addTestSuite(TheRTypeCheckerInspectionTest.class);
    suite.addTestSuite(TheRUnresolvedReferenceInspectionTest.class);
    suite.addTestSuite(TheRUnusedInspectionTest.class);
    suite.addTestSuite(TheRHighlightingLexerTest.class);
    suite.addTestSuite(TheRParsingTest.class);
    suite.addTestSuite(TheRRenameTest.class);

    addDebuggerTests(suite);
    addXDebuggerTests(suite);
    addUiTests(suite);

    return suite;
  }

  private static void addDebuggerTests(@NotNull final TestSuite suite) {
    // evaluator package
    addJUnit4Test(suite, TheRDebuggerEvaluatorImplTest.class);
    addJUnit4Test(suite, TheRExpressionHandlerImplTest.class);

    // frame package
    addJUnit4Test(suite, TheRValueModifierHandlerImplTest.class);
    addJUnit4Test(suite, TheRValueModifierImplTest.class);
    addJUnit4Test(suite, TheRVarsLoaderImplTest.class);

    // function package
    addJUnit4Test(suite, TheRFunctionDebuggerFactoryImplTest.class);
    addJUnit4Test(suite, TheRBraceFunctionDebuggerTest.class);
    addJUnit4Test(suite, TheRUnbraceFunctionDebuggerTest.class);
    addJUnit4Test(suite, TheRTraceAndDebugUtilsTest.class);

    // interpreter package
    addJUnit4Test(suite, TheRExecutionResultCalculatorImplTest.class);
    addJUnit4Test(suite, TheRExecutorUtilsTest.class);

    // `main` package
    addJUnit4Test(suite, TheRDebuggerTest.class);
    addJUnit4Test(suite, TheRDebuggerStringUtilsTest.class);
    addJUnit4Test(suite, TheRForcedFunctionDebuggerHandlerTest.class);
  }

  private static void addXDebuggerTests(@NotNull final TestSuite suite) {
    // stack package
    addJUnit4Test(suite, TheRXPresentationUtilsTest.class);
    addJUnit4Test(suite, TheRXStackFrameTest.class);
    addJUnit4Test(suite, TheRXStackTest.class);
    addJUnit4Test(suite, TheRXSuspendContextTest.class);
  }

  private static void addUiTests(@NotNull final TestSuite suite) {
    // graphics
    addJUnit4Test(suite, TheRGraphicsPanelTest.class);
    suite.addTestSuite(TheRGraphicsStateImplTest.class);
  }

  private static void addJUnit4Test(@NotNull final TestSuite suite, @NotNull final Class<?> cls) {
    suite.addTest(new JUnit4TestAdapter(cls));
  }
}
