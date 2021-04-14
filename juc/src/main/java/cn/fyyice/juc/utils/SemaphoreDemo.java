package cn.fyyice.juc.utils;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {
    public static void main(String[] args) {
        //限流
        Semaphore semaphore = new Semaphore(4);
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                try {
                    //得到
                    semaphore.acquire();
                    int time  = new Random().nextInt(4)+1;
                    System.out.println(Thread.currentThread().getName()+"得到了车位,并且要停"+time+"s");
                    TimeUnit.SECONDS.sleep(time);
                    System.out.println(Thread.currentThread().getName()+"开走了，空出一个车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 释放
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }

    }
}
