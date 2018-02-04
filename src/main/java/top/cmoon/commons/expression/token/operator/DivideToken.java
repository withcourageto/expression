package top.cmoon.commons.expression.token.operator;

import top.cmoon.commons.expression.eval.EvaluationContext;
import top.cmoon.commons.expression.token.AbstractBinaryOperatorToken;
import top.cmoon.commons.expression.token.OperandToken;
import top.cmoon.commons.expression.token.operand.FloatNumToken;
import top.cmoon.commons.expression.token.operand.IntNumToken;

/**
 * Created by cool_moon on 2018/2/3.
 */
public class DivideToken extends AbstractBinaryOperatorToken<Number, Number> {


    public DivideToken() {
    }

    public DivideToken(int code, String token) {
        super(code, token);
    }

    @Override
    public OperandToken cal(OperandToken<Number> operand1, OperandToken<Number> operand2, EvaluationContext evalContext) {
        assert operand1 != null && operand2 != null;

        Number val1 = operand1.getVal(evalContext);
        Number val2 = operand2.getVal(evalContext);

        assert val1 != null && val2 != null;

        if (val1 instanceof Double || val2 instanceof Double) {
            return new FloatNumToken(val1.doubleValue() / val2.doubleValue() + "");
        } else {
            return new IntNumToken(val1.intValue() / val2.intValue() + "");
        }
    }
}
