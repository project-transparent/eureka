package org.transparent.eureka.example.endless;

import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.JCTree.*;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.Names;
import org.transparent.eureka.EurekaFactory;
import org.transparent.lucent.transform.LucentTranslator;

import javax.lang.model.element.Element;

public final class EndlessTranslator extends LucentTranslator {
    private final EurekaFactory factory;

    public EndlessTranslator(Names names, TreeMaker factory) {
        super(names, factory);
        this.factory = new EurekaFactory(names, factory);
    }

    @Override
    public void translate(JCTree tree, Element element) {
        tree.accept(this);
    }

    @Override
    public void visitMethodDef(JCMethodDecl tree) {
        super.visitMethodDef(tree);

    }
}
