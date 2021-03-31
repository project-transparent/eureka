package org.transparent.eureka.impl.builder;

import com.sun.tools.javac.tree.JCTree.*;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.Names;
import org.transparent.eureka.EurekaFactory;
import org.transparent.eureka.api.builder.TreeBuilder;

public class ArrayBuilder extends TreeBuilder<JCVariableDecl> {
    public ArrayBuilder(Names names, EurekaFactory factory) {
        super(names, factory);
    }

    @Override
    public JCVariableDecl build() {
        return null;
    }
}
