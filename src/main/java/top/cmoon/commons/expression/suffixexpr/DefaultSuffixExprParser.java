package top.cmoon.commons.expression.suffixexpr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.cmoon.commons.expression.token.OperatorToken;
import top.cmoon.commons.expression.token.Token;
import top.cmoon.commons.expression.token.TokenType;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DefaultSuffixExprParser implements SuffixExprParser {

    private Logger logger = LoggerFactory.getLogger(DefaultSuffixExprParser.class);

    /**
     * 将表达式转换为后缀表达式
     * <p>
     * 原理：
     * <p>
     * 正常表达式：
     * (1 + 2) + 3 * 2
     * 转换为后缀表达式之后为：
     * 1,2,+,3,2,*,+
     *
     * @param tokens
     * @return
     */
    @Override
    public List<Token> toSuffixExpr(List<Token> tokens) {

        List<Token> sufExp = new ArrayList<>();

        Stack<OperatorToken> suffixStack = new Stack<>();

        for (int i = 0; i < tokens.size(); i++) {
            Token token = tokens.get(i);

            if (token.tokenType() == TokenType.OPERAND) {   // 操作数
                sufExp.add(token);
            } else if (token.tokenType() == TokenType.OPERATOR) {    // 运算符
                OperatorToken opToken = (OperatorToken) token;
                if (isLeftBracket(opToken)) { // "("
                    suffixStack.add(opToken);
                } else if (isRightBracket(token)) { // ")"

                    Token temp = suffixStack.pop();
                    while (!isLeftBracket(temp)) {
                        sufExp.add(temp);
                        temp = suffixStack.pop();
                    }
                } else {
                    if (suffixStack.isEmpty()) {
                        suffixStack.push(opToken);
                    } else {
                        OperatorToken top = suffixStack.pop();
                        if (isLeftBracket(top) || opToken.getPriority() > top.getPriority()) {
                            suffixStack.push(top);
                            suffixStack.push(opToken);
                        } else {
                            sufExp.add(top);
                            suffixStack.push(opToken);
                        }
                    }
                }
            } else {
                throw new RuntimeException("未识别的token类型：" + token.tokenType());
            }

        } // read all tokens

        while (!suffixStack.isEmpty()) {
            sufExp.add(suffixStack.pop());
        }


        logger.info("====================sufix===================");
        logger.info(sufExp.toString());

        return sufExp;
    }

    private boolean isRightBracket(Token token) {
        return token.token().equals(")");
    }

    private boolean isLeftBracket(Token token) {
        return token.token().equals("(");
    }
}
