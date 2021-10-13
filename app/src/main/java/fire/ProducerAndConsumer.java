package fire;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerAndConsumer {
    final int[] queue = new int[10];
    int max = 10;
    volatile int idx = 0;

    class Producer implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                synchronized (queue) {
                    while (idx == max) {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue[idx++] = 1;
                    System.out.println(Thread.currentThread().getName()+"生产了" + idx);
                    queue.notifyAll();
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Consumer implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 200; i++) {
                synchronized (queue) {
                    while (idx == 0) {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue[--idx] = 0;
                    System.out.println(Thread.currentThread().getName() + "消费了" + idx);
                    queue.notifyAll();
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        ProducerAndConsumer p = new ProducerAndConsumer();
        new Thread(p.new Producer(), "生产者1").start();
//        new Thread(p.new Producer(), "生产者2").start();
        new Thread(p.new Consumer(), "消费者1").start();
//        new Thread(p.new Consumer(), "消费者2").start();
    }
}
