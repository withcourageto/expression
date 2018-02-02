package top.cmoon.commons.expression.token.operand;

import top.cmoon.commons.expression.eval.EvaluationContext;
import top.cmoon.commons.expression.token.AbstractOperandToken;
import top.cmoon.commons.expression.token.constant.TokenCodeConst;

public class FloatNumToken extends AbstractOperandToken<Double> {

    public FloatNumToken(double value) {
        super(TokenCodeConst.FLOAT_NUMBER, value + "");
    }

    public FloatNumToken() {
    }


    @Override
    public Double getVal(EvaluationContext evalContext) {
        return Double.valueOf(token);
    }
}
