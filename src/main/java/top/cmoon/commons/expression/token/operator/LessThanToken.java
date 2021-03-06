package top.cmoon.commons.expression.token.operator;

import top.cmoon.commons.expression.eval.EvaluationContext;
import top.cmoon.commons.expression.exception.GrammarException;
import top.cmoon.commons.expression.token.AbstractBinaryOperatorToken;
import top.cmoon.commons.expression.token.OperandToken;
import top.cmoon.commons.expression.token.constant.TokenCodeConst;

public class LessThanToken extends AbstractBinaryOperatorToken<Number, Number> {

    public LessThanToken() {
        super(TokenCodeConst.LESS_THAN, "<");
    }

    @Override
    public OperandToken cal(OperandToken<Number> operand1, OperandToken<Number> operand2, EvaluationContext evalContext) {

        assert operand1 != null && operand2 != null;


        Number val1 = operand1.getVal(evalContext);
        Number val2 = operand2.getVal(evalContext);

        assert val1 != null && val2 != null;

        return toBooleanToken(val1.doubleValue() < val2.doubleValue());
    }

}
