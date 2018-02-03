package top.cmoon.commons.expression.token.operator;

import top.cmoon.commons.expression.token.*;
import top.cmoon.commons.expression.token.constant.TokenCodeConst;

/**
 * Created by cool_moon on 2018/2/2.
 */
public class LeftBracketToken extends AbstractOperatorToken implements OperatorToken {

    public LeftBracketToken() {
        super(TokenCodeConst.LEFT_BRACKET, "(");
    }


    @Override
    public OperatorType operatorType() {
        return OperatorType.SUFFIX;
    }


}
