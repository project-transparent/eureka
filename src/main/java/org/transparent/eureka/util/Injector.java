package org.transparent.eureka.util;

import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.JCTree.JCClassDecl;
import org.transparent.eureka.api.builder.Builder;

public final class Injector {
    private final JCClassDecl tree;

    public Injector(JCClassDecl tree) {
        this.tree = tree;
    }

    public Injector add(Builder<? extends JCTree> builder) {
        tree.defs = tree.defs
                .append(builder.build());
        return this;
    }

    public JCClassDecl tree() {
        return tree;
    }
}
