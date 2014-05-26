package com.steven.test;

/**
 * Created with IntelliJ IDEA.
 * User: gexinlu
 * Date: 5/26/14
 * Time: 11:10 AM
 * BasicClass.java
 */
public class BasicClass {
    private String testName;

    public BasicClass(String testName) {
        this.testName = testName;
    }

    public void testOverride() {
        testPrivateMethod();
        System.out.println("Basic class testOverride method");
    }

    private void testPrivateMethod() {
        System.out.println("Basic class testPrivateMethod method");
    }
}
