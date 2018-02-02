package top.cmoon.commons.expression.core;

import top.cmoon.commons.expression.eval.EvaluationContext;

import java.util.Map;

public interface ExprEngine {

    Object eval(String expr, Map<String, Object> context);

    Object eval(String expr);

    Object eval(String expr, EvaluationContext evaluationContext);

}
