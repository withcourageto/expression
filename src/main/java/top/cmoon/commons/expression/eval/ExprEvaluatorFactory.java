package top.cmoon.commons.expression.eval;

import top.cmoon.commons.expression.token.Token;

import java.util.List;

public interface ExprEvaluatorFactory {

    ExprEvaluator createExprEvaluator(List<Token> suffixExpr);

}
