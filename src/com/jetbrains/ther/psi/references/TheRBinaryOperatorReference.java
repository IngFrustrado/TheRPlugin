package com.jetbrains.ther.psi.references;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiPolyVariantReference;
import com.intellij.psi.ResolveResult;
import com.intellij.util.IncorrectOperationException;
import com.jetbrains.ther.psi.api.TheRBinaryOperator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TheRBinaryOperatorReference implements PsiPolyVariantReference {

  private final TheRBinaryOperator myElement;

  public TheRBinaryOperatorReference(TheRBinaryOperator element) {
    myElement = element;
  }

  @NotNull
  @Override
  public ResolveResult[] multiResolve(boolean incompleteCode) {
    List<ResolveResult> result = new ArrayList<ResolveResult>();
    String text = myElement.getText();
    String quotedText = "\"" + text + "\"";
    String backquotedText = "`" + text + "`";
    TheRResolver.resolveWithoutNamespaceInFile(myElement, result, quotedText, backquotedText);
    if (result.isEmpty()) {
      TheRResolver.addFromSkeletonsAndRLibrary(myElement, result, quotedText, backquotedText);
    }
    return result.toArray(new ResolveResult[result.size()]);
  }

  @Override
  public PsiElement getElement() {
    return myElement;
  }

  @Override
  public TextRange getRangeInElement() {
    final TextRange range = myElement.getNode().getTextRange();
    return range.shiftRight(-myElement.getNode().getStartOffset());
  }

  @Nullable
  @Override
  public PsiElement resolve() {
    final ResolveResult[] results = multiResolve(false);
    return results.length >= 1 ? results[0].getElement() : null;
  }

  @NotNull
  @Override
  public String getCanonicalText() {
    return getElement().getText();
  }

  @Override
  public PsiElement handleElementRename(String newElementName) throws IncorrectOperationException {
    return null;
  }

  @Override
  public PsiElement bindToElement(@NotNull PsiElement element) throws IncorrectOperationException {
    return null;
  }

  @Override
  public boolean isReferenceTo(PsiElement element) {
    return resolve() == element;
  }

  @NotNull
  @Override
  public Object[] getVariants() {
    return new Object[0];
  }

  @Override
  public boolean isSoft() {
    return false;
  }
}
