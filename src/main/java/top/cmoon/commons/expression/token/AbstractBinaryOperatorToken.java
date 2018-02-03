package top.cmoon.commons.expression.token;

import top.cmoon.commons.expression.eval.EvaluationContext;
import top.cmoon.commons.expression.token.operand.BooleanToken;
import top.cmoon.commons.expression.token.operator.EvalOperatorToken;
import top.cmoon.commons.expression.tool.PrioritySupplier;

import static top.cmoon.commons.expression.token.operand.BooleanToken.FALSE;
import static top.cmoon.commons.expression.token.operand.BooleanToken.TRUE;

public abstract class AbstractBinaryOperatorToken<O1, O2> extends AbstractOperatorToken implements EvalOperatorToken<O1, O2> {


    public AbstractBinaryOperatorToken() {
    }

    public AbstractBinaryOperatorToken(int code, String token) {
        super(code, token);
    }

    @Override
    public OperatorType operatorType() {
        return OperatorType.BINARY;
    }

    public final OperandToken cal(OperandToken operand, EvaluationContext evalContext) {
        throw new UnsupportedOperationException();
    }


    protected BooleanToken toBooleanToken(boolean bool) {
        if (bool) {
            return TRUE;
        } else {
            return FALSE;
        }
    }
}
