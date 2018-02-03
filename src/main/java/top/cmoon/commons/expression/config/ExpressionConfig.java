package top.cmoon.commons.expression.config;


import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.cmoon.commons.expression.core.*;
import top.cmoon.commons.expression.eval.DefaultExprEvaluatorFactory;
import top.cmoon.commons.expression.eval.ExprEvaluatorFactory;
import top.cmoon.commons.expression.suffixexpr.DefaultSuffixExprParser;
import top.cmoon.commons.expression.suffixexpr.SuffixExprParser;
import top.cmoon.commons.expression.token.constant.TokenCodeConst;
import top.cmoon.commons.expression.token.operator.*;
import top.cmoon.commons.expression.word.*;

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
    @ConditionalOnBean(WordParser.class)
    WordScanner wordScanner() {
        return new WordScanner();
    }

    @Bean
    @ConditionalOnBean(WordParser.class)
    WordToTokenHandler wordToTokenHandler() {
        return new DefaultWordToTokenHandler();
    }


}
