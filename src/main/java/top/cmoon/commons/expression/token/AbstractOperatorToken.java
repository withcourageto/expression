package top.cmoon.commons.expression.token;

import top.cmoon.commons.expression.tool.PrioritySupplier;

/**
 * Created by cool_moon on 2018/2/3.
 */
public abstract class AbstractOperatorToken extends AbstractToken implements OperatorToken {
    public AbstractOperatorToken() {
    }

    public AbstractOperatorToken(int code, String token) {
        super(code, token);
    }

    @Override
    public TokenType tokenType() {
        return TokenType.OPERATOR;
    }


    @Override
    public int getPriority() {
        return PrioritySupplier.getPriority(code);
    }


}
