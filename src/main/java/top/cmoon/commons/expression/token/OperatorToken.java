package top.cmoon.commons.expression.token;

import top.cmoon.commons.expression.eval.EvaluationContext;

public interface OperatorToken extends Token {

    /**
     * @return
     */
    OperatorType operatorType();

    /**
     * 当 #operatorType 是 OperatorType.BINARY ,调用此方式求值
     *
     * @param operand1
     * @param operand2
     * @param evalContext
     * @return
     */
    OperandToken cal(OperandToken operand1, OperandToken operand2, EvaluationContext evalContext);

    /**
     * 当 #operatorType 是 OperatorType.UNITARY ,调用此方法求值
     *
     * @param operand
     * @param evalContext
     * @return
     */
    OperandToken cal(OperandToken operand, EvaluationContext evalContext);

    /**
     * 优先级
     *
     * @return
     */
    int getPriority();
}
