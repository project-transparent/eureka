package org.transparent.eureka.util;

import com.sun.tools.javac.tree.JCTree.JCMethodDecl;

import java.lang.annotation.Annotation;

public final class Annotations {
    private Annotations() {}

    public static boolean annotated(JCMethodDecl tree, Class<? extends Annotation> clazz) {
        return tree.mods.annotations
                .stream()
                .anyMatch(annotation -> annotation.type.tsym
                        .getQualifiedName()
                        .contentEquals(
                                clazz.getCanonicalName()
                        ));
    }
}
