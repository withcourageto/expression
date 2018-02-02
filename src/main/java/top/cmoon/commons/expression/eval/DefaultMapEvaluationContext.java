package top.cmoon.commons.expression.eval;

import java.util.Map;

public class DefaultMapEvaluationContext implements EvaluationContext {

    private Map<String, Object> context;

    public DefaultMapEvaluationContext(Map<String, Object> context) {
        this.context = context;
    }


    @Override
    public Object getVar(String varName) {
        return context.get(varName);
    }
}
