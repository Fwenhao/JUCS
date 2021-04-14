package cn.fyyice.juc.function;

import java.util.function.Predicate;

/**
 * 断定性接口，返回值只能是boolean
 */
public class PredicateDemo {
    public static void main(String[] args) {
//        Predicate<String> predicate = new Predicate<String>() {
//            @Override
//            public boolean test(String str) {
//                return str.isEmpty();
//            }
//        };
        Predicate<String> p = str->{return str.isEmpty();};
        System.out.println(p.test("123"));

    }
}
