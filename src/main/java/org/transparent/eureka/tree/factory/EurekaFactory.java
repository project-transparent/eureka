package org.transparent.eureka.tree.factory;

import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.tree.JCTree.*;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;
import org.transparent.eureka.tree.builder.BlockBuilder;
import org.transparent.eureka.tree.builder.FieldBuilder;
import org.transparent.eureka.tree.builder.MethodBuilder;
import org.transparent.eureka.util.Injector.*;

public class EurekaFactory extends AbstractTreeFactory {
    public EurekaFactory(Names names, TreeMaker factory) {
        super(names, factory);
    }

    @Override
    public Name name(String name) {
        return names.fromString(name);
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
            if (expr == null) expr = factory.Ident(name(id));
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
    public JCNewArray array(JCExpression type, List<JCExpression> dimensions, List<JCExpression> elements) {
        return factory.NewArray(type, dimensions, elements);
    }

    @Override
    public JCNewArray array(JCExpression type, List<JCExpression> elements) {
        return factory.NewArray(type, List.nil(), elements);
    }

    @Override
    public JCArrayTypeTree array(JCExpression type) {
        return factory.TypeArray(type);
    }

    @Override
    public JCNewArray array(List<JCExpression> elements) {
        return factory.NewArray(null, List.nil(), elements);
    }

    @Override
    public JCNewArray array() {
        return factory.NewArray(null, List.nil(), List.nil());
    }

    @Override
    public JCBlock block(long flags) {
        return factory.Block(flags, List.nil());
    }

    @Override
    public BlockBuilder block() {
        return new BlockBuilder(names, factory, this);
    }

    public JCReturn returnStat(JCExpression value) {
        return factory.Return(value);
    }

    public JCReturn returnStat(Object value) {
        return factory.Return(literal(value));
    }

    public JCAssert assertStat(JCExpression condition, JCExpression detail) {
        return factory.Assert(condition, detail);
    }

    public JCAssert assertStat(JCExpression condition, Object detail) {
        return factory.Assert(condition, literal(detail));
    }

    public JCWhileLoop whileStat(JCExpression condition, BlockBuilder block) {
        return whileStat(condition, block.build());
    }

    public JCWhileLoop whileStat(JCExpression condition, JCStatement statement) {
        return factory.WhileLoop(condition, statement);
    }

    public JCDoWhileLoop doStat(JCExpression condition, BlockBuilder block) {
        return doStat(condition, block.build());
    }

    public JCDoWhileLoop doStat(JCExpression condition, JCStatement statement) {
        return factory.DoLoop(statement, condition);
    }

    public JCForLoop forStat(List<JCStatement> initializers, JCExpression condition,
                             List<JCExpressionStatement> steps, BlockBuilder block) {
        return forStat(initializers, condition, steps, block.build());
    }

    public JCForLoop forStat(List<JCStatement> initializers, JCExpression condition,
                             List<JCExpressionStatement> steps, JCStatement statement) {
        return factory.ForLoop(initializers, condition, steps, statement);
    }

    public JCEnhancedForLoop forEachStat(JCVariableDecl variable, JCExpression condition,
                                         BlockBuilder block) {
        return forEachStat(variable, condition, block.build());
    }

    public JCEnhancedForLoop forEachStat(JCVariableDecl variable, JCExpression condition,
                                         JCStatement statement) {
        return factory.ForeachLoop(variable, condition, statement);
    }

    public JCLabeledStatement labelledStat(String label, JCStatement statement) {
        return factory.Labelled(name(label), statement);
    }

    public JCIf ifStat(JCExpression condition, BlockBuilder then, BlockBuilder or) {
        return ifStat(condition, then.build(), or.build());
    }

    public JCIf ifStat(JCExpression condition, BlockBuilder then, JCStatement or) {
        return ifStat(condition, then.build(), or);
    }

    public JCIf ifStat(JCExpression condition, JCStatement then, BlockBuilder or) {
        return ifStat(condition, then, or.build());
    }

    public JCIf ifStat(JCExpression condition, JCStatement then, JCStatement or) {
        return factory.If(condition, then, or);
    }

    public JCIf ifStat(JCExpression condition, BlockBuilder then) {
        return factory.If(condition, then.build(), null);
    }

    public JCIf ifStat(JCExpression condition, JCStatement then) {
        return factory.If(condition, then, null);
    }

    public JCSwitch switchStat(JCExpression selector, List<JCCase> cases) {
        return factory.Switch(selector, cases);
    }

    public JCContinue continueStat(String label) {
        return factory.Continue(name(label));
    }

    public JCBreak breakStat(String label) {
        return factory.Break(name(label));
    }

    public JCExpressionStatement exprStat(JCExpression expr) {
        return factory.Exec(expr);
    }

    @Override
    public JCBinary binary(Tag operator, JCExpression lhs, JCExpression rhs) {
        return factory.Binary(operator, lhs, rhs);
    }

    @Override
    public JCBinary binary(Tag operator, Object lhs, Object rhs) {
        return factory.Binary(operator, literal(lhs), literal(rhs));
    }

    @Override
    public JCBinary binary(Tag operator, Object lhs, JCExpression rhs) {
        return factory.Binary(operator, literal(lhs), rhs);
    }

    @Override
    public JCBinary binary(Tag operator, JCExpression lhs, Object rhs) {
        return factory.Binary(operator, lhs, literal(rhs));
    }

    @Override
    public JCAssignOp assign(Tag operator, JCExpression lhs, JCExpression rhs) {
        return factory.Assignop(operator, lhs, rhs);
    }

    @Override
    public JCAssignOp assign(Tag operator, Object lhs, Object rhs) {
        return factory.Assignop(operator, literal(lhs), literal(rhs));
    }

    @Override
    public JCAssignOp assign(Tag operator, Object lhs, JCExpression rhs) {
        return factory.Assignop(operator, literal(lhs), rhs);
    }

    @Override
    public JCAssignOp assign(Tag operator, JCExpression lhs, Object rhs) {
        return factory.Assignop(operator, lhs, literal(rhs));
    }

    @Override
    public JCAssign assign(JCExpression lhs, JCExpression rhs) {
        return factory.Assign(lhs, rhs);
    }

    @Override
    public JCAssign assign(Object lhs, Object rhs) {
        return factory.Assign(literal(lhs), literal(rhs));
    }

    @Override
    public JCAssign assign(Object lhs, JCExpression rhs) {
        return factory.Assign(literal(lhs), rhs);
    }

    @Override
    public JCAssign assign(JCExpression lhs, Object rhs) {
        return factory.Assign(lhs, literal(rhs));
    }

    @Override
    public JCExpressionStatement call(String name, JCExpression... args) {
        JCExpression expr = id(name);
        if (args.length > 0)
            expr = factory.Apply(
                List.nil(), expr, List.from(args)
            );
        return factory.Exec(expr);
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

    public FieldBuilder field() {
        return new FieldBuilder(names, factory);
    }

    public MethodBuilder method() {
        return new MethodBuilder(names, factory);
    }

    public ClassInjector inject(JCClassDecl tree) {
        return new ClassInjector(tree);
    }

    public MethodInjector inject(JCMethodDecl tree) {
        return new MethodInjector(tree, factory);
    }
}