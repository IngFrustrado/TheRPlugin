// This is a generated file. Not intended for manual editing.
package com.jetbrains.ther.parsing;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static com.jetbrains.ther.parsing.TheRElementTypes.*;
import static com.jetbrains.ther.parsing.TheRParserUtil.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class TheRParser implements PsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, EXTENDS_SETS_);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    if (t == THE_R_ARGUMENT_LIST) {
      r = argument_list(b, 0);
    }
    else if (t == THE_R_ASSIGNMENT_STATEMENT) {
      r = assignment_statement(b, 0);
    }
    else if (t == THE_R_BINARY_EXPRESSION) {
      r = binary_expression(b, 0);
    }
    else if (t == THE_R_BLOCK_EXPRESSION) {
      r = block_expression(b, 0);
    }
    else if (t == THE_R_BREAK_STATEMENT) {
      r = break_statement(b, 0);
    }
    else if (t == THE_R_CALL_EXPRESSION) {
      r = expression(b, 0, 24);
    }
    else if (t == THE_R_EMPTY_EXPRESSION) {
      r = empty_expression(b, 0);
    }
    else if (t == THE_R_EXPRESSION) {
      r = expression(b, 0, -1);
    }
    else if (t == THE_R_FOR_STATEMENT) {
      r = for_statement(b, 0);
    }
    else if (t == THE_R_FUNCTION_EXPRESSION) {
      r = function_expression(b, 0);
    }
    else if (t == THE_R_HELP_EXPRESSION) {
      r = help_expression(b, 0);
    }
    else if (t == THE_R_IF_STATEMENT) {
      r = if_statement(b, 0);
    }
    else if (t == THE_R_NEXT_STATEMENT) {
      r = next_statement(b, 0);
    }
    else if (t == THE_R_NUMERIC_LITERAL_EXPRESSION) {
      r = numeric_literal_expression(b, 0);
    }
    else if (t == THE_R_PARAMETER) {
      r = parameter(b, 0);
    }
    else if (t == THE_R_PARAMETER_LIST) {
      r = parameter_list(b, 0);
    }
    else if (t == THE_R_PARENTHESIZED_EXPRESSION) {
      r = parenthesized_expression(b, 0);
    }
    else if (t == THE_R_PREFIX_EXPRESSION) {
      r = prefix_expression(b, 0);
    }
    else if (t == THE_R_REFERENCE_EXPRESSION) {
      r = reference_expression(b, 0);
    }
    else if (t == THE_R_REPEAT_STATEMENT) {
      r = repeat_statement(b, 0);
    }
    else if (t == THE_R_SLICE_EXPRESSION) {
      r = expression(b, 0, 20);
    }
    else if (t == THE_R_STRING_LITERAL_EXPRESSION) {
      r = string_literal_expression(b, 0);
    }
    else if (t == THE_R_SUBSCRIPTION_EXPRESSION) {
      r = expression(b, 0, 23);
    }
    else if (t == THE_R_WHILE_STATEMENT) {
      r = while_statement(b, 0);
    }
    else {
      r = parse_root_(t, b, 0);
    }
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return root(b, l + 1);
  }

  public static final TokenSet[] EXTENDS_SETS_ = new TokenSet[] {
    create_token_set_(THE_R_ASSIGNMENT_STATEMENT, THE_R_BINARY_EXPRESSION, THE_R_BLOCK_EXPRESSION, THE_R_BREAK_STATEMENT,
      THE_R_CALL_EXPRESSION, THE_R_EMPTY_EXPRESSION, THE_R_EXPRESSION, THE_R_FOR_STATEMENT,
      THE_R_FUNCTION_EXPRESSION, THE_R_HELP_EXPRESSION, THE_R_IF_STATEMENT, THE_R_NEXT_STATEMENT,
      THE_R_NUMERIC_LITERAL_EXPRESSION, THE_R_PARENTHESIZED_EXPRESSION, THE_R_PREFIX_EXPRESSION, THE_R_REFERENCE_EXPRESSION,
      THE_R_REPEAT_STATEMENT, THE_R_SLICE_EXPRESSION, THE_R_STRING_LITERAL_EXPRESSION, THE_R_SUBSCRIPTION_EXPRESSION,
      THE_R_WHILE_STATEMENT),
  };

  /* ********************************************************** */
  // '...' | expression | external_empty_expression
  static boolean arg(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arg")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, "...");
    if (!r) r = expression(b, l + 1, -1);
    if (!r) r = parseEmptyExpression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // '(' nl* ')' | '(' nl* arg nl* (',' nl* arg nl*)* ')'
  public static boolean argument_list(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "argument_list")) return false;
    if (!nextTokenIs(b, THE_R_LPAR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = argument_list_0(b, l + 1);
    if (!r) r = argument_list_1(b, l + 1);
    exit_section_(b, m, THE_R_ARGUMENT_LIST, r);
    return r;
  }

  // '(' nl* ')'
  private static boolean argument_list_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "argument_list_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, THE_R_LPAR);
    r = r && argument_list_0_1(b, l + 1);
    r = r && consumeToken(b, THE_R_RPAR);
    exit_section_(b, m, null, r);
    return r;
  }

  // nl*
  private static boolean argument_list_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "argument_list_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "argument_list_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // '(' nl* arg nl* (',' nl* arg nl*)* ')'
  private static boolean argument_list_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "argument_list_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, THE_R_LPAR);
    r = r && argument_list_1_1(b, l + 1);
    r = r && arg(b, l + 1);
    r = r && argument_list_1_3(b, l + 1);
    r = r && argument_list_1_4(b, l + 1);
    r = r && consumeToken(b, THE_R_RPAR);
    exit_section_(b, m, null, r);
    return r;
  }

  // nl*
  private static boolean argument_list_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "argument_list_1_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "argument_list_1_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // nl*
  private static boolean argument_list_1_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "argument_list_1_3")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "argument_list_1_3", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // (',' nl* arg nl*)*
  private static boolean argument_list_1_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "argument_list_1_4")) return false;
    int c = current_position_(b);
    while (true) {
      if (!argument_list_1_4_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "argument_list_1_4", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // ',' nl* arg nl*
  private static boolean argument_list_1_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "argument_list_1_4_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, THE_R_COMMA);
    r = r && argument_list_1_4_0_1(b, l + 1);
    r = r && arg(b, l + 1);
    r = r && argument_list_1_4_0_3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // nl*
  private static boolean argument_list_1_4_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "argument_list_1_4_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "argument_list_1_4_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // nl*
  private static boolean argument_list_1_4_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "argument_list_1_4_0_3")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "argument_list_1_4_0_3", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // expression
  public static boolean assignment_statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "assignment_statement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, "<assignment statement>");
    r = expression(b, l + 1, -1);
    exit_section_(b, l, m, THE_R_ASSIGNMENT_STATEMENT, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // expression
  public static boolean binary_expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "binary_expression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, "<binary expression>");
    r = expression(b, l + 1, -1);
    exit_section_(b, l, m, THE_R_BINARY_EXPRESSION, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // nl* '(' nl* (expression nl*)? ')'
  static boolean break_next_expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "break_next_expression")) return false;
    if (!nextTokenIs(b, "", THE_R_LPAR, THE_R_NL)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = break_next_expression_0(b, l + 1);
    r = r && consumeToken(b, THE_R_LPAR);
    r = r && break_next_expression_2(b, l + 1);
    r = r && break_next_expression_3(b, l + 1);
    r = r && consumeToken(b, THE_R_RPAR);
    exit_section_(b, m, null, r);
    return r;
  }

  // nl*
  private static boolean break_next_expression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "break_next_expression_0")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "break_next_expression_0", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // nl*
  private static boolean break_next_expression_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "break_next_expression_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "break_next_expression_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // (expression nl*)?
  private static boolean break_next_expression_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "break_next_expression_3")) return false;
    break_next_expression_3_0(b, l + 1);
    return true;
  }

  // expression nl*
  private static boolean break_next_expression_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "break_next_expression_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = expression(b, l + 1, -1);
    r = r && break_next_expression_3_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // nl*
  private static boolean break_next_expression_3_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "break_next_expression_3_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "break_next_expression_3_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // expression
  public static boolean empty_expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "empty_expression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, "<empty expression>");
    r = expression(b, l + 1, -1);
    exit_section_(b, l, m, THE_R_EMPTY_EXPRESSION, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // expression? (semicolon+ expression?)*
  static boolean expression_list(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expression_list")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = expression_list_0(b, l + 1);
    r = r && expression_list_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // expression?
  private static boolean expression_list_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expression_list_0")) return false;
    expression(b, l + 1, -1);
    return true;
  }

  // (semicolon+ expression?)*
  private static boolean expression_list_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expression_list_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!expression_list_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "expression_list_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // semicolon+ expression?
  private static boolean expression_list_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expression_list_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = expression_list_1_0_0(b, l + 1);
    r = r && expression_list_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // semicolon+
  private static boolean expression_list_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expression_list_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = semicolon(b, l + 1);
    int c = current_position_(b);
    while (r) {
      if (!semicolon(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "expression_list_1_0_0", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // expression?
  private static boolean expression_list_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expression_list_1_0_1")) return false;
    expression(b, l + 1, -1);
    return true;
  }

  /* ********************************************************** */
  // NA_INTEGER | NA_REAL | NA_COMPLEX | NA_CHARACTER |
  //   TRIPLE_DOTS | if | else | repeat | while |
  //   function | for | in | next | break
  static boolean keyword(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "keyword")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, THE_R_NA_INTEGER);
    if (!r) r = consumeToken(b, THE_R_NA_REAL);
    if (!r) r = consumeToken(b, THE_R_NA_COMPLEX);
    if (!r) r = consumeToken(b, THE_R_NA_CHARACTER);
    if (!r) r = consumeToken(b, THE_R_TRIPLE_DOTS);
    if (!r) r = consumeToken(b, THE_R_IF);
    if (!r) r = consumeToken(b, THE_R_ELSE);
    if (!r) r = consumeToken(b, THE_R_REPEAT);
    if (!r) r = consumeToken(b, THE_R_WHILE);
    if (!r) r = consumeToken(b, THE_R_FUNCTION);
    if (!r) r = consumeToken(b, THE_R_FOR);
    if (!r) r = consumeToken(b, THE_R_IN);
    if (!r) r = consumeToken(b, THE_R_NEXT);
    if (!r) r = consumeToken(b, THE_R_BREAK);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // identifier | string | '...'
  static boolean member_tag(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "member_tag")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, THE_R_IDENTIFIER);
    if (!r) r = consumeToken(b, THE_R_STRING);
    if (!r) r = consumeToken(b, "...");
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (identifier '=' expression) | identifier | '...'
  public static boolean parameter(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameter")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<parameter>");
    r = parameter_0(b, l + 1);
    if (!r) r = consumeToken(b, THE_R_IDENTIFIER);
    if (!r) r = consumeToken(b, "...");
    exit_section_(b, l, m, THE_R_PARAMETER, r, false, null);
    return r;
  }

  // identifier '=' expression
  private static boolean parameter_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameter_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, THE_R_IDENTIFIER);
    r = r && consumeToken(b, THE_R_EQ);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ('(' ')') | ('(' parameter nl* (',' nl* parameter nl*)* ')')
  public static boolean parameter_list(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameter_list")) return false;
    if (!nextTokenIs(b, THE_R_LPAR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = parameter_list_0(b, l + 1);
    if (!r) r = parameter_list_1(b, l + 1);
    exit_section_(b, m, THE_R_PARAMETER_LIST, r);
    return r;
  }

  // '(' ')'
  private static boolean parameter_list_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameter_list_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, THE_R_LPAR);
    r = r && consumeToken(b, THE_R_RPAR);
    exit_section_(b, m, null, r);
    return r;
  }

  // '(' parameter nl* (',' nl* parameter nl*)* ')'
  private static boolean parameter_list_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameter_list_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, THE_R_LPAR);
    r = r && parameter(b, l + 1);
    r = r && parameter_list_1_2(b, l + 1);
    r = r && parameter_list_1_3(b, l + 1);
    r = r && consumeToken(b, THE_R_RPAR);
    exit_section_(b, m, null, r);
    return r;
  }

  // nl*
  private static boolean parameter_list_1_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameter_list_1_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "parameter_list_1_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // (',' nl* parameter nl*)*
  private static boolean parameter_list_1_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameter_list_1_3")) return false;
    int c = current_position_(b);
    while (true) {
      if (!parameter_list_1_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "parameter_list_1_3", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // ',' nl* parameter nl*
  private static boolean parameter_list_1_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameter_list_1_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, THE_R_COMMA);
    r = r && parameter_list_1_3_0_1(b, l + 1);
    r = r && parameter(b, l + 1);
    r = r && parameter_list_1_3_0_3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // nl*
  private static boolean parameter_list_1_3_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameter_list_1_3_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "parameter_list_1_3_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // nl*
  private static boolean parameter_list_1_3_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameter_list_1_3_0_3")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "parameter_list_1_3_0_3", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // expression
  public static boolean prefix_expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "prefix_expression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, "<prefix expression>");
    r = expression(b, l + 1, -1);
    exit_section_(b, l, m, THE_R_PREFIX_EXPRESSION, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // expression_list
  static boolean root(PsiBuilder b, int l) {
    return expression_list(b, l + 1);
  }

  /* ********************************************************** */
  // ';' | nl
  static boolean semicolon(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "semicolon")) return false;
    if (!nextTokenIs(b, "", THE_R_SEMI, THE_R_NL)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, THE_R_SEMI);
    if (!r) r = consumeToken(b, THE_R_NL);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // NULL | TRUE | FALSE | NA | INF | NAN | NA_INTEGER | NA_REAL | NA_COMPLEX | NA_CHARACTER
  static boolean special_constant(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "special_constant")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, THE_R_NULL);
    if (!r) r = consumeToken(b, THE_R_TRUE);
    if (!r) r = consumeToken(b, THE_R_FALSE);
    if (!r) r = consumeToken(b, THE_R_NA);
    if (!r) r = consumeToken(b, THE_R_INF);
    if (!r) r = consumeToken(b, THE_R_NAN);
    if (!r) r = consumeToken(b, THE_R_NA_INTEGER);
    if (!r) r = consumeToken(b, THE_R_NA_REAL);
    if (!r) r = consumeToken(b, THE_R_NA_COMPLEX);
    if (!r) r = consumeToken(b, THE_R_NA_CHARACTER);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // '...' | expression | external_empty_expression
  static boolean subscription_expr_elem(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "subscription_expr_elem")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, "...");
    if (!r) r = expression(b, l + 1, -1);
    if (!r) r = parseEmptyExpression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // subscription_expr_elem nl* (',' nl* subscription_expr_elem nl*)*
  static boolean subscription_expr_list(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "subscription_expr_list")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = subscription_expr_elem(b, l + 1);
    r = r && subscription_expr_list_1(b, l + 1);
    r = r && subscription_expr_list_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // nl*
  private static boolean subscription_expr_list_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "subscription_expr_list_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "subscription_expr_list_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // (',' nl* subscription_expr_elem nl*)*
  private static boolean subscription_expr_list_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "subscription_expr_list_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!subscription_expr_list_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "subscription_expr_list_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // ',' nl* subscription_expr_elem nl*
  private static boolean subscription_expr_list_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "subscription_expr_list_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, THE_R_COMMA);
    r = r && subscription_expr_list_2_0_1(b, l + 1);
    r = r && subscription_expr_elem(b, l + 1);
    r = r && subscription_expr_list_2_0_3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // nl*
  private static boolean subscription_expr_list_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "subscription_expr_list_2_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "subscription_expr_list_2_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // nl*
  private static boolean subscription_expr_list_2_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "subscription_expr_list_2_0_3")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "subscription_expr_list_2_0_3", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // Expression root: expression
  // Operator priority table:
  // 0: ATOM(if_statement)
  // 1: ATOM(while_statement)
  // 2: ATOM(for_statement)
  // 3: PREFIX(repeat_statement)
  // 4: ATOM(break_statement)
  // 5: ATOM(next_statement)
  // 6: ATOM(block_expression)
  // 7: ATOM(help_expression)
  // 8: PREFIX(parenthesized_expression)
  // 9: PREFIX(function_expression)
  // 10: BINARY(left_assign_expression)
  // 11: POSTFIX(eq_assign_expression)
  // 12: BINARY(right_assign_expression)
  // 13: PREFIX(unary_tilde_expression) BINARY(tilde_expression)
  // 14: BINARY(or_expression)
  // 15: BINARY(and_expression)
  // 16: PREFIX(unary_not_expression)
  // 17: BINARY(compare_expression)
  // 18: BINARY(plusminus_expression)
  // 19: BINARY(muldiv_expression)
  // 20: BINARY(user_defined_expression)
  // 21: BINARY(slice_expression)
  // 22: PREFIX(unary_plusminus_expression)
  // 23: BINARY(exp_expression)
  // 24: POSTFIX(subscription_expression)
  // 25: POSTFIX(call_expression)
  // 26: POSTFIX(member_expression)
  // 27: BINARY(at_expression)
  // 28: POSTFIX(namespace_access_expression)
  // 29: ATOM(reference_expression)
  // 30: ATOM(numeric_literal_expression) ATOM(string_literal_expression)
  public static boolean expression(PsiBuilder b, int l, int g) {
    if (!recursion_guard_(b, l, "expression")) return false;
    addVariant(b, "<expression>");
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, "<expression>");
    r = if_statement(b, l + 1);
    if (!r) r = while_statement(b, l + 1);
    if (!r) r = for_statement(b, l + 1);
    if (!r) r = repeat_statement(b, l + 1);
    if (!r) r = break_statement(b, l + 1);
    if (!r) r = next_statement(b, l + 1);
    if (!r) r = block_expression(b, l + 1);
    if (!r) r = help_expression(b, l + 1);
    if (!r) r = parenthesized_expression(b, l + 1);
    if (!r) r = function_expression(b, l + 1);
    if (!r) r = unary_tilde_expression(b, l + 1);
    if (!r) r = unary_not_expression(b, l + 1);
    if (!r) r = unary_plusminus_expression(b, l + 1);
    if (!r) r = reference_expression(b, l + 1);
    if (!r) r = numeric_literal_expression(b, l + 1);
    if (!r) r = string_literal_expression(b, l + 1);
    p = r;
    r = r && expression_0(b, l + 1, g);
    exit_section_(b, l, m, null, r, p, null);
    return r || p;
  }

  public static boolean expression_0(PsiBuilder b, int l, int g) {
    if (!recursion_guard_(b, l, "expression_0")) return false;
    boolean r = true;
    while (true) {
      Marker m = enter_section_(b, l, _LEFT_, null);
      if (g < 10 && left_assign_expression_0(b, l + 1)) {
        r = expression(b, l, 9);
        exit_section_(b, l, m, THE_R_ASSIGNMENT_STATEMENT, r, true, null);
      }
      else if (g < 11 && eq_assign_expression_0(b, l + 1)) {
        r = true;
        exit_section_(b, l, m, THE_R_ASSIGNMENT_STATEMENT, r, true, null);
      }
      else if (g < 12 && right_assign_expression_0(b, l + 1)) {
        r = expression(b, l, 12);
        exit_section_(b, l, m, THE_R_ASSIGNMENT_STATEMENT, r, true, null);
      }
      else if (g < 13 && tilde_expression_0(b, l + 1)) {
        r = expression(b, l, 13);
        exit_section_(b, l, m, THE_R_BINARY_EXPRESSION, r, true, null);
      }
      else if (g < 14 && or_expression_0(b, l + 1)) {
        r = expression(b, l, 14);
        exit_section_(b, l, m, THE_R_BINARY_EXPRESSION, r, true, null);
      }
      else if (g < 15 && and_expression_0(b, l + 1)) {
        r = expression(b, l, 15);
        exit_section_(b, l, m, THE_R_BINARY_EXPRESSION, r, true, null);
      }
      else if (g < 17 && compare_expression_0(b, l + 1)) {
        r = expression(b, l, 17);
        exit_section_(b, l, m, THE_R_BINARY_EXPRESSION, r, true, null);
      }
      else if (g < 18 && plusminus_expression_0(b, l + 1)) {
        r = expression(b, l, 18);
        exit_section_(b, l, m, THE_R_BINARY_EXPRESSION, r, true, null);
      }
      else if (g < 19 && muldiv_expression_0(b, l + 1)) {
        r = expression(b, l, 19);
        exit_section_(b, l, m, THE_R_BINARY_EXPRESSION, r, true, null);
      }
      else if (g < 20 && user_defined_expression_0(b, l + 1)) {
        r = expression(b, l, 20);
        exit_section_(b, l, m, THE_R_BINARY_EXPRESSION, r, true, null);
      }
      else if (g < 21 && slice_expression_0(b, l + 1)) {
        r = expression(b, l, 21);
        exit_section_(b, l, m, THE_R_SLICE_EXPRESSION, r, true, null);
      }
      else if (g < 23 && exp_expression_0(b, l + 1)) {
        r = expression(b, l, 23);
        exit_section_(b, l, m, THE_R_BINARY_EXPRESSION, r, true, null);
      }
      else if (g < 24 && subscription_expression_0(b, l + 1)) {
        r = true;
        exit_section_(b, l, m, THE_R_SUBSCRIPTION_EXPRESSION, r, true, null);
      }
      else if (g < 25 && argument_list(b, l + 1)) {
        r = true;
        exit_section_(b, l, m, THE_R_CALL_EXPRESSION, r, true, null);
      }
      else if (g < 26 && member_expression_0(b, l + 1)) {
        r = true;
        exit_section_(b, l, m, THE_R_REFERENCE_EXPRESSION, r, true, null);
      }
      else if (g < 27 && at_expression_0(b, l + 1)) {
        r = expression(b, l, 27);
        exit_section_(b, l, m, THE_R_REFERENCE_EXPRESSION, r, true, null);
      }
      else if (g < 28 && namespace_access_expression_0(b, l + 1)) {
        r = true;
        exit_section_(b, l, m, THE_R_REFERENCE_EXPRESSION, r, true, null);
      }
      else {
        exit_section_(b, l, m, null, false, false, null);
        break;
      }
    }
    return r;
  }

  // if nl* '(' nl* expression nl* ')' nl* expression (nl* else nl* expression)?
  public static boolean if_statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "if_statement")) return false;
    if (!nextTokenIsFast(b, THE_R_IF)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, THE_R_IF);
    r = r && if_statement_1(b, l + 1);
    r = r && consumeToken(b, THE_R_LPAR);
    r = r && if_statement_3(b, l + 1);
    r = r && expression(b, l + 1, -1);
    r = r && if_statement_5(b, l + 1);
    r = r && consumeToken(b, THE_R_RPAR);
    r = r && if_statement_7(b, l + 1);
    r = r && expression(b, l + 1, -1);
    r = r && if_statement_9(b, l + 1);
    exit_section_(b, m, THE_R_IF_STATEMENT, r);
    return r;
  }

  // nl*
  private static boolean if_statement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "if_statement_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeTokenSmart(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "if_statement_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // nl*
  private static boolean if_statement_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "if_statement_3")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeTokenSmart(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "if_statement_3", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // nl*
  private static boolean if_statement_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "if_statement_5")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeTokenSmart(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "if_statement_5", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // nl*
  private static boolean if_statement_7(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "if_statement_7")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeTokenSmart(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "if_statement_7", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // (nl* else nl* expression)?
  private static boolean if_statement_9(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "if_statement_9")) return false;
    if_statement_9_0(b, l + 1);
    return true;
  }

  // nl* else nl* expression
  private static boolean if_statement_9_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "if_statement_9_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = if_statement_9_0_0(b, l + 1);
    r = r && consumeToken(b, THE_R_ELSE);
    r = r && if_statement_9_0_2(b, l + 1);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  // nl*
  private static boolean if_statement_9_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "if_statement_9_0_0")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeTokenSmart(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "if_statement_9_0_0", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // nl*
  private static boolean if_statement_9_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "if_statement_9_0_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeTokenSmart(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "if_statement_9_0_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // while nl* '(' nl* expression nl* ')' nl* expression
  public static boolean while_statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "while_statement")) return false;
    if (!nextTokenIsFast(b, THE_R_WHILE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, THE_R_WHILE);
    r = r && while_statement_1(b, l + 1);
    r = r && consumeToken(b, THE_R_LPAR);
    r = r && while_statement_3(b, l + 1);
    r = r && expression(b, l + 1, -1);
    r = r && while_statement_5(b, l + 1);
    r = r && consumeToken(b, THE_R_RPAR);
    r = r && while_statement_7(b, l + 1);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, m, THE_R_WHILE_STATEMENT, r);
    return r;
  }

  // nl*
  private static boolean while_statement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "while_statement_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeTokenSmart(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "while_statement_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // nl*
  private static boolean while_statement_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "while_statement_3")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeTokenSmart(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "while_statement_3", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // nl*
  private static boolean while_statement_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "while_statement_5")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeTokenSmart(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "while_statement_5", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // nl*
  private static boolean while_statement_7(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "while_statement_7")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeTokenSmart(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "while_statement_7", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // for nl* '(' nl* expression 'in' nl* expression ')' nl* expression
  public static boolean for_statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "for_statement")) return false;
    if (!nextTokenIsFast(b, THE_R_FOR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, THE_R_FOR);
    r = r && for_statement_1(b, l + 1);
    r = r && consumeToken(b, THE_R_LPAR);
    r = r && for_statement_3(b, l + 1);
    r = r && expression(b, l + 1, -1);
    r = r && consumeToken(b, "in");
    r = r && for_statement_6(b, l + 1);
    r = r && expression(b, l + 1, -1);
    r = r && consumeToken(b, THE_R_RPAR);
    r = r && for_statement_9(b, l + 1);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, m, THE_R_FOR_STATEMENT, r);
    return r;
  }

  // nl*
  private static boolean for_statement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "for_statement_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeTokenSmart(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "for_statement_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // nl*
  private static boolean for_statement_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "for_statement_3")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeTokenSmart(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "for_statement_3", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // nl*
  private static boolean for_statement_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "for_statement_6")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeTokenSmart(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "for_statement_6", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // nl*
  private static boolean for_statement_9(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "for_statement_9")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeTokenSmart(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "for_statement_9", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  public static boolean repeat_statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "repeat_statement")) return false;
    if (!nextTokenIsFast(b, THE_R_REPEAT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = repeat_statement_0(b, l + 1);
    p = r;
    r = p && expression(b, l, 3);
    exit_section_(b, l, m, THE_R_REPEAT_STATEMENT, r, p, null);
    return r || p;
  }

  // repeat nl*
  private static boolean repeat_statement_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "repeat_statement_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, THE_R_REPEAT);
    r = r && repeat_statement_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // nl*
  private static boolean repeat_statement_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "repeat_statement_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeTokenSmart(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "repeat_statement_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // break break_next_expression?
  public static boolean break_statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "break_statement")) return false;
    if (!nextTokenIsFast(b, THE_R_BREAK)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, THE_R_BREAK);
    r = r && break_statement_1(b, l + 1);
    exit_section_(b, m, THE_R_BREAK_STATEMENT, r);
    return r;
  }

  // break_next_expression?
  private static boolean break_statement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "break_statement_1")) return false;
    break_next_expression(b, l + 1);
    return true;
  }

  // next break_next_expression?
  public static boolean next_statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "next_statement")) return false;
    if (!nextTokenIsFast(b, THE_R_NEXT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, THE_R_NEXT);
    r = r && next_statement_1(b, l + 1);
    exit_section_(b, m, THE_R_NEXT_STATEMENT, r);
    return r;
  }

  // break_next_expression?
  private static boolean next_statement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "next_statement_1")) return false;
    break_next_expression(b, l + 1);
    return true;
  }

  // '{' nl* expression_list? nl* '}'
  public static boolean block_expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "block_expression")) return false;
    if (!nextTokenIsFast(b, THE_R_LBRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, THE_R_LBRACE);
    r = r && block_expression_1(b, l + 1);
    r = r && block_expression_2(b, l + 1);
    r = r && block_expression_3(b, l + 1);
    r = r && consumeToken(b, THE_R_RBRACE);
    exit_section_(b, m, THE_R_BLOCK_EXPRESSION, r);
    return r;
  }

  // nl*
  private static boolean block_expression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "block_expression_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeTokenSmart(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "block_expression_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // expression_list?
  private static boolean block_expression_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "block_expression_2")) return false;
    expression_list(b, l + 1);
    return true;
  }

  // nl*
  private static boolean block_expression_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "block_expression_3")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeTokenSmart(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "block_expression_3", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // help (nl* help)? nl* (keyword | expression)
  public static boolean help_expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "help_expression")) return false;
    if (!nextTokenIsFast(b, THE_R_HELP)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, THE_R_HELP);
    r = r && help_expression_1(b, l + 1);
    r = r && help_expression_2(b, l + 1);
    r = r && help_expression_3(b, l + 1);
    exit_section_(b, m, THE_R_HELP_EXPRESSION, r);
    return r;
  }

  // (nl* help)?
  private static boolean help_expression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "help_expression_1")) return false;
    help_expression_1_0(b, l + 1);
    return true;
  }

  // nl* help
  private static boolean help_expression_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "help_expression_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = help_expression_1_0_0(b, l + 1);
    r = r && consumeToken(b, THE_R_HELP);
    exit_section_(b, m, null, r);
    return r;
  }

  // nl*
  private static boolean help_expression_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "help_expression_1_0_0")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeTokenSmart(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "help_expression_1_0_0", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // nl*
  private static boolean help_expression_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "help_expression_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeTokenSmart(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "help_expression_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // keyword | expression
  private static boolean help_expression_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "help_expression_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = keyword(b, l + 1);
    if (!r) r = expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  public static boolean parenthesized_expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parenthesized_expression")) return false;
    if (!nextTokenIsFast(b, THE_R_LPAR)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = parenthesized_expression_0(b, l + 1);
    p = r;
    r = p && expression(b, l, 8);
    r = p && report_error_(b, parenthesized_expression_1(b, l + 1)) && r;
    exit_section_(b, l, m, THE_R_PARENTHESIZED_EXPRESSION, r, p, null);
    return r || p;
  }

  // '(' nl*
  private static boolean parenthesized_expression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parenthesized_expression_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, THE_R_LPAR);
    r = r && parenthesized_expression_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // nl*
  private static boolean parenthesized_expression_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parenthesized_expression_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeTokenSmart(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "parenthesized_expression_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // nl* ')'
  private static boolean parenthesized_expression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parenthesized_expression_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = parenthesized_expression_1_0(b, l + 1);
    r = r && consumeToken(b, THE_R_RPAR);
    exit_section_(b, m, null, r);
    return r;
  }

  // nl*
  private static boolean parenthesized_expression_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parenthesized_expression_1_0")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeToken(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "parenthesized_expression_1_0", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  public static boolean function_expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "function_expression")) return false;
    if (!nextTokenIsFast(b, THE_R_FUNCTION)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = function_expression_0(b, l + 1);
    p = r;
    r = p && expression(b, l, 9);
    exit_section_(b, l, m, THE_R_FUNCTION_EXPRESSION, r, p, null);
    return r || p;
  }

  // function parameter_list nl*
  private static boolean function_expression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "function_expression_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, THE_R_FUNCTION);
    r = r && parameter_list(b, l + 1);
    r = r && function_expression_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // nl*
  private static boolean function_expression_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "function_expression_0_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeTokenSmart(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "function_expression_0_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // ('<-' | '<<-') nl*
  private static boolean left_assign_expression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "left_assign_expression_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = left_assign_expression_0_0(b, l + 1);
    r = r && left_assign_expression_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // '<-' | '<<-'
  private static boolean left_assign_expression_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "left_assign_expression_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, THE_R_LEFT_ASSIGN);
    if (!r) r = consumeTokenSmart(b, THE_R_LEFT_COMPLEX_ASSIGN);
    exit_section_(b, m, null, r);
    return r;
  }

  // nl*
  private static boolean left_assign_expression_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "left_assign_expression_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeTokenSmart(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "left_assign_expression_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // '=' nl* (expression | external_empty_expression)
  private static boolean eq_assign_expression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "eq_assign_expression_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, THE_R_EQ);
    r = r && eq_assign_expression_0_1(b, l + 1);
    r = r && eq_assign_expression_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // nl*
  private static boolean eq_assign_expression_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "eq_assign_expression_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeTokenSmart(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "eq_assign_expression_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // expression | external_empty_expression
  private static boolean eq_assign_expression_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "eq_assign_expression_0_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = expression(b, l + 1, -1);
    if (!r) r = parseEmptyExpression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ('->' | '->>') nl*
  private static boolean right_assign_expression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "right_assign_expression_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = right_assign_expression_0_0(b, l + 1);
    r = r && right_assign_expression_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // '->' | '->>'
  private static boolean right_assign_expression_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "right_assign_expression_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, THE_R_RIGHT_ASSIGN);
    if (!r) r = consumeTokenSmart(b, THE_R_RIGHT_COMPLEX_ASSIGN);
    exit_section_(b, m, null, r);
    return r;
  }

  // nl*
  private static boolean right_assign_expression_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "right_assign_expression_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeTokenSmart(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "right_assign_expression_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  public static boolean unary_tilde_expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unary_tilde_expression")) return false;
    if (!nextTokenIsFast(b, THE_R_TILDE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = unary_tilde_expression_0(b, l + 1);
    p = r;
    r = p && expression(b, l, 13);
    exit_section_(b, l, m, THE_R_PREFIX_EXPRESSION, r, p, null);
    return r || p;
  }

  // '~' nl*
  private static boolean unary_tilde_expression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unary_tilde_expression_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, THE_R_TILDE);
    r = r && unary_tilde_expression_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // nl*
  private static boolean unary_tilde_expression_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unary_tilde_expression_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeTokenSmart(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "unary_tilde_expression_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // '~' nl*
  private static boolean tilde_expression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tilde_expression_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, THE_R_TILDE);
    r = r && tilde_expression_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // nl*
  private static boolean tilde_expression_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tilde_expression_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeTokenSmart(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "tilde_expression_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // ('|' | '||') nl*
  private static boolean or_expression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "or_expression_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = or_expression_0_0(b, l + 1);
    r = r && or_expression_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // '|' | '||'
  private static boolean or_expression_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "or_expression_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, THE_R_OR);
    if (!r) r = consumeTokenSmart(b, THE_R_OROR);
    exit_section_(b, m, null, r);
    return r;
  }

  // nl*
  private static boolean or_expression_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "or_expression_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeTokenSmart(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "or_expression_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // ('&' | '&&') nl*
  private static boolean and_expression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "and_expression_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = and_expression_0_0(b, l + 1);
    r = r && and_expression_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // '&' | '&&'
  private static boolean and_expression_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "and_expression_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, THE_R_AND);
    if (!r) r = consumeTokenSmart(b, THE_R_ANDAND);
    exit_section_(b, m, null, r);
    return r;
  }

  // nl*
  private static boolean and_expression_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "and_expression_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeTokenSmart(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "and_expression_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  public static boolean unary_not_expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unary_not_expression")) return false;
    if (!nextTokenIsFast(b, THE_R_NOT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = unary_not_expression_0(b, l + 1);
    p = r;
    r = p && expression(b, l, 16);
    exit_section_(b, l, m, THE_R_PREFIX_EXPRESSION, r, p, null);
    return r || p;
  }

  // '!' nl*
  private static boolean unary_not_expression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unary_not_expression_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, THE_R_NOT);
    r = r && unary_not_expression_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // nl*
  private static boolean unary_not_expression_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unary_not_expression_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeTokenSmart(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "unary_not_expression_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // ('>' | '>=' | '<' | '<=' | '==' | '!=') nl*
  private static boolean compare_expression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "compare_expression_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = compare_expression_0_0(b, l + 1);
    r = r && compare_expression_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // '>' | '>=' | '<' | '<=' | '==' | '!='
  private static boolean compare_expression_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "compare_expression_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, THE_R_GT);
    if (!r) r = consumeTokenSmart(b, THE_R_GE);
    if (!r) r = consumeTokenSmart(b, THE_R_LT);
    if (!r) r = consumeTokenSmart(b, THE_R_LE);
    if (!r) r = consumeTokenSmart(b, THE_R_EQEQ);
    if (!r) r = consumeTokenSmart(b, THE_R_NOTEQ);
    exit_section_(b, m, null, r);
    return r;
  }

  // nl*
  private static boolean compare_expression_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "compare_expression_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeTokenSmart(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "compare_expression_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // ('+' | '-') nl*
  private static boolean plusminus_expression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "plusminus_expression_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = plusminus_expression_0_0(b, l + 1);
    r = r && plusminus_expression_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // '+' | '-'
  private static boolean plusminus_expression_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "plusminus_expression_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, THE_R_PLUS);
    if (!r) r = consumeTokenSmart(b, THE_R_MINUS);
    exit_section_(b, m, null, r);
    return r;
  }

  // nl*
  private static boolean plusminus_expression_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "plusminus_expression_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeTokenSmart(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "plusminus_expression_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // ('*' | '/') nl*
  private static boolean muldiv_expression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "muldiv_expression_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = muldiv_expression_0_0(b, l + 1);
    r = r && muldiv_expression_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // '*' | '/'
  private static boolean muldiv_expression_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "muldiv_expression_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, THE_R_MULT);
    if (!r) r = consumeTokenSmart(b, THE_R_DIV);
    exit_section_(b, m, null, r);
    return r;
  }

  // nl*
  private static boolean muldiv_expression_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "muldiv_expression_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeTokenSmart(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "muldiv_expression_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // (INFIX_OP | MODULUS | INT_DIV | MATRIX_PROD | OUTER_PROD | MATCHING | KRONECKER_PROD) nl*
  private static boolean user_defined_expression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "user_defined_expression_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = user_defined_expression_0_0(b, l + 1);
    r = r && user_defined_expression_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // INFIX_OP | MODULUS | INT_DIV | MATRIX_PROD | OUTER_PROD | MATCHING | KRONECKER_PROD
  private static boolean user_defined_expression_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "user_defined_expression_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, THE_R_INFIX_OP);
    if (!r) r = consumeTokenSmart(b, THE_R_MODULUS);
    if (!r) r = consumeTokenSmart(b, THE_R_INT_DIV);
    if (!r) r = consumeTokenSmart(b, THE_R_MATRIX_PROD);
    if (!r) r = consumeTokenSmart(b, THE_R_OUTER_PROD);
    if (!r) r = consumeTokenSmart(b, THE_R_MATCHING);
    if (!r) r = consumeTokenSmart(b, THE_R_KRONECKER_PROD);
    exit_section_(b, m, null, r);
    return r;
  }

  // nl*
  private static boolean user_defined_expression_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "user_defined_expression_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeTokenSmart(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "user_defined_expression_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // ':' nl*
  private static boolean slice_expression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "slice_expression_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, THE_R_COLON);
    r = r && slice_expression_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // nl*
  private static boolean slice_expression_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "slice_expression_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeTokenSmart(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "slice_expression_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  public static boolean unary_plusminus_expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unary_plusminus_expression")) return false;
    if (!nextTokenIsFast(b, THE_R_PLUS, THE_R_MINUS)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = unary_plusminus_expression_0(b, l + 1);
    p = r;
    r = p && expression(b, l, 22);
    exit_section_(b, l, m, THE_R_PREFIX_EXPRESSION, r, p, null);
    return r || p;
  }

  // ('+' | '-') nl*
  private static boolean unary_plusminus_expression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unary_plusminus_expression_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = unary_plusminus_expression_0_0(b, l + 1);
    r = r && unary_plusminus_expression_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // '+' | '-'
  private static boolean unary_plusminus_expression_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unary_plusminus_expression_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, THE_R_PLUS);
    if (!r) r = consumeTokenSmart(b, THE_R_MINUS);
    exit_section_(b, m, null, r);
    return r;
  }

  // nl*
  private static boolean unary_plusminus_expression_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unary_plusminus_expression_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeTokenSmart(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "unary_plusminus_expression_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // '^' nl*
  private static boolean exp_expression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "exp_expression_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, THE_R_EXP);
    r = r && exp_expression_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // nl*
  private static boolean exp_expression_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "exp_expression_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeTokenSmart(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "exp_expression_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // '[' nl* ']' | '[' nl* subscription_expr_list ']' |
  //   '[[' nl* ']]' | '[[' nl* subscription_expr_list ']]'
  private static boolean subscription_expression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "subscription_expression_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = subscription_expression_0_0(b, l + 1);
    if (!r) r = subscription_expression_0_1(b, l + 1);
    if (!r) r = subscription_expression_0_2(b, l + 1);
    if (!r) r = subscription_expression_0_3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // '[' nl* ']'
  private static boolean subscription_expression_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "subscription_expression_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, THE_R_LBRACKET);
    r = r && subscription_expression_0_0_1(b, l + 1);
    r = r && consumeToken(b, THE_R_RBRACKET);
    exit_section_(b, m, null, r);
    return r;
  }

  // nl*
  private static boolean subscription_expression_0_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "subscription_expression_0_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeTokenSmart(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "subscription_expression_0_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // '[' nl* subscription_expr_list ']'
  private static boolean subscription_expression_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "subscription_expression_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, THE_R_LBRACKET);
    r = r && subscription_expression_0_1_1(b, l + 1);
    r = r && subscription_expr_list(b, l + 1);
    r = r && consumeToken(b, THE_R_RBRACKET);
    exit_section_(b, m, null, r);
    return r;
  }

  // nl*
  private static boolean subscription_expression_0_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "subscription_expression_0_1_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeTokenSmart(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "subscription_expression_0_1_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // '[[' nl* ']]'
  private static boolean subscription_expression_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "subscription_expression_0_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, THE_R_LDBRACKET);
    r = r && subscription_expression_0_2_1(b, l + 1);
    r = r && consumeToken(b, THE_R_RDBRACKET);
    exit_section_(b, m, null, r);
    return r;
  }

  // nl*
  private static boolean subscription_expression_0_2_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "subscription_expression_0_2_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeTokenSmart(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "subscription_expression_0_2_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // '[[' nl* subscription_expr_list ']]'
  private static boolean subscription_expression_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "subscription_expression_0_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, THE_R_LDBRACKET);
    r = r && subscription_expression_0_3_1(b, l + 1);
    r = r && subscription_expr_list(b, l + 1);
    r = r && consumeToken(b, THE_R_RDBRACKET);
    exit_section_(b, m, null, r);
    return r;
  }

  // nl*
  private static boolean subscription_expression_0_3_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "subscription_expression_0_3_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeTokenSmart(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "subscription_expression_0_3_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // '$' nl* member_tag
  private static boolean member_expression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "member_expression_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, THE_R_LIST_SUBSET);
    r = r && member_expression_0_1(b, l + 1);
    r = r && member_tag(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // nl*
  private static boolean member_expression_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "member_expression_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeTokenSmart(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "member_expression_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // '@' nl*
  private static boolean at_expression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "at_expression_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, THE_R_AT);
    r = r && at_expression_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // nl*
  private static boolean at_expression_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "at_expression_0_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!consumeTokenSmart(b, THE_R_NL)) break;
      if (!empty_element_parsed_guard_(b, "at_expression_0_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // ('::' | ':::') identifier
  private static boolean namespace_access_expression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "namespace_access_expression_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = namespace_access_expression_0_0(b, l + 1);
    r = r && consumeToken(b, THE_R_IDENTIFIER);
    exit_section_(b, m, null, r);
    return r;
  }

  // '::' | ':::'
  private static boolean namespace_access_expression_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "namespace_access_expression_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, THE_R_DOUBLECOLON);
    if (!r) r = consumeTokenSmart(b, THE_R_TRIPLECOLON);
    exit_section_(b, m, null, r);
    return r;
  }

  // identifier | special_constant
  public static boolean reference_expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "reference_expression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<reference expression>");
    r = consumeTokenSmart(b, THE_R_IDENTIFIER);
    if (!r) r = special_constant(b, l + 1);
    exit_section_(b, l, m, THE_R_REFERENCE_EXPRESSION, r, false, null);
    return r;
  }

  // integer | numeric | complex
  public static boolean numeric_literal_expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "numeric_literal_expression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, "<numeric literal expression>");
    r = consumeTokenSmart(b, THE_R_INTEGER);
    if (!r) r = consumeTokenSmart(b, THE_R_NUMERIC);
    if (!r) r = consumeTokenSmart(b, THE_R_COMPLEX);
    exit_section_(b, l, m, THE_R_NUMERIC_LITERAL_EXPRESSION, r, false, null);
    return r;
  }

  // string
  public static boolean string_literal_expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "string_literal_expression")) return false;
    if (!nextTokenIsFast(b, THE_R_STRING)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, THE_R_STRING);
    exit_section_(b, m, THE_R_STRING_LITERAL_EXPRESSION, r);
    return r;
  }

}
