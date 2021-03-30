package org.transparent.eureka.util;

import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.JCTree.*;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.List;
import org.transparent.eureka.api.builder.Builder;
import org.transparent.eureka.tree.builder.BlockBuilder;
import org.transparent.eureka.tree.builder.FieldBuilder;

public abstract class Injector<T extends JCTree> {
    protected final T tree;

    public Injector(T tree) {
        this.tree = tree;
    }

    public T tree() {
        return tree;
    }

    public static class ClassInjector extends Injector<JCClassDecl> {
        public ClassInjector(JCClassDecl tree) {
            super(tree);
        }

        public ClassInjector add(Builder<? extends JCTree> builder) {
            return add(builder.build());
        }

        public ClassInjector add(JCTree tree) {
            this.tree.defs = this.tree.defs
                    .append(tree);
            return this;
        }
    }

    public static class MethodInjector extends Injector<JCMethodDecl> {
        private final TreeMaker maker;

        public MethodInjector(JCMethodDecl tree, TreeMaker maker) {
            super(tree);
            this.maker = maker;
        }

        public MethodInjector add(FieldBuilder builder) {
            return add(builder.build());
        }

        public MethodInjector add(JCStatement statement) {
            tree.body.stats = tree.body.stats
                    .append(statement);
            return this;
        }
    }
}
