package com.example.lib;

import java.util.Observable;
import java.util.Observer;

public class Tea_Art implements Observer {
    private String name = "美术老师：";

    @Override
    public void update(Observable observable, Object object) {
        String question = (String) object;
        if (question.equals("莫奈的睡莲是他晚年的作品吗？")) {
            System.out.println(name + "是他晚年一系列的作品");
        } else {
            System.out.println(name + "我不太清楚， 你问问其他老师");
        }

    }
}
