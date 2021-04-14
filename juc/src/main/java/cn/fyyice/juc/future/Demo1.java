package cn.fyyice.juc.future;

import cn.fyyice.juc.pc.A;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 异步调用future
 *  1. 成功回调
 *  2. 失败回调
 *  3. 异步执行
 */
public class Demo1 {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(1);
        atomicInteger.compareAndSet(1,2);
    }
}
