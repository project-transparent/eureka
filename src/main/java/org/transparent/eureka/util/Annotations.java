package org.transparent.eureka.util;

import com.sun.tools.javac.tree.JCTree.JCMethodDecl;

import java.lang.annotation.Annotation;

public final class Annotations {
    private Annotations() {}

    public static boolean annotated(JCMethodDecl tree, Class<? extends Annotation> annotation) {
        return tree.mods.annotations
                .stream()
                .anyMatch(anno -> anno.type.tsym
                        .getQualifiedName()
                        .contentEquals(
                                annotation.getCanonicalName()
                        ));
    }
}
