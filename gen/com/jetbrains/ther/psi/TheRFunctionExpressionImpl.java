// This is a generated file. Not intended for manual editing.
package com.jetbrains.ther.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.jetbrains.ther.parsing.TheRElementTypes.*;
import com.jetbrains.ther.psi.api.*;

public class TheRFunctionExpressionImpl extends TheRExpressionImpl implements TheRFunctionExpression {

  public TheRFunctionExpressionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof TheRVisitor) ((TheRVisitor)visitor).visitFunctionExpression(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public TheRExpression getExpression() {
    return findChildByClass(TheRExpression.class);
  }

  @Override
  @NotNull
  public TheRParameterList getParameterList() {
    return findNotNullChildByClass(TheRParameterList.class);
  }

  @Override
  @NotNull
  public PsiElement getFunction() {
    return findNotNullChildByType(THE_R_FUNCTION);
  }

}
