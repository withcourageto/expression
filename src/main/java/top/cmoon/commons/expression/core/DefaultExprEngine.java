package top.cmoon.commons.expression.core;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import top.cmoon.commons.expression.eval.EvaluationContext;
import top.cmoon.commons.expression.eval.ExprEvaluator;
import top.cmoon.commons.expression.eval.ExprEvaluatorFactory;
import top.cmoon.commons.expression.suffixexpr.SuffixExprParser;
import top.cmoon.commons.expression.token.Token;
import top.cmoon.commons.expression.eval.DefaultMapEvaluationContext;
import top.cmoon.commons.expression.word.WordParser;

import java.util.List;
import java.util.Map;

public class DefaultExprEngine implements ExprEngine {

    private Logger logger = LoggerFactory.getLogger(DefaultExprEngine.class);

    @Autowired
    private WordParser wordParser;

    @Autowired
    private SuffixExprParser suffixExprParser;

    @Autowired
    private ExprEvaluatorFactory exprEvaluatorFactory;

    @Override
    public Object eval(String expr, Map<String, Object> context) {

        if (context == null || context.isEmpty()) {
            return eval(expr, (EvaluationContext) null);
        }

        EvaluationContext evaluationContext = new DefaultMapEvaluationContext(context);
        return eval(expr, evaluationContext);
    }

    public Object eval(String expr) {
        return eval(expr, (Map) null);
    }

    @Override
    public Object eval(String expr, EvaluationContext evaluationContext) {

        logger.info("================expression=======================");
        logger.info(expr);

        // 1. to token
        List<Token> tokens = wordParser.parseToken(expr);


        // 2. token to suffix
        List<Token> suffixExpr = suffixExprParser.toSuffixExpr(tokens);


        // 3. evaluator
        ExprEvaluator exprEvaluator = exprEvaluatorFactory.createExprEvaluator(suffixExpr);

        // 4. eval
        if (evaluationContext == null) {
            return exprEvaluator.getVal();
        }
        return exprEvaluator.getVal(evaluationContext);
    }

}
