package top.cmoon.commons.expression.token.operator;

import top.cmoon.commons.expression.eval.EvaluationContext;
import top.cmoon.commons.expression.exception.GrammarException;
import top.cmoon.commons.expression.token.AbstractBinaryOperatorToken;
import top.cmoon.commons.expression.token.OperandToken;
import top.cmoon.commons.expression.token.constant.TokenCodeConst;

public class GreaterThanToken extends AbstractBinaryOperatorToken {


    public GreaterThanToken() {
        super(TokenCodeConst.GREATER_THAN, ">");
    }

    @Override
    public OperandToken cal(OperandToken operand1, OperandToken operand2, EvaluationContext evalContext) {

        if (operand1 == null) {
            throw new RuntimeException("大于操作符缺失操作数 operand1");
        }
        if (operand2 == null) {
            throw new RuntimeException("大于操作符缺失操作数 operand2");
        }

        Object val1 = operand1.getVal(evalContext);
        Object val2 = operand2.getVal(evalContext);

        if (!(val1 instanceof Number) || !(val2 instanceof Number)) {
            throw new GrammarException("大于操作符的操作数只能是数字，实际值:" + val1 + "," + val2);
        }

        return toBooleanToken(((Number) val1).doubleValue() > ((Number) val2).doubleValue());
    }

}
