package org.transparent.eureka.tree.builder;

import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;
import org.transparent.eureka.api.builder.Builder;

public abstract class EurekaBuilder<T extends JCTree> implements Builder<T> {
    protected final Names names;
    protected final TreeMaker factory;

    protected EurekaBuilder(Names names, TreeMaker factory) {
        this.names = names;
        this.factory = factory;
    }

    protected Name toName(String name) {
        return names.fromString(name);
    }
}