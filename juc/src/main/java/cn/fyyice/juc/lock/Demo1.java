package cn.fyyice.juc.lock;

public class Demo1 {
    public static void main(String[] args) {
        Store store = new Store();

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
// synchronized对普通方法加锁，锁的是这个方法的调用者，本质上是同一把锁
class Store{
    public synchronized void pay(){
        System.out.println(Thread.currentThread().getName()+"-->Pay");
    }
    public synchronized void sale(){
        System.out.println(Thread.currentThread().getName()+"-->Sale");
    }
}
