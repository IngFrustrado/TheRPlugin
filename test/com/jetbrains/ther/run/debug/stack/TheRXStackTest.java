package com.jetbrains.ther.run.debug.stack;

import com.intellij.xdebugger.XSourcePosition;
import com.intellij.xdebugger.frame.XExecutionStack;
import com.intellij.xdebugger.frame.XStackFrame;
import com.jetbrains.ther.debugger.data.TheRLocation;
import com.jetbrains.ther.debugger.frame.TheRStackFrame;
import com.jetbrains.ther.debugger.mock.IllegalTheRDebuggerEvaluator;
import com.jetbrains.ther.debugger.mock.IllegalTheRVarsLoader;
import com.jetbrains.ther.run.debug.mock.ExecutorServices;
import com.jetbrains.ther.run.debug.mock.MockXSourcePosition;
import com.jetbrains.ther.run.debug.mock.MockXStackFrameContainer;
import com.jetbrains.ther.run.debug.resolve.TheRXResolvingSession;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TheRXStackTest {

  @Test
  public void sameDepth() {
    final List<TheRStackFrame> originalStack = new ArrayList<TheRStackFrame>();
    final MockTheRXResolvingSession resolvingSession = new MockTheRXResolvingSession();

    final TheRXStack stack = new TheRXStack(originalStack, resolvingSession, ExecutorServices.ILLEGAL_EXECUTOR);

    originalStack.add(
      new TheRStackFrame(
        new TheRLocation("abc", 2),
        new IllegalTheRVarsLoader(),
        new IllegalTheRDebuggerEvaluator()
      )
    );

    originalStack.add(
      new TheRStackFrame(
        new TheRLocation("def", 1),
        new IllegalTheRVarsLoader(),
        new IllegalTheRDebuggerEvaluator()
      )
    );

    stack.update();

    assertEquals(0, resolvingSession.myCurrent);
    assertEquals(2, resolvingSession.myNext);
    assertEquals(0, resolvingSession.myDropped);
    check(stack, 2, 1);

    originalStack.set(
      1,
      new TheRStackFrame(
        new TheRLocation("def", 2),
        new IllegalTheRVarsLoader(),
        new IllegalTheRDebuggerEvaluator()
      )
    );

    stack.update();

    assertEquals(1, resolvingSession.myCurrent);
    assertEquals(2, resolvingSession.myNext);
    assertEquals(0, resolvingSession.myDropped);
    check(stack, 3, 1);
  }

  @Test
  public void plusOneDepth() {
    final List<TheRStackFrame> originalStack = new ArrayList<TheRStackFrame>();
    final MockTheRXResolvingSession resolvingSession = new MockTheRXResolvingSession();

    final TheRXStack stack = new TheRXStack(originalStack, resolvingSession, ExecutorServices.ILLEGAL_EXECUTOR);

    originalStack.add(
      new TheRStackFrame(
        new TheRLocation("abc", 2),
        new IllegalTheRVarsLoader(),
        new IllegalTheRDebuggerEvaluator()
      )
    );

    originalStack.add(
      new TheRStackFrame(
        new TheRLocation("def", 1),
        new IllegalTheRVarsLoader(),
        new IllegalTheRDebuggerEvaluator()
      )
    );

    stack.update();

    assertEquals(2, resolvingSession.myNext);
    assertEquals(0, resolvingSession.myCurrent);
    assertEquals(0, resolvingSession.myDropped);
    check(stack, 2, 1);

    originalStack.add(
      new TheRStackFrame(
        new TheRLocation("ghi", 1),
        new IllegalTheRVarsLoader(),
        new IllegalTheRDebuggerEvaluator()
      )
    );

    stack.update();

    assertEquals(3, resolvingSession.myNext);
    assertEquals(0, resolvingSession.myCurrent);
    assertEquals(0, resolvingSession.myDropped);
    check(stack, 3, 2, 1);
  }

  @Test
  public void moreDepth() {
    final List<TheRStackFrame> originalStack = new ArrayList<TheRStackFrame>();
    final MockTheRXResolvingSession resolvingSession = new MockTheRXResolvingSession();

    final TheRXStack stack = new TheRXStack(originalStack, resolvingSession, ExecutorServices.ILLEGAL_EXECUTOR);

    originalStack.add(
      new TheRStackFrame(
        new TheRLocation("abc", 2),
        new IllegalTheRVarsLoader(),
        new IllegalTheRDebuggerEvaluator()
      )
    );

    originalStack.add(
      new TheRStackFrame(
        new TheRLocation("def", 1),
        new IllegalTheRVarsLoader(),
        new IllegalTheRDebuggerEvaluator()
      )
    );

    stack.update();

    assertEquals(2, resolvingSession.myNext);
    assertEquals(0, resolvingSession.myCurrent);
    assertEquals(0, resolvingSession.myDropped);
    check(stack, 2, 1);

    originalStack.add(
      new TheRStackFrame(
        new TheRLocation("ghi", 1),
        new IllegalTheRVarsLoader(),
        new IllegalTheRDebuggerEvaluator()
      )
    );

    originalStack.add(
      new TheRStackFrame(
        new TheRLocation("jkl", 1),
        new IllegalTheRVarsLoader(),
        new IllegalTheRDebuggerEvaluator()
      )
    );

    stack.update();

    assertEquals(4, resolvingSession.myNext);
    assertEquals(0, resolvingSession.myCurrent);
    assertEquals(0, resolvingSession.myDropped);
    check(stack, 4, 3, 2, 1);
  }

  @Test
  public void minusOneDepth() {
    final List<TheRStackFrame> originalStack = new ArrayList<TheRStackFrame>();
    final MockTheRXResolvingSession resolvingSession = new MockTheRXResolvingSession();

    final TheRXStack stack = new TheRXStack(originalStack, resolvingSession, ExecutorServices.ILLEGAL_EXECUTOR);

    originalStack.add(
      new TheRStackFrame(
        new TheRLocation("abc", 2),
        new IllegalTheRVarsLoader(),
        new IllegalTheRDebuggerEvaluator()
      )
    );

    originalStack.add(
      new TheRStackFrame(
        new TheRLocation("def", 1),
        new IllegalTheRVarsLoader(),
        new IllegalTheRDebuggerEvaluator()
      )
    );

    stack.update();

    assertEquals(2, resolvingSession.myNext);
    assertEquals(0, resolvingSession.myCurrent);
    assertEquals(0, resolvingSession.myDropped);
    check(stack, 2, 1);

    originalStack.remove(originalStack.size() - 1);

    stack.update();

    assertEquals(2, resolvingSession.myNext);
    assertEquals(1, resolvingSession.myCurrent);
    assertEquals(1, resolvingSession.myDropped);
    check(stack, 3, 1);
  }

  @Test
  public void lessDepth() {
    final List<TheRStackFrame> originalStack = new ArrayList<TheRStackFrame>();
    final MockTheRXResolvingSession resolvingSession = new MockTheRXResolvingSession();

    final TheRXStack stack = new TheRXStack(originalStack, resolvingSession, ExecutorServices.ILLEGAL_EXECUTOR);

    originalStack.add(
      new TheRStackFrame(
        new TheRLocation("abc", 2),
        new IllegalTheRVarsLoader(),
        new IllegalTheRDebuggerEvaluator()
      )
    );

    originalStack.add(
      new TheRStackFrame(
        new TheRLocation("def", 1),
        new IllegalTheRVarsLoader(),
        new IllegalTheRDebuggerEvaluator()
      )
    );

    originalStack.add(
      new TheRStackFrame(
        new TheRLocation("ghi", 1),
        new IllegalTheRVarsLoader(),
        new IllegalTheRDebuggerEvaluator()
      )
    );

    stack.update();

    assertEquals(3, resolvingSession.myNext);
    assertEquals(0, resolvingSession.myCurrent);
    assertEquals(0, resolvingSession.myDropped);
    check(stack, 3, 2, 1);

    originalStack.remove(originalStack.size() - 1);
    originalStack.remove(originalStack.size() - 1);

    stack.update();

    assertEquals(3, resolvingSession.myNext);
    assertEquals(1, resolvingSession.myCurrent);
    assertEquals(2, resolvingSession.myDropped);
    check(stack, 4);
  }

  private void check(@NotNull final TheRXStack stack, @NotNull final int... lines) {
    final MockXStackFrameContainer container = new MockXStackFrameContainer();

    final XExecutionStack executionStack = stack.getSuspendContext().getActiveExecutionStack();
    assert executionStack != null;

    executionStack.computeStackFrames(0, container);

    int index = 0;
    for (final XStackFrame frame : container.getResult()) {
      final XSourcePosition position = frame.getSourcePosition();
      assert position != null;

      assertEquals(lines[index], position.getLine());

      index++;
    }
  }

  private static class MockTheRXResolvingSession implements TheRXResolvingSession {

    private int myNext = 0;
    private int myCurrent = 0;
    private int myDropped = 0;

    @Nullable
    @Override
    public XSourcePosition resolveNext(@NotNull final TheRLocation nextLocation) {
      myNext++;

      return new MockXSourcePosition(null, myNext + myCurrent);
    }

    @Nullable
    @Override
    public XSourcePosition resolveCurrent(final int line) {
      myCurrent++;

      return new MockXSourcePosition(null, myNext + myCurrent);
    }

    @Override
    public void dropLast(final int number) {
      myDropped += number;
    }
  }
}