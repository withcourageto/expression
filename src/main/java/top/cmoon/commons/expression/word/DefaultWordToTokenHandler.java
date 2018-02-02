package top.cmoon.commons.expression.word;

import top.cmoon.commons.expression.token.Token;
import top.cmoon.commons.expression.token.constant.TokenCodeConst;
import top.cmoon.commons.expression.token.operand.*;
import top.cmoon.commons.expression.token.operator.EqualEqualToken;
import top.cmoon.commons.expression.token.operator.NotEqualToken;

import java.util.List;

class DefaultWordToTokenHandler implements WordToTokenHandler {

    @Override
    public Token toToken(WordParseProgress progress) {
        switch (progress.syn) {
            case 11:
                // 浮点数 数字处理
                return new FloatNumToken(progress.num);
            case 12:
                // 整数 数字处理
                return new IntNumToken(progress.int_num);
            case -1:
                // 错误：
                throw new RuntimeException("语法错误：错误位置" + progress.errorPosition + "error:" + progress.error);
            default:
                // 标识符，字面量
                if (progress.syn == 0 && progress.token.isEmpty()) {
                    return null; // 词法分析完成，正常结束
                }
                if (progress.syn == TokenCodeConst.VARIABLE) {
                    return new VariableToken(token(progress.token));
                } else if (progress.syn == TokenCodeConst.EQUAL_EQUAL) {
                    return new EqualEqualToken();
                } else if (progress.syn == TokenCodeConst.NOT_EQUAL) {
                    return new NotEqualToken();
                } else if (progress.syn == TokenCodeConst.STRING_LITERAL) {
                    return new StringLiteralToken(token(progress.token));
                } else if (progress.syn == TokenCodeConst.LITERAL_NULL) {
                    return new NullToken();
                } else {
                    throw new RuntimeException("暂不支持：" + "(" + progress.syn + "," + token(progress.token) + ")");
                }
        }
    }

    private char[] toCharArr(List<Character> token) {
        Character[] arr = token.toArray(new Character[0]);
        char[] temp = new char[arr.length];
        for (int i = 0; i < arr.length; i++) {
            temp[i] = arr[i];
        }
        return temp;
    }

    private String token(List<Character> token) {
        char[] temp = toCharArr(token);
        return new String(temp);
    }


}
