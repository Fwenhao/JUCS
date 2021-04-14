package cn.fyyice.juc.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

//懒汉单例
public class Lazy {
    private Lazy(){
        synchronized (Lazy.class){
            if (lazy != null){
                throw new RuntimeException("别搞骚操作");
            }
            System.out.println(Thread.currentThread().getName()+"-> Hello");
        }

    }

    private static volatile Lazy lazy;

    public static Lazy getInstance() {
        //由于在多线程并发下不安全，加入双重检测锁模式  DCL懒汉式
        if (lazy == null){
            synchronized (Lazy.class){
                if (lazy == null){
                    /**
                     * 不是一个原子性操作
                     *  1.分配内存空间
                     *  2.执行构造方法，初始化对象
                     *  3.把这个对象指向这个空间
                     *  （考虑指令重排）
                     *  123
                     *  132  A
                     *       B  //此时Lazy还没有完成构造，导致 B 线程在判断的时候，lazy ！= null，直接走return，空间是一片虚无，所以需要加入volatile
                     *
                     */
                    lazy = new Lazy();
                }
            }
        }
        return lazy;
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
//        for (int i = 1; i <= 10; i++) {
//            new Thread(()->{
//                Lazy.getInstance();
//            }).start();
//        }
        Constructor<Lazy> constructor = Lazy.class.getDeclaredConstructor(null);
        constructor.setAccessible(true);
        Lazy instance2 = Lazy.getInstance();
        Lazy instance1 = constructor.newInstance();

        System.out.println(instance1);
        System.out.println(instance2);
    }
}
