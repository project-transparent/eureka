package org.transparent.eureka.util;

import com.sun.tools.javac.tree.JCTree.*;

import java.lang.annotation.Annotation;

public final class Annotations {
    private Annotations() {}

    public static boolean isAnnotated(JCClassDecl tree) {
        return tree.mods.annotations.isEmpty();
    }

    public static boolean isAnnotated(JCClassDecl tree, Class<? extends Annotation> clazz) {
        return tree.mods.annotations
                .stream()
                .anyMatch(annotation -> annotation.type.tsym
                        .getQualifiedName()
                        .contentEquals(clazz
                                .getCanonicalName()));
    }

    public static boolean isAnnotated(JCVariableDecl tree) {
        return tree.mods.annotations.isEmpty();
    }

    public static boolean isAnnotated(JCVariableDecl tree, Class<? extends Annotation> clazz) {
        return tree.mods.annotations
                .stream()
                .anyMatch(annotation -> annotation.type.tsym
                        .getQualifiedName()
                        .contentEquals(clazz
                                .getCanonicalName()));
    }

    public static boolean isAnnotated(JCMethodDecl tree) {
        return tree.mods.annotations.isEmpty();
    }

    public static boolean isAnnotated(JCMethodDecl tree, Class<? extends Annotation> clazz) {
        return tree.mods.annotations
                .stream()
                .anyMatch(annotation -> annotation.type.tsym
                        .getQualifiedName()
                        .contentEquals(clazz
                                .getCanonicalName()));
    }
}
