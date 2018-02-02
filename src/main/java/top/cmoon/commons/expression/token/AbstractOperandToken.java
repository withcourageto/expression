package top.cmoon.commons.expression.token;

public abstract class AbstractOperandToken<V> extends AbstractToken implements OperandToken<V> {


    protected AbstractOperandToken(int code, String token) {
        super(code, token);
    }


    protected AbstractOperandToken() {
    }

    @Override
    public TokenType tokenType() {
        return TokenType.OPERAND;
    }

}
