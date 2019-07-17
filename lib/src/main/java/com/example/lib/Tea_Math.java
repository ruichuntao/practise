package com.example.lib;

import java.util.Observable;
import java.util.Observer;

public class Tea_Math implements Observer {
    private String name = "数学老师：";

    @Override
    public void update(Observable observable, Object object) {
        String question = (String) object;
        if (question.equals("矩阵相乘的意义是什么呢？")) {
            System.out.println(name + "从某种角度来说， 是坐标的变换");
        } else {
            System.out.println(name + "我不太清楚， 你问问其他老师");
        }

    }
}
