package top.cmoon.commons.expression.suffixexpr;


import top.cmoon.commons.expression.token.Token;

import java.util.List;

public interface SuffixExprParser {

    List<Token> toSuffixExpr(List<Token> positiveTokens);

}
