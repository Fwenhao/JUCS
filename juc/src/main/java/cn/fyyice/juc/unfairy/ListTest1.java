package cn.fyyice.juc.unfairy;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

//java.util.ConcurrentModificationException 并发修改异常
public class ListTest1 {
    public static void main(String[] args) {
        /**
         *  并发下，list是不安全的
         *  解决方案：
         *  1.List<String> list = Vector()<>集合类
         *  2.List<String> list = Collections.synchronizedList(new ArrayList<>())
         *  3.List<String> list = new CopyOnWriteArrayList<>();
         *      CopyOnWrite 写入时复制    COW 优化策略
         *      多个线程调用的时候，list读取是固定的，但是在写入的时候可能会被后面的线程给覆盖掉
         *      在写入的时候避免覆盖，造成数据问题
         *      读写分离
         */
        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 1; i <= 10; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,6));
                System.out.println(list);
            },String.valueOf(i)).start();

        }
    }
}
