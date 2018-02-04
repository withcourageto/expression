package top.cmoon.commons.expression.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.cmoon.commons.expression.core.DefaultTokenRegistry;
import top.cmoon.commons.expression.core.DefaultTokenRegistry.TokenRegistryEntryImpl;
import top.cmoon.commons.expression.core.TokenRegistry;
import top.cmoon.commons.expression.token.constant.TokenCodeConst;
import top.cmoon.commons.expression.token.operand.*;
import top.cmoon.commons.expression.token.operator.*;
import top.cmoon.commons.expression.word.WordParser;

/**
 * Token 注册表配置，暂时硬编码到代码中
 */
@Configuration
public class TokenRegisterConfig {

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(WordParser.class)
    TokenRegistry operatorRegistry() {

        TokenRegistry registry = new DefaultTokenRegistry();

        registry.register(new TokenRegistryEntryImpl(TokenCodeConst.BOOLEAN_LITERAL_TRUE, "true", BooleanToken.class));
        registry.register(new TokenRegistryEntryImpl(TokenCodeConst.BOOLEAN_LITERAL_FALSE, "false", BooleanToken.class));
        registry.register(new TokenRegistryEntryImpl(TokenCodeConst.LITERAL_NULL, "null", NullToken.class));
        registry.register(new TokenRegistryEntryImpl(TokenCodeConst.FLOAT_NUMBER, null, FloatNumToken.class));
        registry.register(new TokenRegistryEntryImpl(TokenCodeConst.INT_NUMBER, null, IntNumToken.class));

        registry.register(new TokenRegistryEntryImpl(TokenCodeConst.STRING_LITERAL, null, StringLiteralToken.class));
        registry.register(new TokenRegistryEntryImpl(TokenCodeConst.VARIABLE, null, VariableToken.class));


        registry.register(new TokenRegistryEntryImpl(TokenCodeConst.SUBTRACT, "-", SubtractToken.class));
        registry.register(new TokenRegistryEntryImpl(TokenCodeConst.PLUS, "+", PlusToken.class));

        registry.register(new TokenRegistryEntryImpl(TokenCodeConst.MULTIPLY, "*", MultiplyToken.class));
        registry.register(new TokenRegistryEntryImpl(TokenCodeConst.DIVIDE, "/", DivideToken.class));
        registry.register(new TokenRegistryEntryImpl(TokenCodeConst.MOD, "%", ModToken.class));


        registry.register(new TokenRegistryEntryImpl(TokenCodeConst.EQUAL_EQUAL, "==", EqualEqualToken.class));
        registry.register(new TokenRegistryEntryImpl(TokenCodeConst.NOT_EQUAL, "!=", NotEqualToken.class));
        registry.register(new TokenRegistryEntryImpl(TokenCodeConst.GREATER_THAN, ">", GreaterThanToken.class));
        registry.register(new TokenRegistryEntryImpl(TokenCodeConst.LESS_THAN, "<", LessThanToken.class));

        registry.register(new TokenRegistryEntryImpl(TokenCodeConst.LEFT_BRACKET, "(", LeftBracketToken.class));
        registry.register(new TokenRegistryEntryImpl(TokenCodeConst.RIGHT_BRACKET, ")", RightBracketToken.class));

        return registry;
    }

}
