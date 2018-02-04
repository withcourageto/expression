package top.cmoon.commons.expression.word;

import org.springframework.beans.factory.annotation.Autowired;
import top.cmoon.commons.expression.core.TokenRegistry;
import top.cmoon.commons.expression.core.TokenRegistry.TokenRegistryEntry;
import top.cmoon.commons.expression.exception.GrammarException;
import top.cmoon.commons.expression.token.constant.TokenCodeConst;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class WordScanner {

    private static final char space_char = ' ';
    private static final int ERROR_SYN = -1;


    @Autowired
    private TokenRegistry tokenRegistry;


    /**
     * 扫描表达式
     * <p>
     * 当遇到错误的时候，直接返回
     *
     * @param prog
     * @param progress
     * @param reserveWords
     */
    public void scan(char[] prog, WordParseProgress progress, Map<String, ReserveWord> reserveWords) {

        progress.next();

        int p = progress.pos;
        List<Character> token = progress.token;
        int syn;

        char ch = prog[p++];

        // 1. 去除无用空格
        while (ch == space_char && p < prog.length) {
            ch = prog[p];
            p++;
        }
        if (ch == space_char) {
            syn = 0; // end

            fillEndProgress(progress, p, syn);
            return;
        }

        // 2. 字符串解析
        if (ch == '\'') { // 单引号，表明是字符串
            int tempPosition = p - 1;
            ch = prog[p];
            while (ch != '\'') {
                token.add(ch);
                p++;

                if (p >= prog.length) {
                    break;
                }

                ch = prog[p];
            }
            if (ch == '\'') {
                syn = TokenCodeConst.STRING_LITERAL;
                p++;
            } else {
                syn = ERROR_SYN;
                String error = "单引号没有对应结束符号";
                int errorPosition = tempPosition;

                fillErrProgress(progress, p, syn, error, errorPosition);
                return;
            }
        } else if (isLetter(ch)) { // 开始字符是字母，有可能是变量或者标识符

            while (isDigital(ch) || isLetter(ch) || isDot(ch)) {
                token.add(ch);
                ch = prog[p++];
            }

            p--; // 跳回到结束标识符或者变量的地方，如果是空格，下一次scan会丢弃
            syn = TokenCodeConst.VARIABLE;

            String tokenStr = new String(toCharArr(token));

            // 如果在保留字中找到标识符，说明是保留字关键字

            Optional<TokenRegistryEntry> reserveWord = tokenRegistry.get(tokenStr);
            if (reserveWord.isPresent()) {
                syn = reserveWord.get().code();
            }
        } else if (isDigital(ch)) { // 开始字符数字
            int tempPosition = p - 1;
            while (isDigital(ch)) {
                token.add(ch);
                if (p >= prog.length) {
                    break;
                }
                ch = prog[p++];
            }
            boolean isDouble = false;
            if (isDot(ch)) {
                isDouble = true;
                token.add(ch);

                ch = prog[p];
                while (isDigital(ch)) {
                    token.add(ch);
                    p++;

                    if (p >= prog.length) {
                        break;
                    }

                    ch = prog[p];
                }

            }
            if (!isDouble) {
                if (p < prog.length)
                    p--;// 跳回到结束数字的地方，如果是空格，下一次scan会丢弃
                syn = TokenCodeConst.INT_NUMBER;
            } else {
                syn = TokenCodeConst.FLOAT_NUMBER;
            }

            if (isDouble) {
                try {
                    Double.valueOf(new String(token(token)));
                } catch (NumberFormatException e) {
                    syn = ERROR_SYN;
                    String error = "非法数字：" + new String(token(token));
                    int errorPosition = tempPosition - 1;

                    fillErrProgress(progress, p, syn, error, errorPosition);
                    return;
                }
            } else {
                try {
                    Integer.valueOf(new String(token(token)));
                } catch (NumberFormatException e) {
                    syn = ERROR_SYN;
                    String error = "非法数字：" + new String(token(token));
                    int errorPosition = tempPosition - 1;
                    fillErrProgress(progress, p, syn, error, errorPosition);
                    return;
                }
            }

        } else {

            switch (ch) {
                case '<':
                    token.add(ch);
                    ch = prog[p++];
                    if (ch == '=') {
                        syn = TokenCodeConst.LESS_THAN_EQUAL;
                        token.add(ch);
                    } else {
                        syn = TokenCodeConst.LESS_THAN;
                        p--;
                    }
                    break;
                case '>':
                    token.add(ch);
                    ch = prog[p++];

                    if (ch == '=') {
                        syn = TokenCodeConst.GREATER_THAN_EQUAL;
                        token.add(ch);
                    } else {
                        syn = TokenCodeConst.GREATER_THAN;
                        p--;
                    }
                    break;
                case '!':
                    token.add(ch);
                    ch = prog[p++];

                    if (ch == '=') {
                        syn = TokenCodeConst.NOT_EQUAL;
                        token.add(ch);
                    } else {
                        syn = tokenRegistry.get("!").orElseThrow(() -> new GrammarException("不支持的操作符:!")).code();
                        p--;
                    }
                    break;
                case '=':
                    token.add(ch);

                    ch = prog[p++];
                    if (ch == '=') {
                        syn = TokenCodeConst.EQUAL_EQUAL;
                        token.add(ch);
                    } else {
                        syn = -1;
                    }
                    break;
                case '*':
                    syn = tokenRegistry.get("*").orElseThrow(() -> new GrammarException("不支持的操作符:*")).code();
                    token.add(ch);
                    break;
                case '/':
                    syn = tokenRegistry.get("/").orElseThrow(() -> new GrammarException("不支持的操作符:/")).code();
                    token.add(ch);
                    break;
                case '+':
                    syn = TokenCodeConst.PLUS;
                    token.add(ch);
                    break;
                case '-':
                    syn = TokenCodeConst.SUBTRACT;
                    token.add(ch);
                    break;
                case '%':
                    syn = tokenRegistry.get("%").orElseThrow(() -> new GrammarException("不支持的操作符:%")).code();
                    token.add(ch);
                    break;
                case '(':
                    syn = tokenRegistry.get("(").orElseThrow(() -> new GrammarException("不支持的操作符:(")).code();
                    token.add(ch);
                    break;
                case ')':
                    syn = tokenRegistry.get(")").orElseThrow(() -> new GrammarException("不支持的操作符:)")).code();
//                    syn = TokenCodeConst.RIGHT_BRACKET;
                    token.add(ch);
                    break;

                default:
                    syn = ERROR_SYN; // 非法标识

                    String error = "非法符号:" + ch;
                    int errorPosition = p - 1;

                    fillErrProgress(progress, p, syn, error, errorPosition);
                    return;
            }
        }
        fillProgress(progress, p, token, syn);
    }

    private void fillProgress(WordParseProgress progress, int p, List<Character> token, int syn) {
        progress.syn = syn;
        progress.pos = p;
        progress.token = token;
    }

    private void fillErrProgress(WordParseProgress progress, int p, int syn, String error, int errorPosition) {
        progress.syn = syn;
        progress.pos = p;
        progress.error = error;
        progress.errorPosition = errorPosition;
    }

    private void fillEndProgress(WordParseProgress progress, int p, int syn) {
        progress.syn = syn;
        progress.pos = p;
    }


    private char[] toCharArr(List<Character> token) {
        Character[] arr = token.toArray(new Character[0]);
        char[] temp = new char[arr.length];
        for (int i = 0; i < arr.length; i++) {
            temp[i] = arr[i];
        }
        return temp;
    }

    private String token(List<Character> token) {
        char[] temp = toCharArr(token);
        return new String(temp);
    }

    private boolean isLetter(char ch) {
        return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || ch == '[' || ch == ']';
    }

    private boolean isDigital(char ch) {
        return ch >= '0' && ch <= '9';
    }

    private boolean isDot(char ch) {
        return ch == '.';
    }
}
