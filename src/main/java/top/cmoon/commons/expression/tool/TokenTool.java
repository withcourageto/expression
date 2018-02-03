package top.cmoon.commons.expression.tool;

import java.util.List;

/**
 * Created by cool_moon on 2018/2/2.
 */
public class TokenTool {

    public static char[] toCharArr(List<Character> token) {
        Character[] arr = token.toArray(new Character[0]);
        char[] temp = new char[arr.length];
        for (int i = 0; i < arr.length; i++) {
            temp[i] = arr[i];
        }
        return temp;
    }

    /**
     * 将包含字符的List 转换为字符串
     *
     * @param token
     * @return
     */
    public static String token(List<Character> token) {
        char[] temp = toCharArr(token);
        return new String(temp);
    }
}
