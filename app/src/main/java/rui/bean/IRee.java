package rui.bean;

import java.util.concurrent.CountDownLatch;

public class IRee implements Runnable {

    CountDownLatch latch = new CountDownLatch(3);
    int sum1 = 0;
    int sum2 = 0;
    int sum3 = 0;
    Thread1 thread1;
    Thread2 thread2;
    Thread3 thread3;
    Thread thread01;
    Thread thread02;
    Thread thread03;
    ThreadLocal<Integer> local1 = new ThreadLocal<>();
    ThreadLocal<Integer> local2 = new ThreadLocal<>();
    ThreadLocal<Integer> local3 = new ThreadLocal<>();

    public IRee() {
        thread1 = new Thread1(this, latch);
//        thread2 = new Thread2(this, latch);
//        thread3 = new Thread3(this, latch);
    }

    @Override
    public void run() {
        try {
//            latch.await();
            System.out.println(sum1 + sum2 + sum3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] s) {
        IRee ree = new IRee();
        ree.thread01 = new Thread(ree.thread1);
        ree.thread02 = new Thread(ree.thread1);
        ree.thread03 = new Thread(ree.thread1);
        ree.thread01.setName("111");
        ree.thread02.setName("222");
        ree.thread03.setName("333");
        ree.thread01.start();
        ree.thread02.start();
        ree.thread03.start();
        Thread thread = new Thread(ree);
        thread.start();
//        int x = 0;
//        for (int i = 0; i < 30000000; i++) {
//            x+=i;
//        }
//        System.out.println(x);
//        new IRee().b();
//        int []a =new int[100];
//        for (int i = 0;i<a.length;i++){
//            a[i] = i+1;
//        }
//        for (int i = 0;i<a.length;i++){
//            Random r = new Random();
//            int x = r.nextInt(100);
//            int temp = a[i];
//            a[i] = a[x];
//            a[x] = temp;
//        }
//        for (int i:a) {
//            System.out.print(i+",");
//        }
    }


//    WeakReference<String> a(){
//        String o = new String("123");
//        WeakReference<String> w = new WeakReference<>(o);
//        return w;
//    }
//
//    void b(){
//        WeakReference<String> w = a();
//        String s = w.get().toUpperCase();
//        System.out.println(s);
//    }

}

class Thread1 implements Runnable {
    IRee thread4;
    CountDownLatch latch;
    ThreadLocal<Integer> local1;

    public Thread1(Runnable runnable, CountDownLatch latch) {
        thread4 = (IRee) runnable;
        this.latch = latch;
    }

    @Override
    public void run() {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                thread4.sum1 += i;
                System.out.println(Thread.currentThread().getName()+"   ," + thread4.sum1);
                if (i == 5 && !Thread.currentThread().getName().equals(thread4.thread03.getName())) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (Thread.currentThread().getName().equals(thread4.thread03.getName())){
                    notifyAll();
                }
            }
//        latch.countDown();
        }
    }
}

class Thread2 implements Runnable {
    IRee thread4;
    CountDownLatch latch;
    ThreadLocal<Integer> local2;


    public Thread2(Runnable runnable, CountDownLatch latch) {
        thread4 = (IRee) runnable;
        this.latch = latch;
    }

    @Override
    public void run() {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                thread4.sum2 += i;
                System.out.println(Thread.currentThread().getName()+"   ," + thread4.sum2);
                if (i == 5)
                    notify();

            }
//        latch.countDown();
        }
    }
}

class Thread3 implements Runnable {
    IRee thread4;
    CountDownLatch latch;
    ThreadLocal<Integer> local3;


    public Thread3(Runnable runnable, CountDownLatch latch) {
        thread4 = (IRee) runnable;
        this.latch = latch;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            thread4.sum3 += i;
            System.out.println(Thread.currentThread().getName() +"   ,"+ thread4.sum3);
        }
//        latch.countDown();
    }
}