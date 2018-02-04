package top.cmoon.commons.expression.core;

import top.cmoon.commons.expression.token.Token;

import java.util.Optional;

/**
 * token注册表，只有注册了的操作符，
 * 否则，token解析过程将会报错
 * <p>
 * 如果你自定义{@link top.cmoon.commons.expression.word.WordParser WordParser},  TokenRegistry 注册表无效
 */
public interface TokenRegistry {

    interface TokenRegistryEntry {
        int code();

        String token();

        Class<? extends Token> tokenClass();
    }

    Optional<TokenRegistryEntry> get(int code);

    Optional<TokenRegistryEntry> get(String token);


    void register(TokenRegistryEntry operatorToken);
}
