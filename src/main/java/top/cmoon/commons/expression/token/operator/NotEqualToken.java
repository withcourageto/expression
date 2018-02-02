package top.cmoon.commons.expression.token.operator;

import top.cmoon.commons.expression.eval.EvaluationContext;
import top.cmoon.commons.expression.token.AbstractBinaryOperatorToken;
import top.cmoon.commons.expression.token.OperandToken;
import top.cmoon.commons.expression.token.OperatorType;
import top.cmoon.commons.expression.token.constant.TokenCodeConst;
import top.cmoon.commons.expression.token.operand.BooleanToken;

import static top.cmoon.commons.expression.token.operand.BooleanToken.FALSE;
import static top.cmoon.commons.expression.token.operand.BooleanToken.TRUE;

public class NotEqualToken extends AbstractBinaryOperatorToken {

    public NotEqualToken() {
        super(TokenCodeConst.NOT_EQUAL, "!=");
    }

    @Override
    public OperatorType operatorType() {
        return OperatorType.BINARY;
    }

    @Override
    public OperandToken cal(OperandToken operand1, OperandToken operand2, EvaluationContext evalContext) {

        if (operand1 == null) {
            throw new RuntimeException("不等于操作符缺失操作数 operand1");
        }

        if (operand2 == null) {
            throw new RuntimeException("不等于操作符缺失操作数 operand2");
        }

        Object val1 = operand1.getVal(evalContext);
        Object val2 = operand2.getVal(evalContext);

        if (val1 == val2) {
            return FALSE;
        }

        if (val1 == null && val2 != null) {
            return TRUE;
        }
        if (val1 != null && val2 == null) {
            return TRUE;
        }

        return toBooleanToken(!val1.equals(val2));
    }
}
