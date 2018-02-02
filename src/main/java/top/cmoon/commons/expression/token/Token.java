package top.cmoon.commons.expression.token;

public interface Token {

    /**
     * 操作符类型
     *
     * @return
     */
    TokenType tokenType();


    /**
     * 标识符编码，请参考码表
     *
     * @return
     */
    int code();

    /**
     * 获取token的字符串形式
     *
     * @return
     */
    String token();


    void setCode(int code);

    void setToken(String token);

}
