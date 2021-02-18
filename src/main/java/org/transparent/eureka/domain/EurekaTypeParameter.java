package org.transparent.eureka.domain;

import com.sun.source.tree.Tree;
import com.sun.tools.javac.tree.JCTree.*;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.Names;

public class EurekaTypeParameter implements EurekaObject<JCTypeParameter> {
    private final String name;

    public EurekaTypeParameter(String name) {
        this.name = name;
    }

    // TODO
    @Override
    public JCTypeParameter toTree(TreeMaker maker, Names names) {
        return null;
    }

    @Override
    public Tree.Kind getKind() {
        return Tree.Kind.TYPE_PARAMETER;
    }

    @Override
    public Tag getTag() {
        return Tag.TYPEPARAMETER;
    }

    public String getName() {
        return name;
    }
}
