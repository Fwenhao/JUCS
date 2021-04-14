package cn.fyyice.juc.pool;

import java.util.concurrent.*;

public class Demo2 {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 6, 3, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        System.out.println("当前CPU核数:"+Runtime.getRuntime().availableProcessors());
        try {
            //最大承载 maxImumPoolSize + capacity
            for (int i = 1; i <= 8; i++) {
                threadPoolExecutor.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"---> deal");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPoolExecutor.shutdown();
        }
    }
}
