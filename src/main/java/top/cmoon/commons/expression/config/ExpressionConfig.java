package top.cmoon.commons.expression.config;


import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.cmoon.commons.expression.core.*;
import top.cmoon.commons.expression.eval.DefaultExprEvaluatorFactory;
import top.cmoon.commons.expression.eval.ExprEvaluatorFactory;
import top.cmoon.commons.expression.suffixexpr.DefaultSuffixExprParser;
import top.cmoon.commons.expression.suffixexpr.SuffixExprParser;
import top.cmoon.commons.expression.word.DefaultWordParser;
import top.cmoon.commons.expression.word.WordParser;

@Configuration
public class ExpressionConfig {


    @Bean
    @ConditionalOnMissingBean(ExprEngine.class)
    ExprEngine exprEngine() {
        return new DefaultExprEngine();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(ExprEngine.class)
    WordParser wordParser() {
        return new DefaultWordParser();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(ExprEngine.class)
    SuffixExprParser suffixExprParser() {
        return new DefaultSuffixExprParser();
    }


    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(ExprEngine.class)
    ExprEvaluatorFactory exprEvaluatorFactory() {
        return new DefaultExprEvaluatorFactory();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(WordParser.class)
    OperatorRegistry operatorRegistry() {
        return new DefaultOperatorRegistry();
    }

}
