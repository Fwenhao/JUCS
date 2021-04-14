package cn.fyyice.juc.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CASDemo {

    //如果泛型是一个包装类，要注意泛型的引用问题
    //在正常的业务操作中，这里比较的都是一个个的对象
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(1,1);

    public static void main(String[] args) {

        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println("Thread 1-1 ----"+stamp);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(atomicStampedReference.compareAndSet(1, 2, stamp, stamp + 1));
            System.out.println("Thread 1-2 ----"+atomicStampedReference.getStamp());

            System.out.println(atomicStampedReference.compareAndSet(2, 1, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));
            System.out.println("Thread 1-3 ----"+atomicStampedReference.getStamp());
        },"Thread 1").start();


        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println("Thread 2-1 ----"+stamp);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread 2 修改："+atomicStampedReference.compareAndSet(1, 6, stamp, stamp + 1));
            System.out.println("Thread 2-2 ----"+atomicStampedReference.getStamp());
        },"Thread 2").start();
    }
}
