package top.cmoon.commons.expression.token.operand;

import top.cmoon.commons.expression.eval.EvaluationContext;
import top.cmoon.commons.expression.exception.EvaluationException;
import top.cmoon.commons.expression.token.AbstractOperandToken;
import top.cmoon.commons.expression.token.constant.TokenCodeConst;

public class VariableToken extends AbstractOperandToken {

    public VariableToken() {
    }

    public VariableToken(String token) {
        super(TokenCodeConst.VARIABLE, token);
    }

    @Override
    public Object getVal(EvaluationContext evalContext) {

        if (evalContext == null) {
            throw new EvaluationException("表达式包含变量:" + token + "，求值上下文不能为null");
        }

        return evalContext.getVar(token);
    }
}
