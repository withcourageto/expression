package top.cmoon.commons.expression.word;

import top.cmoon.commons.expression.token.Token;
import top.cmoon.commons.expression.exception.GrammarException;
import top.cmoon.commons.expression.exception.ReserveWordExistsException;

import java.util.List;

/**
 * 分词器，用于把表达式解析为Token序列（变量，操作符，字面量）
 * <p>
 * 例如:
 * <pre>
 * 表达式：
 *  2 + 9 == 2
 * 解析后：
 * token : code
 *   2  :  12
 *   +  :  71
 *   9  :  12
 *   == :  41
 *   2  :  12
 *  </pre>
 */
public interface WordParser {

    /**
     * 添加保留字
     *
     * @param reserveWords
     * @throws ReserveWordExistsException 如果保留字冲突（word,或者code冲突）
     */
    void addReserveWords(ReserveWord... reserveWords) throws ReserveWordExistsException;


    /**
     * 分解表达式为 token
     *
     * @param expr 表达式
     * @return
     * @throws GrammarException 如果有系统不识别的标识
     */
    List<Token> parseToken(String expr) throws GrammarException;
}
