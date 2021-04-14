package cn.fyyice.juc.unfairy;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class MapTest {
    public static void main(String[] args) {
        //map是这样用的？默认等价于什么？
        Map<String, String> map = new ConcurrentHashMap<>();
        //加载因子，初始化容量
        /**
         *  new ConcurrentHashMap<>();
         *  Collections.synchronizedMap(new HashMap<>());
         */
        for (int i = 1; i < 20; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,5));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }
}
