package cn.fyyice.juc.demo;

public class SaleTicket {
    public static void main(String[] args) {
        //并发：多线程操作一个资源类
        Ticket t = new Ticket();

        //@FunctionalInterface函数式接口，jdk 1.8 lombda表达式 (参数)->{代码块}
        new Thread(()->{ for (int i = 0; i < 50; i++) t.sale();},"甲").start();
        new Thread(()->{ for (int i = 0; i < 50; i++) t.sale();},"乙").start();
        new Thread(()->{ for (int i = 0; i < 50; i++) t.sale();},"丙").start();
    }
}

// OOP资源类
class Ticket{
    private Integer ticket = 20;

    public synchronized void sale(){
        if (ticket > 0){
            System.out.println(Thread.currentThread().getName()+"卖出了编号:"+ticket--+"的票,剩余:"+ticket);
        }
    }

}