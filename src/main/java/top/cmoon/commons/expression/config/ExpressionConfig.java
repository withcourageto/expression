package top.cmoon.commons.expression.config;


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
    WordParser wordParser() {
        return new DefaultWordParser();
    }

    @Bean
    @ConditionalOnMissingBean
    SuffixExprParser suffixExprParser() {
        return new DefaultSuffixExprParser();
    }


    @Bean
    @ConditionalOnMissingBean
    ExprEvaluatorFactory exprEvaluatorFactory() {
        return new DefaultExprEvaluatorFactory();
    }

}
