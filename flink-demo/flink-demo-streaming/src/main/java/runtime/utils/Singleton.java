package runtime.utils;


/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/6/11 13:20
 * @Description
 */
public enum Singleton {

    // 属性
    SINGLETON;


    private static class InnerClass {
        private static final SingletonClass SINGLETON = new SingletonClass();
    }

    public SingletonClass getInstance() {
        return InnerClass.SINGLETON;
    }


}



