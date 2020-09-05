package com.test;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/8/31 14:57
 * @Description
 */
public class finalize {
    public static void main(String[] args) {
        PhantomReference<Object> reference = new PhantomReference<>(new Object(), new ReferenceQueue<>());

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
