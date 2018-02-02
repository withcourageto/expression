package top.cmoon.commons.expression.word;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.cmoon.commons.expression.exception.ReserveWordExistsException;
import top.cmoon.commons.expression.token.Token;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultWordParser implements WordParser {

    private static Logger logger = LoggerFactory.getLogger(DefaultWordParser.class);

    private Map<String, ReserveWord> reserveWords = new HashMap<>();
    private Map<Integer, ReserveWord> reserveWordCodes = new HashMap<>();   // 用于检查保留字代码是否重复

    @Override
    public void addReserveWords(ReserveWord... reserveWords) throws ReserveWordExistsException {

        synchronized (this) {
            for (ReserveWord reserveWord : reserveWords) {
                if (this.reserveWordCodes.containsKey(reserveWord.code())) {

                    logger.error("保留字代码已经存在" + reserveWord.code());
                    throw new RuntimeException("保留字代码已经存在" + reserveWord.code());
                }

                if (this.reserveWords.containsKey(reserveWord.word())) {
                    logger.error("保留字已经存在" + reserveWord.code());
                    throw new RuntimeException("保留字已经存在" + reserveWord.code());
                }

                this.reserveWords.put(reserveWord.word(), reserveWord);
                this.reserveWordCodes.put(reserveWord.code(), reserveWord);
            }
        }

    }


    public List<Token> parseToken(String expr) {

        assert expr != null;
        String realExpr = expr.trim();
        assert realExpr.length() > 0;

        List<Token> tokens = new ArrayList<>();
        char[] exprArr = realExpr.toCharArray();

        WordToTokenHandler toTokenHandler = new DefaultWordToTokenHandler();
        WordScanner wordScanner = new WordScanner();

        WordParseProgress progress = new WordParseProgress();   // 在扫描过程中，进度状态将会被更改
        do {
            wordScanner.scan(exprArr, progress, reserveWords);

            tokens.add(toTokenHandler.toToken(progress));
        } while (progress.syn != 0 && progress.pos < exprArr.length);

        logger.info("================tokens=====================");
        logger.info(tokens.toString());

        return tokens;
    }

}
