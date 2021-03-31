package org.transparent.eureka;

import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.tree.JCTree.*;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;
import org.transparent.eureka.api.factory.StatementFactory;
import org.transparent.eureka.api.factory.MemberFactory;
import org.transparent.eureka.impl.builder.statement.ArrayBuilder;
import org.transparent.eureka.impl.builder.BlockBuilder;
import org.transparent.eureka.impl.builder.FieldBuilder;
import org.transparent.eureka.impl.builder.MethodBuilder;
import org.transparent.eureka.impl.builder.statement.ForBuilder;
import org.transparent.eureka.impl.builder.statement.SwitchBuilder;

public class EurekaFactory implements MemberFactory, StatementFactory {
    private final Names names;
    private final TreeMaker factory;

    public EurekaFactory(Names names, TreeMaker factory) {
        this.names = names;
        this.factory = factory;
    }

    public FieldBuilder field() {
        return new FieldBuilder(this);
    }

    public MethodBuilder method() {
        return new MethodBuilder(this);
    }

    @Override
    public Name name(String name) {
        return names.fromString(name);
    }

    @Override
    public JCModifiers mods(long flags) {
        return factory.Modifiers(flags);
    }

    @Override
    public JCModifiers mods(long flags, JCAnnotation... annotations) {
        return factory.Modifiers(flags, List.from(annotations));
    }

    @Override
    public JCModifiers annotations(JCAnnotation... annotations) {
        return factory.Modifiers(0L, List.from(annotations));
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
            if (expr == null) expr = factory.Ident(name(id));
            else expr = factory.Select(expr, name(id));
        }
        return expr;
    }

    @Override
    public List<JCExpression> ids(JCVariableDecl... parameters) {
        return factory.Idents(List.from(parameters));
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
    public ArrayBuilder array() {
        return new ArrayBuilder(this);
    }

    @Override
    public BlockBuilder block() {
        return new BlockBuilder(this);
    }

    @Override
    public JCReturn returnStat(Object value) {
        return factory.Return(expr(value));
    }

    @Override
    public JCAssert assertion(JCExpression condition, Object detail) {
        return factory.Assert(condition, expr(detail));
    }

    @Override
    public JCWhileLoop whileLoop(JCExpression condition, Object block) {
        return factory.WhileLoop(condition, stat(block));
    }

    @Override
    public JCDoWhileLoop doLoop(JCExpression condition, Object block) {
        return factory.DoLoop(stat(block), condition);
    }

    @Override
    public ForBuilder forLoop() {
        return new ForBuilder(this);
    }

    @Override
    public JCEnhancedForLoop forEach(JCVariableDecl variable, JCExpression condition, Object block) {
        return factory.ForeachLoop(variable, condition, stat(block));
    }

    @Override
    public JCLabeledStatement label(String label, JCStatement statement) {
        return factory.Labelled(name(label), statement);
    }

    @Override
    public JCIf ifStat(JCExpression condition, Object then, Object or) {
        return factory.If(condition, stat(then), stat(or));
    }

    @Override
    public JCIf ifStat(JCExpression condition, Object then) {
        return factory.If(condition, stat(then), null);
    }

    @Override
    public SwitchBuilder switchStat(JCExpression selector) {
        return new SwitchBuilder(this, selector);
    }

    @Override
    public JCContinue continueStat(String label) {
        return factory.Continue(name(label));
    }

    @Override
    public JCBreak breakStat(String label) {
        return factory.Break(name(label));
    }

    @Override
    public JCExpressionStatement exprStat(JCExpression expr) {
        return factory.Exec(expr);
    }

    @Override
    public JCBinary bin(Tag operator, Object lhs, Object rhs) {
        return factory.Binary(operator, expr(lhs), expr(rhs));
    }

    @Override
    public JCAssignOp assign(Tag operator, Object lhs, Object rhs) {
        return factory.Assignop(operator, expr(lhs), expr(rhs));
    }

    @Override
    public JCAssign assign(Object lhs, Object rhs) {
        return factory.Assign(expr(lhs), expr(rhs));
    }

    @Override
    public JCExpressionStatement call(String name, Object... args) {
        JCExpression expr = id(name);
        if (args.length > 0) {
            List<JCExpression> exprs = List.nil();
            for (Object arg : args)
                exprs = exprs.append(literal(arg));
            expr = factory.Apply(List.nil(), expr, exprs);
        }
        return factory.Exec(expr);
    }

    public JCExpression expr(Object value) {
        return (value instanceof JCExpression)
                ? (JCExpression) value
                : factory.Literal(value);
    }

    public JCStatement stat(Object value) {
        return (value instanceof BlockBuilder)
                ? ((BlockBuilder) value).build()
                : (JCStatement) value;
    }

    public Names names() {
        return names;
    }

    public TreeMaker maker() {
        return factory;
    }
}