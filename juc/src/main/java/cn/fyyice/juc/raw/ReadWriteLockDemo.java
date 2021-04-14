package cn.fyyice.juc.raw;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 又称为   独占锁（写锁）    一次只能被一个线程占有
 *          共享锁（读锁）    多个线程可以同时占有
 * ReadWriteLock
 * 读-读  可以共存
 * 读-写  不能共存
 * 写-写  不能共存
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
//        MyCache myCache = new MyCache();
        MyLockCache myCache = new MyLockCache();
        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(()->{
                myCache.put(temp+"", UUID.randomUUID().toString().substring(0,5));
            },String.valueOf(i)).start();
        }

        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(()->{
                myCache.get(temp+"");
            },String.valueOf(i)).start();
        }
    }
}

class MyLockCache{

    private volatile Map<String,Object> map = new HashMap<>();
    //读写锁：更加细粒度的控制
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    //在写操作的时候希望同一时间，只有一个线程进行写操作，避免覆盖
    public void put(String key,Object value){
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"写入："+value);
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"写入完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    //读的时候加锁是为了防止   在读的时候发生写操作
    public void get(String key){
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"读取："+key);
            Object obj = map.get(key);
            System.out.println("----------"+Thread.currentThread().getName()+"读取完成---------");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

}

/**
 * 自定义缓存
 */
class MyCache{

    private volatile Map<String,Object> map = new HashMap<>();

    public void put(String key,Object value){
        System.out.println(Thread.currentThread().getName()+"写入："+value);
        map.put(key,value);
        System.out.println("----------"+Thread.currentThread().getName()+"写入完成---------");
    }

    public void get(String key){
        System.out.println(Thread.currentThread().getName()+"读取："+key);
        Object obj = map.get(key);
        System.out.println("----------"+Thread.currentThread().getName()+"读取完成---------");
    }

}
