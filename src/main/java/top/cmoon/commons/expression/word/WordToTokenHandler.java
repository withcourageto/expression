package top.cmoon.commons.expression.word;

import top.cmoon.commons.expression.token.Token;

/**
 * 内部使用接口，不对外开放
 */
public interface WordToTokenHandler {

    Token toToken(WordParseProgress progress);
}
