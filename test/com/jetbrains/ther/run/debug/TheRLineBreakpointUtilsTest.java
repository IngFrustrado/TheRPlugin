package com.jetbrains.ther.run.debug;

import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.testFramework.PlatformTestCase;

import java.io.IOException;

public class TheRLineBreakpointUtilsTest extends PlatformTestCase {

  public void testNotRFile() throws IOException {
    final VirtualFile file = getVirtualFile(createTempFile("script.s", "print(\"ok\")"));
    assert file != null;

    assertFalse(
      TheRLineBreakpointUtils.canPutAt(getProject(), file, 0)
    );
  }

  public void testWhitespaces() throws IOException {
    final VirtualFile file = getVirtualFile(createTempFile("script.r", "   "));
    assert file != null;

    assertFalse(
      TheRLineBreakpointUtils.canPutAt(getProject(), file, 0)
    );
  }

  public void testComment() throws IOException {
    final VirtualFile file = getVirtualFile(createTempFile("script.r", "# comment"));
    assert file != null;

    assertFalse(
      TheRLineBreakpointUtils.canPutAt(getProject(), file, 0)
    );
  }

  public void testLeftBrace() throws IOException {
    final VirtualFile file = getVirtualFile(createTempFile("script.r", "{"));
    assert file != null;

    assertFalse(
      TheRLineBreakpointUtils.canPutAt(getProject(), file, 0)
    );
  }

  public void testRightBrace() throws IOException {
    final VirtualFile file = getVirtualFile(createTempFile("script.r", "}"));
    assert file != null;

    assertFalse(
      TheRLineBreakpointUtils.canPutAt(getProject(), file, 0)
    );
  }

  public void testOk() throws IOException {
    final VirtualFile file = getVirtualFile(createTempFile("script.r", "print(\"ok\")"));
    assert file != null;

    assertTrue(
      TheRLineBreakpointUtils.canPutAt(getProject(), file, 0)
    );
  }
}