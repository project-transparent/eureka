package org.transparent.eureka.impl.builder;

import com.sun.tools.javac.tree.JCTree.*;
import org.transparent.eureka.EurekaFactory;
import org.transparent.eureka.api.builder.TreeBuilder;
import org.transparent.eureka.util.Modifiers;
import org.transparent.eureka.util.Types;

public class FieldBuilder extends TreeBuilder<JCVariableDecl> {
    private long mods;
    private String name;
    private JCExpression type;
    private JCExpression value;

    public FieldBuilder(EurekaFactory factory) {
        super(factory);
        mods = 0L;
    }

    public FieldBuilder mods(Modifiers mods) {
        this.mods = mods.getFlags();
        return this;
    }

    public FieldBuilder mods(long flags) {
        this.mods = flags;
        return this;
    }

    public FieldBuilder name(String name) {
        this.name = name;
        return this;
    }

    public FieldBuilder type(String type) {
        boolean isArray = false;
        if (type.endsWith("[]")) {
            isArray = true;
            type = type.substring(0, type.indexOf('['));
        }
        final JCExpression id = factory.utils.id(type);
        this.type = (!isArray)
                ? id
                : factory.maker.TypeArray(id);
        return this;
    }

    public FieldBuilder type(JCExpression type) {
        this.type = type;
        return this;
    }

    public FieldBuilder type(Types type) {
        this.type = factory.utils.id(type.getTag());
        return this;
    }

    public FieldBuilder type(Class<?> clazz) {
        this.type = factory.utils.id(clazz.getCanonicalName());
        return this;
    }

    public FieldBuilder value(String expr) {
        this.value = factory.expression(expr);
        return this;
    }

    public FieldBuilder value(JCLiteral literal) {
        this.value = literal;
        return this;
    }

    @Override
    public JCVariableDecl build() {
        return factory.maker.VarDef(
                factory.utils.mods(mods),
                factory.utils.name(name),
                type, value
        );
    }
}