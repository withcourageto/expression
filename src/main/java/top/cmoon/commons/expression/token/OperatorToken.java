package top.cmoon.commons.expression.token;

public interface OperatorToken extends Token {

    /**
     * @return
     */
    OperatorType operatorType();


    /**
     * 优先级
     *
     * @return
     */
    int getPriority();
}
