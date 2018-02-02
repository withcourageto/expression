package top.cmoon.commons.expression.token.operator;

import top.cmoon.commons.expression.eval.EvaluationContext;
import top.cmoon.commons.expression.token.AbstractBinaryOperatorToken;
import top.cmoon.commons.expression.token.OperandToken;
import top.cmoon.commons.expression.token.constant.TokenCodeConst;

public class LessThanToken extends AbstractBinaryOperatorToken {

    public LessThanToken() {
        super(TokenCodeConst.LESS_THAN, "<");
    }

    @Override
    public OperandToken cal(OperandToken operand1, OperandToken operand2, EvaluationContext evalContext) {
        return null;
    }

}
