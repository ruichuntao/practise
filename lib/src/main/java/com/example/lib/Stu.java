package com.example.lib;

import java.util.Observable;

public class Stu extends Observable {
    public void ask(String question) {
        System.out.println("question:" + question);
        setChanged();
        notifyObservers(question);
    }
}
