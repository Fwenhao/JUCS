package cn.fyyice.juc.function;

import java.util.function.Supplier;

/**
 * 供给型接口：没有参数，只有返回值
 */
public class SupplierDemo {
    public static void main(String[] args) {
        Supplier supplier = ()->{return 1024;};
        System.out.println(supplier.get());
    }
}
