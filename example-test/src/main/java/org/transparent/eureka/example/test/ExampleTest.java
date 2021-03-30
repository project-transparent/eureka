package org.transparent.eureka.example.test;

import org.transparent.eureka.example.example.Example;

@Example
public final class ExampleTest {
    public static void main(String[] args) {
        final ExampleTest test = new ExampleTest();
        System.out.println(test.generated);
        System.out.println(test.generated());
    }
}