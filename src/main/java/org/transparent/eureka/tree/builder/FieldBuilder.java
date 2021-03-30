package org.transparent.eureka.tree.builder;

import com.sun.tools.javac.tree.JCTree.*;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.Names;

public class FieldBuilder extends EurekaBuilder<JCVariableDecl> {
    private JCModifiers mods;
    private String name;
    private JCExpression type;
    private JCExpression init;

    public FieldBuilder(Names names, TreeMaker factory) {
        super(names, factory);
        mods = factory.Modifiers(0L);
    }

    public FieldBuilder mods(JCModifiers mods) {
        this.mods = mods;
        return this;
    }

    public FieldBuilder mods(long flags) {
        this.mods = factory.Modifiers(flags);
        return this;
    }

    public FieldBuilder name(String name) {
        this.name = name;
        return this;
    }

    public FieldBuilder type(JCExpression type) {
        this.type = type;
        return this;
    }

    public FieldBuilder type(String type) {
        this.type = factory.Ident(toName(type));
        return this;
    }

    public FieldBuilder init(JCExpression init) {
        this.init = init;
        return this;
    }

    public FieldBuilder init(Object init) {
        return init(factory.Literal(init));
    }

    @Override
    public JCVariableDecl build() {
        return factory.VarDef(mods,
                toName(name),
                type, init);
    }
}