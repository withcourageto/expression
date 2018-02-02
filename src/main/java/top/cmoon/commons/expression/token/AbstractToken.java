package top.cmoon.commons.expression.token;

public abstract class AbstractToken implements Token {

    // 设置为保护变量，是方便子类读取，请不要修改
    protected int code;
    protected String token;

    public AbstractToken(int code, String token) {
        this.code = code;
        this.token = token;
    }

    public AbstractToken() {
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String token() {
        return token;
    }


    @Override
    public String toString() {
        return token + ":" + code;
    }

    @Override
    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public void setToken(String token) {
        this.token = token;
    }
}
