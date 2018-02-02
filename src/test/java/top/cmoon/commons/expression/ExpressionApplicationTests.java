package top.cmoon.commons.expression;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.cmoon.commons.expression.core.ExprEngine;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExpressionApplicationTests {

    @Autowired
    ExprEngine engine;

    @Test
    public void contextLoads() {
        String expr = "'lli' == 'lideng'";
        Map<String, Object> context = new HashMap<>();
        Object obj = engine.eval(expr, context);
        Assert.assertEquals(obj, Boolean.FALSE);
    }

    @Test
    public void testGreatherThan() {
        String expr = "22 > 3";
        Map<String, Object> context = new HashMap<>();
        Object obj = engine.eval(expr, context);
        Assert.assertEquals(obj, Boolean.FALSE);
    }

    public void testContext() {

        Map<String, Object> context = new HashMap<>();
        context.put("count", 40);

        Object obj = engine.eval("count == 40", context);

        Assert.assertEquals(obj, Boolean.TRUE);
    }


}
