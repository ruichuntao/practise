package com.example.lib;

public class MonkeyPeach {
    public static void main(String[] args) {
        int lastdayNum = 1;
        for(int i=2; i<=10; i++) {
            lastdayNum = (lastdayNum+1) * 2;
        }
        System.out.println("猴子第一天摘了 " + lastdayNum + " 个桃子");
    }
}
