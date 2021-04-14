package cn.fyyice.juc.jmm;

import java.util.concurrent.atomic.AtomicInteger;

//volatile不保证原子性
public class VDemo {

    private static volatile AtomicInteger number = new AtomicInteger();
    public static void main(String[] args) {

        for (int i = 1; i <= 20; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            },String.valueOf(i)).start();
        }

        while (Thread.activeCount() > 2){
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName()+":"+number);
    }

    public static void add (){
        //不是一个原子性操作
        /**
         * temp = number
         * number = number +1;
         * return temp
         */
//            number++;
        //AtomicInteger +1操作
        number.getAndIncrement();
    }
}
