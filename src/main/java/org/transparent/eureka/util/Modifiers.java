package org.transparent.eureka.util;

import static com.sun.tools.javac.code.Flags.*;

public enum Modifiers {
    PRIVATE_STATIC_FINAL    (PRIVATE | STATIC | FINAL),
    PUBLIC_STATIC_FINAL     (PUBLIC | STATIC | FINAL),
    PRIVATE_FINAL           (PRIVATE | FINAL),
    PUBLIC_FINAL            (PUBLIC | FINAL),
    PRIVATE_STATIC          (PRIVATE | STATIC),
    PUBLIC_STATIC           (PUBLIC | STATIC),
    ;

    private final long flags;

    Modifiers(long flags) {
        this.flags = flags;
    }

    public long getFlags() {
        return flags;
    }
}