package top.cmoon.commons.expression.core;

import top.cmoon.commons.expression.token.OperatorToken;
import top.cmoon.commons.expression.token.Token;

/**
 * 操作符注册，只有注册了的操作符，才能被系统识别，如果你自定义{@link top.cmoon.commons.expression.word.WordParser WordParser}, 此处注册了也无效
 */
public interface OperatorRegistry {

    interface OperatorRegistryEntry {
        int code();

        String token();

        Class<? extends Token> tokenClass();
    }

    OperatorRegistryEntry get(int code);

    OperatorRegistryEntry get(String token);

    void register(OperatorRegistryEntry operatorToken);
}
