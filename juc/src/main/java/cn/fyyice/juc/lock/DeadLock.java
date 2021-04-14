package cn.fyyice.juc.lock;

import java.util.concurrent.TimeUnit;

public class DeadLock {
    public static void main(String[] args) {
        new Thread(new MyLock("lockA","lockB"),"T1").start();
        new Thread(new MyLock("lockB","lockA"),"T2").start();
    }
}

class MyLock implements Runnable{
    private String lockA;
    private String lockB;
    MyLock(String lockA,String lockB){
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName() + "-->lock:" + lockA + "===get-->"+lockB);

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName() + "-->lock:" + lockB + "===get-->"+lockA);
            }
        }
    }
}
