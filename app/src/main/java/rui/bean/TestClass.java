package rui.bean;

import java.util.concurrent.locks.ReentrantLock;

public class TestClass implements Runnable {
    int num = 1;
    int sum;
    int i = 0;
//    ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (i < 1000) {
            jia();
            i++;

        }

    }

    private  void jia() {
        synchronized (this) {
            sum += num;
            System.out.println(Thread.currentThread().getName() + "   " + sum);
        }
    }


}
