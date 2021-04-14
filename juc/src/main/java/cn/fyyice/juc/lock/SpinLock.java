package cn.fyyice.juc.lock;

import java.util.concurrent.atomic.AtomicReference;

public class SpinLock {
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    //加锁
    public void lock(){
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName()+"--->myLock");
        do{

        }while (!atomicReference.compareAndSet(null,thread));
    }

    //解锁
    public void unlock(){
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName()+"--->myUnLock");
        atomicReference.compareAndSet(thread,null);
    }
}
