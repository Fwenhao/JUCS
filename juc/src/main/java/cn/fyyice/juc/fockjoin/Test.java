package cn.fyyice.juc.fockjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class Test {

    private final static Long start = 1L;
    private final static Long end = 10_0000_0000L;

    public static void main(String[] args) {
        test1();
        System.out.println("----------------------------------------------------");
        test2();
        System.out.println("----------------------------------------------------");
        test3();
    }

    public static void test1(){
        long startTime = System.currentTimeMillis();
        Long sum = 0L;
        for (Long i = start; i <= end; i++) {
            sum += i;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("普通累加求和结果："+sum+"\n求和时间："+(endTime-startTime));
    }

    public static void test2(){
        try {
            long startTime = System.currentTimeMillis();
            ForkJoinPool forkJoinPool = new ForkJoinPool();
//        forkJoinPool.execute(new ForkJoinDemo(start,end));        执行任务没有结果，所以这里使用sumbit
            ForkJoinTask<Long> result = forkJoinPool.submit(new ForkJoinDemo(start, end));
            Long sum = result.get();
            long endTime = System.currentTimeMillis();
            System.out.println("ForkJoin求和结果："+sum+"\n求和时间："+(endTime-startTime));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    //Stream 并行流
    public static void test3(){
        long startTime = System.currentTimeMillis();
        //range() rangeClosed(]
        long result = LongStream.rangeClosed(start, end).parallel().reduce(0, Long::sum);
        long endTime = System.currentTimeMillis();
        System.out.println("Stream 并行流求和结果："+result+"\n求和时间："+(endTime-startTime));
    }
}
