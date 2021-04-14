package cn.fyyice.juc.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * enum : 本身也是一个class类，jkd1.5
 *
 */
public enum EnumSingle {

   INSTANCE;

   public static EnumSingle getInstance(){
       return INSTANCE;
   }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        EnumSingle single = EnumSingle.getInstance();
        Constructor<EnumSingle> constructor = EnumSingle.class.getDeclaredConstructor(String.class,int.class);
        constructor.setAccessible(true);
        EnumSingle enumSingle = constructor.newInstance();
        System.out.println(single == enumSingle);
    }
}
