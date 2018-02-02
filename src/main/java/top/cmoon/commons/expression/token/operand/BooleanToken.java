package top.cmoon.commons.expression.token.operand;

import top.cmoon.commons.expression.eval.EvaluationContext;
import top.cmoon.commons.expression.token.AbstractOperandToken;
import top.cmoon.commons.expression.token.constant.TokenCodeConst;

public class BooleanToken extends AbstractOperandToken<Boolean> {

    public static final BooleanToken TRUE = new BooleanToken(TokenCodeConst.BOOLEAN_LITERAL_TRUE, "true");
    public static final BooleanToken FALSE = new BooleanToken(TokenCodeConst.BOOLEAN_LITERAL_FALSE, "false");

    private BooleanToken(int type, String token) {
        super(type, token);
    }

    public BooleanToken() {
    }


    @Override
    public Boolean getVal(EvaluationContext evalContext) {

        if (!"true".equals(token) && !"false".equals(token)) {
            throw new RuntimeException("不正确的boolean字符串token，只能是true或者false,实际值：" + token);
        }

        return Boolean.valueOf(token);
    }

}
