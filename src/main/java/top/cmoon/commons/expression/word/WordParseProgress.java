package top.cmoon.commons.expression.word;

import java.util.ArrayList;
import java.util.List;

public class WordParseProgress {

    int pos;
    int syn;
    List<Character> token;
    double num;
    int int_num;
    String error;
    int errorPosition;

    public WordParseProgress() {
        pos = 0;
        syn = 0;
        token = new ArrayList<>();
    }

    public void next() {
        token.clear();
    }


}
