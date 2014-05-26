package com.steven.test;

/**
 * Created with IntelliJ IDEA.
 * User: gexinlu
 * Date: 5/26/14
 * Time: 11:13 AM
 * DerivedClass.java
 */
public class DerivedClass extends BasicClass {
    private String derivedClassName;

    public DerivedClass(String derivedClassName) {
        super(derivedClassName + "- construct from derived class");
        this.derivedClassName = derivedClassName;
    }

    @Override
    public void testOverride() {
        System.out.println("from derived class");
    }
}
