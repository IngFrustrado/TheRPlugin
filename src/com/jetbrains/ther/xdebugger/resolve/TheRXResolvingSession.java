package com.jetbrains.ther.xdebugger.resolve;

import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.xdebugger.XDebuggerUtil;
import com.intellij.xdebugger.XSourcePosition;
import com.jetbrains.ther.debugger.data.TheRLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

// TODO [xdbg][test]
public class TheRXResolvingSession {

  @NotNull
  private final TheRXFunctionDescriptor myRoot;

  @NotNull
  private final VirtualFile myVirtualFile;

  @NotNull
  private final List<TheRXResolvingSessionEntry> myEntries;

  public TheRXResolvingSession(@NotNull final TheRXFunctionDescriptor root, @NotNull final VirtualFile virtualFile) {
    myRoot = root;
    myVirtualFile = virtualFile;
    myEntries = new ArrayList<TheRXResolvingSessionEntry>();
  }

  @Nullable
  public XSourcePosition resolveNext(@NotNull final TheRLocation nextLocation) {
    addEntry(nextLocation);

    return resolvePosition(myEntries.get(myEntries.size() - 1));
  }

  @Nullable
  public XSourcePosition resolveCurrent(final int line) {
    updateCurrentEntry(line);

    return resolvePosition(myEntries.get(myEntries.size() - 1));
  }

  public void dropLast(final int number) {
    final ListIterator<TheRXResolvingSessionEntry> iterator = myEntries.listIterator(myEntries.size());
    iterator.previous();

    for (int i = 0; i < number; i++) {
      iterator.remove();
      iterator.previous();
    }
  }

  private void addEntry(@NotNull final TheRLocation nextLocation) {
    final TheRXFunctionDescriptor descriptor =
      myEntries.isEmpty() ? myRoot : resolveDescriptor(myEntries.listIterator(myEntries.size()), nextLocation.getFunctionName());

    myEntries.add(
      new TheRXResolvingSessionEntry(
        descriptor,
        nextLocation.getLine()
      )
    );
  }

  @Nullable
  private XSourcePosition resolvePosition(@NotNull final TheRXResolvingSessionEntry entry) {
    if (entry.myDescriptor == null) {
      return null;
    }

    final int result = calculateLineOffset(entry.myDescriptor) + entry.myLine;

    return XDebuggerUtil.getInstance().createPosition(myVirtualFile, result); // TODO [xdbg][null]
  }

  private void updateCurrentEntry(final int line) {
    final int lastIndex = myEntries.size() - 1;

    myEntries.set(
      lastIndex,
      new TheRXResolvingSessionEntry(
        myEntries.get(lastIndex).myDescriptor,
        line
      )
    );
  }

  @Nullable
  private TheRXFunctionDescriptor resolveDescriptor(@NotNull final ListIterator<TheRXResolvingSessionEntry> entries,
                                                    @NotNull final String nextFunctionName) {
    if (!entries.hasPrevious()) {
      return null;
    }

    final TheRXFunctionDescriptor candidate = resolveDescriptor(entries.previous(), nextFunctionName);

    if (candidate != null) {
      return candidate;
    }

    return resolveDescriptor(entries, nextFunctionName);
  }

  private int calculateLineOffset(@NotNull final TheRXFunctionDescriptor descriptor) {
    if (descriptor.getParent() == null) {
      return 0;
    }

    return getEldestNotMainParent(descriptor).getStartLine();
  }

  @Nullable
  private TheRXFunctionDescriptor resolveDescriptor(@NotNull final TheRXResolvingSessionEntry entry,
                                                    @NotNull final String nextFunctionName) {
    final TheRXFunctionDescriptor currentDescriptor = entry.myDescriptor;

    if (currentDescriptor == null) {
      return null;
    }

    TheRXFunctionDescriptor result = null;

    if (currentDescriptor.getChildren().containsKey(nextFunctionName)) {
      int distance = Integer.MAX_VALUE;

      for (final TheRXFunctionDescriptor candidate : currentDescriptor.getChildren().get(nextFunctionName)) {
        final int currentDistance = currentDescriptor.getStartLine() + entry.myLine - candidate.getStartLine();

        if (currentDistance > 0 && currentDistance < distance) { // candidate is declared before the current line
          result = candidate;
          distance = currentDistance;
        }
      }
    }

    return result;
  }

  @NotNull
  private TheRXFunctionDescriptor getEldestNotMainParent(@NotNull final TheRXFunctionDescriptor descriptor) {
    TheRXFunctionDescriptor current = descriptor;

    // 1. Method is called with descriptor which holds any function except `MAIN_FUNCTION`.
    // 2. There is only one descriptor which has `null` parent. This descriptor is root and it holds `MAIN_FUNCTION`.
    // Conclusion: current.getParent() can't return `null` here.

    //noinspection ConstantConditions
    while (current.getParent().getParent() != null) {
      current = current.getParent();
    }

    return current;
  }

  private static class TheRXResolvingSessionEntry {

    @Nullable
    private final TheRXFunctionDescriptor myDescriptor;

    private final int myLine;

    public TheRXResolvingSessionEntry(@Nullable final TheRXFunctionDescriptor descriptor, final int line) {
      myDescriptor = descriptor;
      myLine = line;
    }
  }
}
