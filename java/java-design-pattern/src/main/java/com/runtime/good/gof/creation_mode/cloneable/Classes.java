package com.runtime.good.gof.creation_mode.cloneable;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/6/13 9:20
 * @Description
 */


public class Classes implements Cloneable {

    private int classId;//基本类型

    private String className;//引用类型


    public Classes(int classId, String className) {
        this.classId = classId;
        this.className = className;
    }

    public Classes() {
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }


    @Override
    public String toString() {
        return "Classes{" +
                "classId=" + classId +
                ", className='" + className + '\'' +
                '}';
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
