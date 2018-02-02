package top.cmoon.commons.expression.word;

/**
 * 保留字，类似语言的关键字 ，例如 true, false , and ...
 * <p>
 * 保留字和保留字代码意义对应，在系统中都只能存在一次
 */
public interface ReserveWord {

    String word();

    int code();
}
