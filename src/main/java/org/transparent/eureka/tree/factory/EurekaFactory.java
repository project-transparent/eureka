package org.transparent.eureka.tree.factory;

import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.tree.JCTree.*;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Names;
import org.transparent.eureka.tree.builder.BlockBuilder;
import org.transparent.eureka.tree.builder.FieldBuilder;
import org.transparent.eureka.tree.builder.MethodBuilder;
import org.transparent.eureka.util.Injector;

public class EurekaFactory extends AbstractTreeFactory {
    public EurekaFactory(Names names, TreeMaker factory) {
        super(names, factory);
    }

    @Override
    public JCModifiers mods() {
        return factory.Modifiers(0L);
    }

    @Override
    public JCModifiers mods(long flags) {
        return factory.Modifiers(flags);
    }

    @Override
    public JCModifiers mods(long flags, List<JCAnnotation> annotations) {
        return factory.Modifiers(flags, annotations);
    }

    @Override
    public JCModifiers mods(List<JCAnnotation> annotations) {
        return factory.Modifiers(0L, annotations);
    }

    @Override
    public JCExpression id(JCVariableDecl parameter) {
        return factory.Ident(parameter);
    }

    @Override
    public JCExpression id(String id) {
        final String[] ids = id.split("\\.");
        return (ids.length == 0)
                ? factory.Ident(name(id))
                : id(ids);
    }

    private JCExpression id(String[] ids) {
        JCExpression expr = null;
        for (String id : ids) {
            if (id == null) factory.Ident(name(id));
            else expr = factory.Select(expr, name(id));
        }
        return expr;
    }

    @Override
    public JCIdent id(Symbol symbol) {
        return factory.Ident(symbol);
    }

    @Override
    public List<JCExpression> ids(List<JCVariableDecl> parameters) {
        return factory.Idents(parameters);
    }

    @Override
    public JCPrimitiveTypeTree id(TypeTag tag) {
        return factory.TypeIdent(tag);
    }

    @Override
    public JCLiteral literal(TypeTag tag, Object value) {
        return factory.Literal(tag, value);
    }

    @Override
    public JCLiteral literal(Object value) {
        return factory.Literal(value);
    }

    @Override
    public JCLiteral literal() {
        return factory.Literal(TypeTag.BOT, null);
    }

    @Override
    public JCVariableDecl field(JCModifiers mods, String name,
                                 JCExpression type, JCExpression init) {
        return factory.VarDef(mods, name(name), type, init);
    }

    @Override
    public JCVariableDecl field(String name, JCExpression type, JCExpression init) {
        return factory.VarDef(mods(), name(name), type, init);
    }

    @Override
    public JCVariableDecl field(JCModifiers mods, String name, JCExpression type) {
        return factory.VarDef(mods, name(name), type, null);
    }

    @Override
    public JCVariableDecl field(String name, JCExpression type) {
        return factory.VarDef(mods(), name(name), type, null);
    }

    public FieldBuilder field() {
        return new FieldBuilder(names, factory);
    }

    @Override
    public JCMethodDecl method(JCModifiers mods, String name, JCExpression returnType, JCBlock body) {
        return factory.MethodDef(
                mods, name(name),
                returnType, List.nil(),
                List.nil(), List.nil(),
                body, null);
    }

    @Override
    public JCMethodDecl method(String name, JCExpression returnType, JCBlock body) {
        return factory.MethodDef(
                mods(), name(name),
                returnType, List.nil(),
                List.nil(), List.nil(),
                body, null);
    }

    @Override
    public JCMethodDecl method(String name, JCBlock body) {
        return factory.MethodDef(
                mods(), name(name),
                literal(), List.nil(),
                List.nil(), List.nil(),
                body, null);
    }

    @Override
    public JCBlock block(long flags) {
        return factory.Block(flags, List.nil());
    }

    @Override
    public BlockBuilder block() {
        return new BlockBuilder(names, factory);
    }

    public MethodBuilder method() {
        return new MethodBuilder(names, factory);
    }

    public Injector inject(JCClassDecl tree) {
        return new Injector(tree);
    }
}