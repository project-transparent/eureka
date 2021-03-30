package org.transparent.eureka.example.example;

import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.JCTree.*;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.Names;
import org.transparent.eureka.tree.builder.BlockBuilder;
import org.transparent.eureka.tree.builder.MethodBuilder;
import org.transparent.eureka.tree.factory.EurekaFactory;
import org.transparent.lucent.transform.LucentTranslator;

import javax.lang.model.element.Element;

public final class ExampleTranslator extends LucentTranslator {
    private final EurekaFactory eureka;

    public ExampleTranslator(Names names, TreeMaker factory) {
        super(names, factory);
        this.eureka = new EurekaFactory(names, factory);
    }

    @Override
    public void translate(JCTree tree, Element element) {
        tree.accept(this);
    }

    @Override
    public void visitClassDef(JCClassDecl tree) {
        result = eureka
                .inject(tree)
                .add(eureka.field()
                        .mods(Flags.PRIVATE | Flags.FINAL)
                        .type("String")
                        .name("generated")
                        .init("A generated string."))
                .add(eureka.method()
                        .mods(Flags.PUBLIC)
                        .returns(TypeTag.INT)
                        .name("generated")
                        .body(eureka.block()
                                .ifStat(
                                        eureka.binary(Tag.NE,
                                                eureka.id("generated"),
                                                eureka.literal()),
                                        eureka.block()
                                                .returnStat(1))
                                .returnStat(0)))
                .tree();
    }
}