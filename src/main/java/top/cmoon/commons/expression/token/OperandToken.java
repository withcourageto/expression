package top.cmoon.commons.expression.token;

import top.cmoon.commons.expression.eval.EvaluationContext;
import top.cmoon.commons.expression.exception.EvaluationException;

/**
 * @param <V> 操作数的值类型
 */
public interface OperandToken<V> extends Token {

    /**
     * @param evalContext 求值上下文，只有变量 token 的时候才有用
     * @return
     * @throws EvaluationException 当求值发生错误的时候，例如 VariableToken 求值没有传入 evalContext
     */
    V getVal(EvaluationContext evalContext) throws EvaluationException;




}
