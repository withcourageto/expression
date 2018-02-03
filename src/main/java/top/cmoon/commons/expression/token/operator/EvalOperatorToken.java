package top.cmoon.commons.expression.token.operator;

import top.cmoon.commons.expression.eval.EvaluationContext;
import top.cmoon.commons.expression.token.OperandToken;
import top.cmoon.commons.expression.token.OperatorToken;

/**
 * Created by cool_moon on 2018/2/3.
 */
public interface EvalOperatorToken<O1, O2> extends OperatorToken {


    /**
     * 当 #operatorType 是 OperatorType.BINARY ,调用此方式求值
     *
     * @param operand1
     * @param operand2
     * @param evalContext
     * @return
     */
    OperandToken cal(OperandToken<O1> operand1, OperandToken<O2> operand2, EvaluationContext evalContext);

    /**
     * 当 #operatorType 是 OperatorType.UNITARY ,调用此方法求值
     *
     * @param operand
     * @param evalContext
     * @return
     */
    OperandToken cal(OperandToken operand, EvaluationContext evalContext);


}
