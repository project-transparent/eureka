package org.transparent.eureka.tree.builder;

import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.tree.JCTree.*;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Names;

public class MethodBuilder extends EurekaBuilder<JCMethodDecl> {
    private JCModifiers mods;
    private String name;
    private JCExpression returnType;
    private List<JCTypeParameter> typeParameters = List.nil();
    private JCVariableDecl receiver;
    private List<JCVariableDecl> parameters = List.nil();
    private List<JCExpression> exceptions = List.nil();
    private JCBlock body;
    private JCExpression defaultValue;

    public MethodBuilder(Names names, TreeMaker factory) {
        super(names, factory);
        mods = factory.Modifiers(0L);
        returnType = factory.Literal(TypeTag.VOID, null);
        body = factory.Block(0L, List.nil());
    }

    public MethodBuilder mods(JCModifiers mods) {
        this.mods = mods;
        return this;
    }

    public MethodBuilder mods(long flags) {
        this.mods = factory.Modifiers(flags);
        return this;
    }

    public MethodBuilder name(String name) {
        this.name = name;
        return this;
    }

    public MethodBuilder returns(JCExpression returnType) {
        this.returnType = returnType;
        return this;
    }

    public MethodBuilder returns(String type) {
        this.returnType = factory.Ident(
                toName(type));
        return this;
    }

    public MethodBuilder returns(TypeTag tag) {
        this.returnType = factory.TypeIdent(tag);
        return this;
    }

    public MethodBuilder generics(JCTypeParameter... typeParameters) {
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

    public MethodBuilder thrown(JCExpression... exceptions) {
        this.exceptions = List.from(exceptions);
        return this;
    }

    public MethodBuilder body(JCBlock body) {
        this.body = body;
        return this;
    }

    public MethodBuilder body(BlockBuilder body) {
        this.body = body.build();
        return this;
    }

    public MethodBuilder value(JCExpression defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    @Override
    public JCMethodDecl build() {
        return factory.MethodDef(
                mods, toName(name), returnType,
                typeParameters, receiver, parameters,
                exceptions, body, defaultValue);
    }
}
