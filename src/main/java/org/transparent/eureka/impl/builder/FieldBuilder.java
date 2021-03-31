package org.transparent.eureka.impl.builder;

import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.tree.JCTree.*;
import org.transparent.eureka.EurekaFactory;
import org.transparent.eureka.api.builder.TreeBuilder;
import org.transparent.eureka.util.Modifiers;

public class FieldBuilder extends TreeBuilder<JCVariableDecl> {
    private JCModifiers mods;
    private String name;
    private JCExpression type;
    private JCExpression value;

    public FieldBuilder(EurekaFactory factory) {
        super(factory);
        mods = factory.mods(0L);
    }

    public FieldBuilder mods(Modifiers mods) {
        this.mods = factory.mods(mods.getFlags());
        return this;
    }

    public FieldBuilder mods(long flags) {
        this.mods = factory.mods(flags);
        return this;
    }

    public FieldBuilder name(String name) {
        this.name = name;
        return this;
    }

    public FieldBuilder type(String type) {
        this.type = factory.id(type);
        return this;
    }

    public FieldBuilder type(JCExpression type) {
        this.type = type;
        return this;
    }

    public FieldBuilder type(TypeTag type) {
        this.type = factory.id(type);
        return this;
    }

    public FieldBuilder type(Class<?> clazz) {
        this.type = factory.id(clazz.getCanonicalName());
        return this;
    }

    public FieldBuilder value(Object value) {
        this.value = factory.expr(value);
        return this;
    }

    @Override
    public JCVariableDecl build() {
        return factory.maker().VarDef(mods,
                factory.names().fromString(name),
                type, value);
    }
}