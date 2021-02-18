package org.transparent.eureka.domain;

import com.sun.source.tree.Tree.Kind;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.JCTree.Tag;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.Names;

public interface EurekaObject<T extends JCTree> {
    T toTree(TreeMaker maker, Names names);

    Kind getKind();

    Tag getTag();
}