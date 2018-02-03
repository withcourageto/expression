package top.cmoon.commons.expression.token.constant;

public class TokenCodeConst {


    public static final int ERROR_CODE = -1;

    public static final int BOOLEAN_LITERAL_TRUE = 1; // true
    public static final int BOOLEAN_LITERAL_FALSE = 2; // false

    public static final int LITERAL_NULL = 3; // null

    public static final int FLOAT_NUMBER = 11; // 22.3
    public static final int INT_NUMBER = 12;    // 22


    /**
     * 变量标识符
     */
    public static final int VARIABLE = 10; // name

    public static final int STRING_LITERAL = 22; // '...'

    // 以下是运算符
    public static final int NOT_EQUAL = 31;  // !=
    public static final int EQUAL_EQUAL = 32; // ==


    public static final int GREATER_THAN = 42; // >
    public static final int GREATER_THAN_EQUAL = 43; // >=
    public static final int LESS_THAN = 44; // <
    public static final int LESS_THAN_EQUAL = 45; // <=


    public static final int PLUS = 71;      // +
    public static final int SUBTRACT = 72;  // -

    public static final int MULTIPLE = 80;  // *
    public static final int DIVIDE = 81;    // /
    public static final int MOD = 82;   // %

    public static final int EXCLAMATION_MARK = 31;  // !


    public static final int LEFT_BRACKET = 121; // (
    public static final int RIGHT_BRACKET = 122; // )

}
