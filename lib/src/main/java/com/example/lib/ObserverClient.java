package com.example.lib;

public class ObserverClient {
    public static void main(String[] args) {

        Stu stu = new Stu();

        Tea_Math tea_Math = new Tea_Math();
        Tea_Art tea_Art = new Tea_Art();

        stu.addObserver(tea_Math);
        stu.addObserver(tea_Art);

        stu.ask("矩阵相乘的意义是什么呢？");

        stu.ask("莫奈的睡莲是他晚年的作品吗？");
    }
}
