package top.cmoon.commons.expression.token.operand;

import top.cmoon.commons.expression.eval.EvaluationContext;
import top.cmoon.commons.expression.token.AbstractOperandToken;
import top.cmoon.commons.expression.token.constant.TokenCodeConst;

public class IntNumToken extends AbstractOperandToken<Integer> {

    public IntNumToken() {
    }

    public IntNumToken(String token) {
        super(TokenCodeConst.INT_NUMBER, token);
    }

    @Override
    public Integer getVal(EvaluationContext evalContext) {
        return Integer.valueOf(token);
    }
}
