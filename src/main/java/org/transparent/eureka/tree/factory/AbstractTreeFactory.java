package org.transparent.eureka.tree.factory;

import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;
import org.transparent.eureka.api.factory.TreeFactory;

public abstract class AbstractTreeFactory implements TreeFactory {
    protected final Names names;
    protected final TreeMaker factory;

    protected AbstractTreeFactory(Names names, TreeMaker factory) {
        this.names = names;
        this.factory = factory;
    }
}