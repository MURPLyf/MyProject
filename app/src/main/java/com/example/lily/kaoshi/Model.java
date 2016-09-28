package com.example.lily.kaoshi;

/**
 * Created by Administrator on 2016/9/21.
 */
public class Model {
    String question; // hold the question
    int current = NONE; // hold the answer picked by the user, initial is NONE(see below)
    public static final int NONE = 1000; // 没有答案被选中
    public static final int ANSWER_ONE_SELECTED = 0; // 第一个答案被选中
    public static final int ANSWER_TWO_SELECTED = 1; // 第二个答案被选中
    public static final int ANSWER_THREE_SELECTED = 2; // 第三个答案被选中
    public static final int ANSWER_FOUR_SELECTED = 3; // 第四个答案被选中

    public Model(String question) {
        this.question = question;
    }

}
