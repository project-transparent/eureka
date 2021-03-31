package org.transparent.eureka.example.example;

import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.JCTree.*;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.Names;
import org.transparent.eureka.EurekaFactory;
import org.transparent.lucent.transform.LucentTranslator;

import javax.lang.model.element.Element;

import static org.transparent.eureka.util.Modifiers.PRIVATE_FINAL;

public final class ExampleTranslator extends LucentTranslator {
    private final EurekaFactory factory;

    public ExampleTranslator(Names names, TreeMaker factory) {
        super(names, factory);
        this.factory = new EurekaFactory(names, factory);
    }

    @Override
    public void translate(JCTree tree, Element element) {
        tree.accept(this);
    }

    @Override
    public void visitClassDef(JCClassDecl tree) {
        super.visitClassDef(tree);
        tree.defs = tree.defs.append(factory.array()
                .mods(PRIVATE_FINAL)
                .type(String.class)
                .name("myArray")
                .elements("first", "second")
                .variable());
        tree.defs = tree.defs.append(factory.field()
                .mods(PRIVATE_FINAL)
                .type(String.class)
                .name("myField")
                .value("This is my field.")
                .build());
        result = tree;
    }
}