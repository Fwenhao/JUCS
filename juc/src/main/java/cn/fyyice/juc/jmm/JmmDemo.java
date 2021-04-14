package cn.fyyice.juc.jmm;

import java.util.concurrent.TimeUnit;

public class JmmDemo {

    // 不加volatile 程序会出现死循环，加了  volatile 可以保证可见性
    private static volatile int number = 0;

    public static void main(String[] args) {

        new Thread(()->{
            while (number == 0) {

            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        number = 1;
        System.out.println(number);
    }
}
