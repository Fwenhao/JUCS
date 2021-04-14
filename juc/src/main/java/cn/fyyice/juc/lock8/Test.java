package cn.fyyice.juc.lock8;

import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) {
        Phone phone = new Phone();

        new Thread(()->{
            phone.send();

        },"A").start();

        try {
            System.out.println("我要等待3s");
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            phone.call();
        },"B").start();
    }
}

class Phone {
    // synchronized锁的对象是方法的调用者！
    // 这里两个方法用的是同一个锁，谁先拿到谁先执行！
    public synchronized void send(){
        try {
            System.out.println("我是phone中的等待");
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发信息");
    }
    public synchronized void call(){
        System.out.println("打电话");
    }
}
