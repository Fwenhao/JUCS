package cn.fyyice.juc.pool;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Executor  工具类，3大方法
public class Demo1 {
    public static void main(String[] args) {
        //创建单个线程池
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        //创建一个固定大小的线程池
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(6);
        //创建一个可伸缩的线程池
        ExecutorService cachedThreadPool =Executors.newCachedThreadPool();

        try {
            for (int i = 0; i < 10; i++) {
                //使用了线程池之后，使用线程池来创建线程

                //始终只有一个线程执行
//                singleThreadExecutor.execute(()->{
//                    System.out.println(Thread.currentThread().getName()+"----> start");
//                });

                //固定线程数量执行
//                fixedThreadPool.execute(()->{
//                    System.out.println(Thread.currentThread().getName()+"----> start");
//                });

                //根据cpu支持线程数量执行
                cachedThreadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"----> start");
                });

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //线程池用完，程序结束，关闭线程池
            //singleThreadExecutor.shutdown();
            //fixedThreadPool.shutdown();
            cachedThreadPool.shutdown();
        }
    }
}
