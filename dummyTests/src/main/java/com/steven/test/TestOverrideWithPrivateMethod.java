package com.steven.test;

/**
 * Created with IntelliJ IDEA.
 * User: gexinlu
 * Date: 5/26/14
 * Time: 11:10 AM
 * TestOverrideWithPrivateMethod.java
 */
public class TestOverrideWithPrivateMethod {
    public static void main(String[] args) {
        BasicClass bsc = new DerivedClass("Hello world");
        bsc.testOverride();
    }
}
