package top.cmoon.commons.expression.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.cmoon.commons.expression.core.DefaultOperatorRegistry;
import top.cmoon.commons.expression.core.DefaultOperatorRegistry.OperatorRegistryEntryImpl;
import top.cmoon.commons.expression.core.OperatorRegistry;
import top.cmoon.commons.expression.token.constant.TokenCodeConst;
import top.cmoon.commons.expression.token.operand.*;
import top.cmoon.commons.expression.token.operator.*;
import top.cmoon.commons.expression.word.WordParser;

/**
 * Created by cool_moon on 2018/2/2.
 */
@Configuration
public class OperatorRegisterConfig {

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(WordParser.class)
    OperatorRegistry operatorRegistry() {

        OperatorRegistry registry = new DefaultOperatorRegistry();

        registry.register(new OperatorRegistryEntryImpl(TokenCodeConst.BOOLEAN_LITERAL_TRUE, "true", BooleanToken.class));
        registry.register(new OperatorRegistryEntryImpl(TokenCodeConst.BOOLEAN_LITERAL_FALSE, "false", BooleanToken.class));
        registry.register(new OperatorRegistryEntryImpl(TokenCodeConst.LITERAL_NULL, "null", NullToken.class));
        registry.register(new OperatorRegistryEntryImpl(TokenCodeConst.FLOAT_NUMBER, "", FloatNumToken.class));
        registry.register(new OperatorRegistryEntryImpl(TokenCodeConst.INT_NUMBER, "", IntNumToken.class));

        registry.register(new OperatorRegistryEntryImpl(TokenCodeConst.STRING_LITERAL, "", StringLiteralToken.class));
        registry.register(new OperatorRegistryEntryImpl(TokenCodeConst.VARIABLE, "", VariableToken.class));


        registry.register(new OperatorRegistryEntryImpl(TokenCodeConst.SUBTRACT, "-", SubtractToken.class));
        registry.register(new OperatorRegistryEntryImpl(TokenCodeConst.PLUS, "+", PlusToken.class));

        registry.register(new OperatorRegistryEntryImpl(TokenCodeConst.EQUAL_EQUAL, "==", EqualEqualToken.class));
        registry.register(new OperatorRegistryEntryImpl(TokenCodeConst.NOT_EQUAL, "!=", NotEqualToken.class));
        registry.register(new OperatorRegistryEntryImpl(TokenCodeConst.GREATER_THAN, ">", GreaterThanToken.class));
        registry.register(new OperatorRegistryEntryImpl(TokenCodeConst.LESS_THAN, "<", LessThanToken.class));

        registry.register(new OperatorRegistryEntryImpl(TokenCodeConst.LEFT_BRACKET, "(", LeftBracketToken.class));
        registry.register(new OperatorRegistryEntryImpl(TokenCodeConst.RIGHT_BRACKET, ")", RightBracketToken.class));

        return registry;
    }


}
