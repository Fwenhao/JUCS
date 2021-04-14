package cn.fyyice.juc.stream;

import java.util.Arrays;
import java.util.List;

public class StreamTest {
    public static void main(String[] args) {
        /**
         * 从以下用户数据中筛选
         * 1.id为偶数
         * 2.年龄大于等于20
         * 3.姓名转为大写
         * 4.输出第一条用户记录
         */
        User user1 = new User(1,18,"a");
        User user2 = new User(2,29,"b");
        User user3 = new User(3,20,"c");
        User user4 = new User(4,19,"d");
        User user5 = new User(5,23,"e");

        List<User> userList = Arrays.asList(user1,user2,user3,user4,user5);
        //计算交给stream流
        //lambda表达式，链式编程，函数式接口，Stream流计算
        userList.stream()
                .filter(user -> {return user.getId() %2==0;})
                .filter(user -> {return user.getAge() >= 20;})
                .map((user ->{return user.getUsername().toUpperCase();}))
                .limit(1)
                .forEach(System.out::println);
    }
}
