package org.transparent.eureka.impl.builder;

import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.tree.JCTree.*;
import com.sun.tools.javac.util.List;
import org.transparent.eureka.EurekaFactory;
import org.transparent.eureka.api.builder.TreeBuilder;
import org.transparent.eureka.util.Modifiers;
import org.transparent.eureka.util.Types;

public class MethodBuilder extends TreeBuilder<JCMethodDecl> {
    private long mods;
    private String name;
    private JCExpression type;
    private List<JCTypeParameter> typeParameters = List.nil();
    private JCVariableDecl receiver;
    private List<JCVariableDecl> parameters = List.nil();
    private List<JCExpression> exceptions = List.nil();
    private JCStatement body;
    private JCExpression value;

    public MethodBuilder(EurekaFactory factory) {
        super(factory);
        mods = 0L;
        type = factory.utils.id(TypeTag.VOID);
        body = factory.utils.block();
    }

    public MethodBuilder mods(Modifiers mods) {
        this.mods = mods.getFlags();
        return this;
    }

    public MethodBuilder mods(long flags) {
        this.mods = flags;
        return this;
    }

    public MethodBuilder name(String name) {
        this.name = name;
        return this;
    }

    public MethodBuilder type(String type) {
        this.type = factory.utils.id(type);
        return this;
    }

    public MethodBuilder type(JCExpression type) {
        this.type = type;
        return this;
    }

    public MethodBuilder type(TypeTag type) {
        this.type = factory.utils.id(type);
        return this;
    }

    public MethodBuilder type(Types type) {
        this.type = factory.utils.id(type.getTag());
        return this;
    }

    public MethodBuilder type(Class<?> clazz) {
        this.type = factory.utils.id(clazz.getCanonicalName());
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

    public MethodBuilder body(JCStatement body) {
        this.body = body;
        return this;
    }

    public MethodBuilder value(JCExpression value) {
        this.value = value;
        return this;
    }

    @Override
    public JCMethodDecl build() {
        return factory.maker.MethodDef(
                factory.utils.mods(mods),
                factory.utils.name(name),
                type, typeParameters, receiver,
                parameters, exceptions,
                (body instanceof JCBlock)
                        ? (JCBlock) body
                        : factory.maker.Block(0L, List.of(body)),
                value);
    }
}
