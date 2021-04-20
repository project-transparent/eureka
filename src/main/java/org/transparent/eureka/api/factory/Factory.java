package org.transparent.eureka.api.factory;

import org.transparent.eureka.api.builder.TreeBuilder;

import static com.sun.tools.javac.tree.JCTree.*;

public interface Factory {
    TreeBuilder<JCVariableDecl> field();

    TreeBuilder<JCMethodDecl> method();

    JCExpression expression(String s);

    JCStatement statements(String... strings);
}