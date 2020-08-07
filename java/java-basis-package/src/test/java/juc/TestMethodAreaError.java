package juc;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;


/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/8/3 14:24
 * @Description
 */
public class TestMethodAreaError {
    public static void main(String[] args) {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOM.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {

                @Override
                public Object intercept(Object obj, Method arg1, Object[] args, MethodProxy proxy) throws Throwable {
                    return proxy.invokeSuper(obj, args);
                }
            });
            OOM oom = (OOM) enhancer.create();
            oom.sayHello("Kevin LUAN");
        }
    }

    static class OOM {
        public String sayHello(String str) {
            return "HI " + str;
        }
    }
}
