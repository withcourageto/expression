package top.cmoon.commons.expression.token.operand;

import top.cmoon.commons.expression.eval.EvaluationContext;
import top.cmoon.commons.expression.token.AbstractOperandToken;
import top.cmoon.commons.expression.token.constant.TokenCodeConst;

public class StringLiteralToken extends AbstractOperandToken<String> {


    public StringLiteralToken() {
    }

    public StringLiteralToken(String token) {
        super(TokenCodeConst.STRING_LITERAL, token);
    }

    @Override
    public String getVal(EvaluationContext evalContext) {
        return new String(token);
    }
}
