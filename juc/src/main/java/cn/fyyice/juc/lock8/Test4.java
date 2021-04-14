package cn.fyyice.juc.lock8;

import java.util.concurrent.TimeUnit;

public class Test4 {
    public static void main(String[] args) {
        Phone4 phone = new Phone4();

        new Thread(() -> {
            phone.send();

        }, "A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            phone.call();
        },"B").start();

    }
}

class Phone4 {
    // synchronized锁的对象是方法的调用者！
    // 这里两个方法用的是同一个锁，谁先拿到谁先执行！
    public static synchronized void send(){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"-->发信息");
    }
    public synchronized void call(){
        System.out.println(Thread.currentThread().getName()+"-->打电话");
    }

}
