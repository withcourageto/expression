package top.cmoon.commons.expression.tool;

public class PrioritySupplier {

    /**
     * 获取操作符优先级
     *
     * @param code
     * @return
     */
    public static int getPriority(int code) {

        if (code < 30) {
            return code / 30;
        }

        return code / 10;
    }

}
