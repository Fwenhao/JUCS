package cn.fyyice.juc.lock8;

import java.util.concurrent.TimeUnit;

public class Test2 {
    public static void main(String[] args) {
        Phone2 phone = new Phone2();
        Phone2 phone2 = new Phone2();

        new Thread(()->{
            phone.send();

        },"A2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            phone2.call();
        },"B2").start();
    }
}

class Phone2 {
    // synchronized锁的对象是方法的调用者！
    // 这里两个方法用的是同一个锁，谁先拿到谁先执行！
    public synchronized void send(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"-->发信息");
    }
    public synchronized void call(){
        System.out.println(Thread.currentThread().getName()+"-->打电话");
    }

    public void hello(){
        System.out.println("hello");
    }
}
