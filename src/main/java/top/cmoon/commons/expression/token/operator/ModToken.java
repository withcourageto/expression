package top.cmoon.commons.expression.token.operator;

import top.cmoon.commons.expression.eval.EvaluationContext;
import top.cmoon.commons.expression.exception.EvaluationException;
import top.cmoon.commons.expression.token.AbstractBinaryOperatorToken;
import top.cmoon.commons.expression.token.OperandToken;
import top.cmoon.commons.expression.token.operand.FloatNumToken;
import top.cmoon.commons.expression.token.operand.IntNumToken;

/**
 * Created by cool_moon on 2018/2/3.
 */
public class ModToken extends AbstractBinaryOperatorToken<Number, Number> {

    public ModToken() {
    }

    public ModToken(int code, String token) {
        super(code, token);
    }

    @Override
    public OperandToken cal(OperandToken<Number> operand1, OperandToken<Number> operand2, EvaluationContext evalContext) {

        if (operand1 == null) {
            throw new RuntimeException(" % 操作符缺失操作数 operand1");
        }

        if (operand2 == null) {
            throw new RuntimeException(" % 操作符缺失操作数 operand2");
        }

        Number val1 = operand1.getVal(evalContext);
        Number val2 = operand2.getVal(evalContext);

        if (val1 == null || val2 == null) {
            throw new EvaluationException(" % 操作符的操作数不能为空：" + operand1.token() + "," + operand2.token());
        }

        if (val1 instanceof Double || val2 instanceof Double) {
            return new FloatNumToken(val1.doubleValue() % val2.doubleValue() + "");
        } else {
            return new IntNumToken(val1.intValue() % val2.intValue() + "");
        }
    }
}
