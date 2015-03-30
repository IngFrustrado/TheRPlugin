package com.jetbrains.ther.rename;

import com.jetbrains.ther.TheRTestCase;

public class TheRRenameTest extends TheRTestCase {

  private void doTestWithProject(String newName) {
    myFixture.configureByFile("rename/" + getTestName(true) + "/main.R");
    myFixture.renameElementAtCaret(newName);
    myFixture.checkResultByFile("rename/" + getTestName(true) + "/main.after.R", true);
  }

  public void testRenameFunction() {
    doTestWithProject("test_function1");
  }

  public void testRenameParameter() {
    doTestWithProject("x1");
  }
}
