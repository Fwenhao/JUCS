package cn.fyyice.juc.bq;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(3);

        //超出容量抛出异常/移除同理
        blockingQueue.add("a");
        blockingQueue.add("b");
        blockingQueue.add("c");
        //有返回值
        System.out.println(blockingQueue.offer("d"));
    }
}
