package org.transparent.eureka.example.endless;

import com.sun.tools.javac.tree.JCTree.*;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.Names;
import org.transparent.eureka.EurekaFactory;
import org.transparent.lucent.transform.LucentTranslator;
import org.transparent.lucent.transform.LucentValidator;

public final class EndlessTranslator extends LucentTranslator {
    private final EurekaFactory factory;

    public EndlessTranslator(Names names, TreeMaker factory, LucentValidator validator) {
        super(names, factory, validator);
        this.factory = new EurekaFactory(names, factory);
    }

    @Override
    public void visitMethodDef(JCMethodDecl tree) {
        super.visitMethodDef(tree);

    }
}
