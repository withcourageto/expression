package top.cmoon.commons.expression.core;

import top.cmoon.commons.expression.token.Token;

import java.util.HashMap;
import java.util.Map;

public class DefaultOperatorRegistry implements OperatorRegistry {

    Map<Integer, OperatorRegistryEntry> codeMap;
    Map<String, OperatorRegistryEntry> tokenMap;

    public DefaultOperatorRegistry() {
        codeMap = new HashMap<>();
        tokenMap = new HashMap<>();
    }

    @Override
    public OperatorRegistryEntry get(int code) {
        return codeMap.get(code);
    }

    @Override
    public OperatorRegistryEntry get(String token) {
        return tokenMap.get(token);
    }

    @Override
    public void register(OperatorRegistryEntry operatorToken) {
        if (codeMap.containsKey(operatorToken.code())) {
            throw new RuntimeException("操作符code 已经存在：" + operatorToken.code());
        }
        codeMap.put(operatorToken.code(), operatorToken);

        if (tokenMap.containsKey(operatorToken.token())) {
            throw new RuntimeException("操作符 token 已经存在：" + operatorToken.token());
        }
        tokenMap.put(operatorToken.token(), operatorToken);
    }

    public static class OperatorRegistryEntryImpl implements OperatorRegistryEntry {

        private int code;
        private String token;
        private Class<? extends Token> tokenClass;

        public OperatorRegistryEntryImpl(int code, String token, Class<? extends Token> tokenClass) {
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
