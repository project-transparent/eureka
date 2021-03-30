package org.transparent.eureka.example.test;

import org.transparent.eureka.example.Example;

@Example
public final class Test {
    public static void main(String[] args) {
        final Test test = new Test();
        System.out.println(test.generated);
        System.out.println(test.generated());
    }
}