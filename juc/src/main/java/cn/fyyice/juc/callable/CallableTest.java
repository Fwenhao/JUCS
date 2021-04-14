package cn.fyyice.juc.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // new Thread(new Runnable() {}).start();
        // new Thread(new FutureTask<V>()).start());

        MyThread thread = new MyThread();
        //适配类
        FutureTask<String> task = new FutureTask<>(thread);
        new Thread(task,"A").start();
        new Thread(task,"C").start();
        new Thread(task,"D").start();
        //这个get方法可能会出现阻塞！把他放到最后、或者使用异步通信来处理
        System.out.println(task.get());
    }
}

class MyThread implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("成功进入Callable方法");
        return Thread.currentThread().getName()+"--->hello";
    }
}