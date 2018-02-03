package top.cmoon.commons.expression.token.operator;

import top.cmoon.commons.expression.token.AbstractOperatorToken;
import top.cmoon.commons.expression.token.OperatorToken;
import top.cmoon.commons.expression.token.OperatorType;
import top.cmoon.commons.expression.token.constant.TokenCodeConst;

/**
 * Created by cool_moon on 2018/2/2.
 */
public class RightBracketToken extends AbstractOperatorToken implements OperatorToken {

    public RightBracketToken() {
        super(TokenCodeConst.RIGHT_BRACKET, ")");
    }


    @Override
    public OperatorType operatorType() {
        return OperatorType.SUFFIX;
    }


}
