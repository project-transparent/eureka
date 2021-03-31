package org.transparent.eureka.util;

import static com.sun.tools.javac.code.Flags.*;

public final class Modifiers {
    private Modifiers() {}

    public static long publicStaticFinal() {
        return PUBLIC | STATIC | FINAL;
    }

    public static long privateFinal() {
        return PRIVATE | FINAL;
    }
}
