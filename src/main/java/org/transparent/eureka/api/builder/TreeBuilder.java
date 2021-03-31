package org.transparent.eureka.api.builder;

import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.Names;
import org.transparent.eureka.EurekaFactory;

public abstract class TreeBuilder<T extends JCTree> implements Builder<T> {
    protected final Names names;
    protected final EurekaFactory factory;

    protected TreeBuilder(Names names, EurekaFactory factory) {
        this.names = names;
        this.factory = factory;
    }
}