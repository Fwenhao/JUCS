package cn.fyyice.juc.single;

public class Hungry {
    /**
     * 可能会浪费空间，在类加载的时候就把所有东西给创建出来了
     */
    private Hungry(){}

    private final static Hungry HUNGRY = new Hungry();

    public static Hungry getInstance(){
        return HUNGRY;
    }

}
