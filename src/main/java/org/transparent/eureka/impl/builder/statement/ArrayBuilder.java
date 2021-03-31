package org.transparent.eureka.impl.builder.statement;

import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.tree.JCTree.*;
import com.sun.tools.javac.util.List;
import org.transparent.eureka.EurekaFactory;
import org.transparent.eureka.api.builder.TreeBuilder;
import org.transparent.eureka.impl.builder.FieldBuilder;
import org.transparent.eureka.util.Lists;
import org.transparent.eureka.util.Modifiers;

public class ArrayBuilder extends TreeBuilder<JCNewArray> {
    private long mods;
    private JCExpression type;
    private String name;
    private List<JCExpression> elements = List.nil();
    private List<JCExpression> dimensions = List.nil();

    public ArrayBuilder(EurekaFactory factory) {
        super(factory);
        this.mods =  0L;
    }

    public ArrayBuilder mods(Modifiers mods) {
        this.mods = mods.getFlags();
        return this;
    }

    public ArrayBuilder mods(long flags) {
        this.mods = flags;
        return this;
    }

    public ArrayBuilder type(String type) {
        this.type = factory.id(type);
        return this;
    }

    public ArrayBuilder type(JCExpression type) {
        this.type = type;
        return this;
    }

    public ArrayBuilder type(TypeTag type) {
        this.type = factory.id(type);
        return this;
    }

    public ArrayBuilder type(Class<?> clazz) {
        this.type = factory.id(clazz.getCanonicalName());
        return this;
    }

    public ArrayBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ArrayBuilder elements(Object... elements) {
        this.elements = Lists.literalList(factory, elements);
        return this;
    }

    public ArrayBuilder elements(JCExpression... elements) {
        this.elements = List.from(elements);
        return this;
    }

    public ArrayBuilder dimensions(Object... dimensions) {
        this.dimensions = Lists.literalList(factory, dimensions);
        return this;
    }

    public ArrayBuilder dimensions(JCExpression... dimensions) {
        this.dimensions = List.from(dimensions);
        return this;
    }

    @Override
    public JCNewArray build() {
        return factory.maker().NewArray(type, dimensions, elements);
    }

    public JCVariableDecl variable() {
        return new FieldBuilder(factory)
                .mods(mods)
                .type(factory.maker().TypeArray(type))
                .name(name)
                .value(build())
                .build();
    }
}
