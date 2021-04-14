package cn.fyyice.juc.utils;

import java.util.concurrent.CountDownLatch;

//计数器
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        //总数是10，必须要执行任务的时候才使用
        CountDownLatch count = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"溜了");
                count.countDown();
            },String.valueOf(i)).start();
        }
        //等待计数器归0，再向下执行
        count.await();
        System.out.println("遛完了，关门");
    }
}
