package top.cmoon.commons.expression.eval;

import top.cmoon.commons.expression.token.Token;

import java.util.List;

public class DefaultExprEvaluatorFactory implements ExprEvaluatorFactory {
    @Override
    public ExprEvaluator createExprEvaluator(List<Token> suffixExpr) {
        return new DefaultExprEvaluator(suffixExpr);
    }
}
