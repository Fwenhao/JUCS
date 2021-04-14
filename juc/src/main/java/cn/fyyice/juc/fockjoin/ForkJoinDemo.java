package cn.fyyice.juc.fockjoin;

import java.util.concurrent.RecursiveTask;

/**
 * 求和计算
 * FockJoin
 * 1. 通过ForkJoinPool来执行
 * 2. 计算任务:execute(ForkJoinTask<?> task)
 * 3. 计算类集成类 RecursiveTask<V>
 */
public class ForkJoinDemo extends RecursiveTask<Long> {

    private Long start = 1L;
    private Long end = 10_0000_0000L;
    private Long temp = 10000L;

    public ForkJoinDemo(Long start, Long end) {
        this.start = start;
        this.end = end;
    }
    @Override
    protected Long compute() {
        if ((end-start) < temp){
            Long sum = 0L;
            for (Long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        }else {
            long middle = (start+end)/2;
            ForkJoinDemo task1 = new ForkJoinDemo(start,middle);
            //拆分任务，把任务压入线程队列
            task1.fork();
            ForkJoinDemo task2 = new ForkJoinDemo(middle+1,end);
            //拆分任务，把任务压入线程队列
            task2.fork();

            return task1.join()+task2.join();
        }
    }
}
