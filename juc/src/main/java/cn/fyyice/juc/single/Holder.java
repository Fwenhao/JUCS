package cn.fyyice.juc.single;

/**
 * 静态内部类
 */
public class Holder {

    private Holder(){}

    public static Holder getInstance(){
        return innerClass.holder;
    }

    public static class innerClass{
        private static final Holder holder = new Holder();
    }
}
