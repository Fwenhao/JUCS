package cn.fyyice.juc.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Demo2 {
    public static void main(String[] args) {
        Store2 store = new Store2();

        new Thread(()->{
            store.pay();
            store.sale();
        },"A").start();

        new Thread(()->{
            store.pay();
            store.sale();
        },"B").start();

    }
}

//lock锁。与synchronized体现的效果是一样的，但是本质上是有区别的。
//在这里lock锁有两把锁，在使用的时候必须配对，否则会出现死锁的情况
class Store2{
    private Lock lock = new ReentrantLock();
    public void pay(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"-->Pay");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void sale(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"-->Sale");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}