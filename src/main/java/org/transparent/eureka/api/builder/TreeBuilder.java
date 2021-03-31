package org.transparent.eureka.api.builder;

import com.sun.tools.javac.tree.JCTree;
import org.transparent.eureka.EurekaFactory;

public abstract class TreeBuilder<T extends JCTree> implements Builder<T> {
    protected final EurekaFactory factory;

    protected TreeBuilder(EurekaFactory factory) {
        this.factory = factory;
    }
}