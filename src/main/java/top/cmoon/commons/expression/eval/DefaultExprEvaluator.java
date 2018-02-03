package top.cmoon.commons.expression.eval;

import top.cmoon.commons.expression.exception.GrammarException;
import top.cmoon.commons.expression.token.*;
import top.cmoon.commons.expression.token.operator.EvalOperatorToken;

import java.util.List;
import java.util.Map;
import java.util.Stack;

public class DefaultExprEvaluator implements ExprEvaluator {

    private List<Token> sufExp;

    public DefaultExprEvaluator(List<Token> suffixExpr) {
        this.sufExp = suffixExpr;
    }

    /**

     * 使用栈运算方式来计算后缀表达式的值
     *
     * @param evalContext
     * @return
     */
    private Object cal(EvaluationContext evalContext) {
        Stack<Token> valStack = new Stack<>();
        for (int i = 0; i < sufExp.size(); i++) {
            Token token = sufExp.get(i);
            if (token.tokenType() == TokenType.OPERAND) {
                valStack.push(token);
            } else {

                EvalOperatorToken operatorToken = (EvalOperatorToken) token;
                if (operatorToken.operatorType() == OperatorType.BINARY) {
                    OperandToken operand2 = (OperandToken) valStack.pop();
                    OperandToken operand1 = (OperandToken) valStack.pop();
                    Token result = operatorToken.cal(operand1, operand2, evalContext);
                    valStack.push(result);
                } else if (operatorToken.operatorType() == OperatorType.UNITARY) {
                    OperandToken operand = (OperandToken) valStack.pop();
                    valStack.push(operatorToken.cal(operand, evalContext));
                } else {
                    throw new GrammarException("暂时不支持其他类型的运算符：" + operatorToken.operatorType().name());
                }
            }
        }

        OperandToken val = (OperandToken) valStack.pop();

        return val.getVal(evalContext);
    }

    @Override
    public Object getVal() {
        return getVal((EvaluationContext) null);
    }

    @Override
    public Object getVal(Map<String, Object> params) {
        return getVal(varName -> params.get(varName));
    }

    @Override
    public Object getVal(EvaluationContext evalContext) {

        return cal(evalContext);
    }
}
