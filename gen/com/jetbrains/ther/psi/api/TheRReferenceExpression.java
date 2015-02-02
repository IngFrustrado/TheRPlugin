// This is a generated file. Not intended for manual editing.
package com.jetbrains.ther.psi.api;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.jetbrains.ther.psi.references.TheRReferenceImpl;

public interface TheRReferenceExpression extends TheRExpression {

  @Nullable
  PsiElement getFalse();

  @Nullable
  PsiElement getInf();

  @Nullable
  PsiElement getNa();

  @Nullable
  PsiElement getNan();

  @Nullable
  PsiElement getNaCharacter();

  @Nullable
  PsiElement getNaComplex();

  @Nullable
  PsiElement getNaInteger();

  @Nullable
  PsiElement getNaReal();

  @Nullable
  PsiElement getNull();

  @Nullable
  PsiElement getTrue();

  @Nullable
  PsiElement getIdentifier();

  TheRReferenceImpl getReference();

  @Nullable
  String getNamespace();

  String getName();

}
