package cn.fyyice.juc.demo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SaleTicket2 {
    public static void main(String[] args) {
        //并发：多线程操作一个资源类
        Ticket2 t = new Ticket2();

        //@FunctionalInterface函数式接口，jdk 1.8 lombda表达式 (参数)->{代码块}
        new Thread(()->{ for (int i = 0; i < 30; i++) t.sale();},"A").start();
        new Thread(()->{ for (int i = 0; i < 30; i++) t.sale();},"B").start();
        new Thread(()->{ for (int i = 0; i < 30; i++) t.sale();},"C").start();
    }
}

// OOP资源类
class Ticket2{
    private Integer ticket = 20;
    Lock lock = new ReentrantLock();
    public void sale(){
        //加锁
        lock.lock();
        try {
            if (ticket > 0){
                System.out.println(Thread.currentThread().getName()+"卖出了编号:"+ticket--+"的票,剩余:"+ticket);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //解锁
            lock.unlock();
        }

    }

}