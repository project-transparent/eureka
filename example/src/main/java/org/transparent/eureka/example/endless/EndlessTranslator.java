package org.transparent.eureka.example.endless;

import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.JCTree.*;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Names;
import org.transparent.eureka.tree.factory.EurekaFactory;
import org.transparent.eureka.util.Annotations;
import org.transparent.eureka.util.Lists;
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
        if (Annotations.annotated(tree, Endless.class)) {
            result = factory.inject(tree)
                    .add(factory.field()
                            .mods(Flags.FINAL)
                            .type(factory.array(factory.id("String")))
                            .name("array")
                            .init(factory.array(
                                    factory.id("String"),
                                    Lists.literals(factory,
                                            "first",
                                            "second",
                                            "third"
                                    ))))
                    .add(factory.whileStat(
                            factory.literal(true),
                            factory.forEachStat(
                                    factory.field("s", factory.id("String")),
                                    factory.id("array"),
                                    factory.call(
                                            "System.out.println",
                                            factory.id("s")))
                    ))
                    .tree();
        }
    }
}
