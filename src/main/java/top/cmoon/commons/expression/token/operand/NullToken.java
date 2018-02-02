package top.cmoon.commons.expression.token.operand;

import top.cmoon.commons.expression.eval.EvaluationContext;
import top.cmoon.commons.expression.token.AbstractOperandToken;
import top.cmoon.commons.expression.token.constant.TokenCodeConst;

public class NullToken extends AbstractOperandToken<Object> {

    public NullToken() {
        super(TokenCodeConst.LITERAL_NULL, "null");
    }


    @Override
    public Object getVal(EvaluationContext evalContext) {
        return null;
    }
}
