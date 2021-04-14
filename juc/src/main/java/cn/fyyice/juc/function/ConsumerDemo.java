package cn.fyyice.juc.function;

import java.util.function.Consumer;

/**
 * 消费性接口：只有输入，没有返回值
 */
public class ConsumerDemo {
    public static void main(String[] args) {

        Consumer<String> consumer = str->{
            System.out.println(str);
        };
        consumer.accept("hello");
    }
}
