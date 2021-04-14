package cn.fyyice.juc.bq;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class SynchronousQueueDemo {
    public static void main(String[] args) {
        // 同步队列     和其他BlockingQueue不一样，synchronousQueue不存储元素
        BlockingQueue<String> synchronousQueue = new SynchronousQueue<>();

        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"--->put1");
                synchronousQueue.put("1");
                System.out.println(Thread.currentThread().getName()+"--->put2");
                synchronousQueue.put("2");
                System.out.println(Thread.currentThread().getName()+"--->put3");
                synchronousQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName()+"--->get1");
                synchronousQueue.take();
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName()+"--->get2");
                synchronousQueue.take();
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName()+"--->get3");
                synchronousQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();
    }
}