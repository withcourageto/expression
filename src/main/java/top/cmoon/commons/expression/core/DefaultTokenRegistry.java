package top.cmoon.commons.expression.core;

import top.cmoon.commons.expression.token.Token;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DefaultTokenRegistry implements TokenRegistry {

    Map<Integer, TokenRegistryEntry> codeMap;
    Map<String, TokenRegistryEntry> tokenMap;

    public DefaultTokenRegistry() {
        codeMap = new HashMap<>();
        tokenMap = new HashMap<>();
    }

    @Override
    public Optional<TokenRegistryEntry> get(int code) {
        return Optional.ofNullable(codeMap.get(code));
    }

    @Override
    public Optional<TokenRegistryEntry> get(String token) {
        return Optional.ofNullable(tokenMap.get(token));
    }

    @Override
    public void register(TokenRegistryEntry operatorToken) {
        if (codeMap.containsKey(operatorToken.code())) {
            throw new RuntimeException("操作符code 已经存在：" + operatorToken.code());
        } else
            codeMap.put(operatorToken.code(), operatorToken);

        if (operatorToken.token() != null && tokenMap.containsKey(operatorToken.token())) {
            throw new RuntimeException("操作符 token 已经存在：" + operatorToken.token());
        } else
            tokenMap.put(operatorToken.token(), operatorToken);
    }

    public static class TokenRegistryEntryImpl implements TokenRegistryEntry {

        private int code;
        private String token;
        private Class<? extends Token> tokenClass;

        public TokenRegistryEntryImpl(int code, String token, Class<? extends Token> tokenClass) {
            this.code = code;
            this.token = token;
            this.tokenClass = tokenClass;
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
        public Class<? extends Token> tokenClass() {
            return tokenClass;
        }
    }

}
