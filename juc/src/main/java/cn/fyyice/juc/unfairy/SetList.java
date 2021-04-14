package cn.fyyice.juc.unfairy;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class SetList {
    public static void main(String[] args) {
        //不安全
        Set<Object> set = new HashSet<>();
        /**
         * 解决方案：
         *  Collections.synchronizedSet(new HashSet<>());
         *  new CopyOnWriteArraySet<>()
         */
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,6));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
    }
}
