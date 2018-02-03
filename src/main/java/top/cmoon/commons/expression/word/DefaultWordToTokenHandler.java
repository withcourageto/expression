package top.cmoon.commons.expression.word;

import org.springframework.beans.factory.annotation.Autowired;
import top.cmoon.commons.expression.core.OperatorRegistry;
import top.cmoon.commons.expression.exception.GrammarException;
import top.cmoon.commons.expression.token.Token;

import static top.cmoon.commons.expression.token.constant.TokenCodeConst.ERROR_CODE;
import static top.cmoon.commons.expression.tool.TokenTool.token;

public class DefaultWordToTokenHandler implements WordToTokenHandler {

    @Autowired
    private OperatorRegistry operatorRegistry;

    @Override
    public Token toToken(WordParseProgress progress) {

        if (progress.syn == ERROR_CODE) {
            throw new RuntimeException("语法错误：错误位置" + progress.errorPosition + "error:" + progress.error);
        }

        OperatorRegistry.OperatorRegistryEntry operatorRegistryEntry = operatorRegistry.get(progress.syn);

        if (operatorRegistryEntry == null) {
            throw new GrammarException("不支持的操作符," + progress.syn + ":" + token(progress.token));
        }

        Class<? extends Token> tokenClass = operatorRegistryEntry.tokenClass();

        try {
            Token token = tokenClass.newInstance();

            token.setCode(progress.syn);
            token.setToken(token(progress.token));

            return token;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
