package cn.fyyice.juc.function;

import java.util.function.Function;

public class FunctionDemo {
    public static void main(String[] args) {
//        Function<String, String> f = new Function<String, String>() {
//            @Override
//            public String apply(String str) {
//                return str;
//            }
//        };
        Function<String, String> f = (str)-> {return str;};
        System.out.println(f.apply("hello world"));
    }
}
