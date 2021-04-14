package cn.fyyice.juc.lock;

import java.util.concurrent.TimeUnit;

public class SpinLockTest {
    public static void main(String[] args) {

        SpinLock lock = new SpinLock();

        new Thread(()->{
            lock.lock();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        },"A").start();

        new Thread(()->{
            lock.lock();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        },"B").start();
    }
}
