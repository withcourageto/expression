package top.cmoon.commons.expression.eval;

import top.cmoon.commons.expression.eval.EvaluationContext;

import java.util.Map;

public interface ExprEvaluator {

    Object getVal();

    Object getVal(Map<String, Object> params);

    Object getVal(EvaluationContext evalContext);

}
