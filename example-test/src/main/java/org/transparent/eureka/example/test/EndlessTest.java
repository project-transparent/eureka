package org.transparent.eureka.example.test;

import org.transparent.eureka.example.endless.Endless;

public class EndlessTest {
    public static void main(String[] args) {
        endless();
    }

    @Endless
    private static void endless() {}
}