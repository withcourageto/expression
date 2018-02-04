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

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExpressionApplicationTests {

    @Autowired
    ExprEngine engine;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testEqual() {
        String expr = "'lli' == 'aa'";
        Object _false = engine.eval(expr);
        Assert.assertEquals(_false, FALSE);

        expr = "false == 1 > 0";
        _false = engine.eval(expr);
        Assert.assertEquals(_false, FALSE);


        expr = "'aa' == 'aa'";
        Object _true = engine.eval(expr);
        Assert.assertEquals(_true, TRUE);

        expr = "true == 1 > 0";
        _true = engine.eval(expr);
        Assert.assertEquals(_true, TRUE);

    }

    @Test
    public void testNotEqual() {
        String expr = "'lli' != 'aa'";
        Object _true = engine.eval(expr);
        Assert.assertEquals(_true, TRUE);

        expr = "'aa' != 'aa'";
        Object _false = engine.eval(expr);
        Assert.assertEquals(_false, FALSE);
    }


    @Test
    public void testGreaterThan() {
        String expr = "22 > 3";
        Object obj = engine.eval(expr);
        Assert.assertEquals(TRUE, obj);

        expr = "3 > 22";
        Object _false = engine.eval(expr);
        Assert.assertEquals(_false, FALSE);

        expr = "3 > 3";
        _false = engine.eval(expr);
        Assert.assertEquals(_false, FALSE);
    }

    @Test
    public void testLessThan() {
        String expr = "22 < 3";
        Object _false = engine.eval(expr);
        Assert.assertEquals(_false, FALSE);

        expr = "3 < 22";
        Object _true = engine.eval(expr);
        Assert.assertEquals(TRUE, _true);

        expr = "3 < 3";
        _false = engine.eval(expr);
        Assert.assertEquals(_false, FALSE);
    }


    @Test
    public void testContextVar() {
        Map<String, Object> context = new HashMap<>();
        context.put("count", 40);

        Object obj = engine.eval("count == 40", context);

        Assert.assertEquals(TRUE, obj);
    }

    @Test
    public void testPlus() {
        Object obj = engine.eval("1+1 > 1");
        Assert.assertEquals(TRUE, obj);

        Object obj1 = engine.eval("5-3");
        Assert.assertEquals(2, obj1);
    }

    @Test
    public void testSubtraction() {
        Object obj = engine.eval("1-1");
        Assert.assertEquals(0, obj);
    }

    @Test
    public void testDivide() {
        Object obj = engine.eval("9/3");
        Assert.assertEquals(3, obj);
    }

    @Test
    public void testMultiply() {
        Object obj = engine.eval("3 * 3");
        Assert.assertEquals(9, obj);
    }


    @Test
    public void testComplication() {
        Object obj = engine.eval("(1+1) * 5 == 10");
        Assert.assertEquals(TRUE, obj);

        Object three = engine.eval("1+1*2");
        Assert.assertEquals(3, three);
    }


}
