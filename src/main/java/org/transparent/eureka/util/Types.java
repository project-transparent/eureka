package org.transparent.eureka.util;

import com.sun.tools.javac.code.TypeTag;

public enum Types {
    VOID(TypeTag.VOID),
    BOOLEAN(TypeTag.BOOLEAN),
    BYTE(TypeTag.BYTE),
    SHORT(TypeTag.SHORT),
    INT(TypeTag.INT),
    LONG(TypeTag.LONG),
    FLOAT(TypeTag.FLOAT),
    DOUBLE(TypeTag.DOUBLE),
    CHAR(TypeTag.CHAR)
    ;

    private final TypeTag tag;

    Types(TypeTag tag) {
        this.tag = tag;
    }

    public TypeTag getTag() {
        return tag;
    }
}