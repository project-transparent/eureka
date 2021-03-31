package org.transparent.eureka.impl.builder;

import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.tree.JCTree.*;
import com.sun.tools.javac.util.List;
import org.transparent.eureka.EurekaFactory;
import org.transparent.eureka.api.builder.TreeBuilder;
import org.transparent.eureka.util.Modifiers;

public class MethodBuilder extends TreeBuilder<JCMethodDecl> {
    private JCModifiers mods;
    private String name;
    private JCExpression type;
    private List<JCTypeParameter> typeParameters = List.nil();
    private JCVariableDecl receiver;
    private List<JCVariableDecl> parameters = List.nil();
    private List<JCExpression> exceptions = List.nil();
    private JCBlock body;
    private JCExpression value;

    public MethodBuilder(EurekaFactory factory) {
        super(factory);
        mods = factory.mods(0L);
        type = factory.literal();
        body = factory.maker().Block(0L, List.nil());
    }

    public MethodBuilder mods(Modifiers mods) {
        this.mods = factory.mods(mods.getFlags());
        return this;
    }

    public MethodBuilder mods(long flags) {
        this.mods = factory.mods(flags);
        return this;
    }

    public MethodBuilder name(String name) {
        this.name = name;
        return this;
    }

    public MethodBuilder type(String type) {
        this.type = factory.id(type);
        return this;
    }

    public MethodBuilder type(JCExpression type) {
        this.type = type;
        return this;
    }

    public MethodBuilder type(TypeTag returnType) {
        this.type = factory.id(returnType);
        return this;
    }

    public MethodBuilder typeParams(JCTypeParameter... typeParameters) {
        this.typeParameters = List.from(typeParameters);
        return this;
    }

    public MethodBuilder receiver(JCVariableDecl receiver) {
        this.receiver = receiver;
        return this;
    }

    public MethodBuilder params(JCVariableDecl... parameters) {
        this.parameters = List.from(parameters);
        return this;
    }

    public MethodBuilder exceptions(JCExpression... exceptions) {
        this.exceptions = List.from(exceptions);
        return this;
    }

    public MethodBuilder body(Object body) {
        this.body = (body instanceof BlockBuilder)
                ? ((BlockBuilder) body).build()
                : (JCBlock) body;
        return this;
    }

    public MethodBuilder value(JCExpression value) {
        this.value = value;
        return this;
    }

    @Override
    public JCMethodDecl build() {
        return factory.maker().MethodDef(
                mods, factory.names().fromString(name), type,
                typeParameters, receiver, parameters,
                exceptions, body, value);
    }
}
