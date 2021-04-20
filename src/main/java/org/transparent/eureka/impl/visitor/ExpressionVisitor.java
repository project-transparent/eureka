package org.transparent.eureka.impl.visitor;

import com.github.javaparser.ast.ArrayCreationLevel;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.GenericVisitorAdapter;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.List;
import org.transparent.eureka.util.Operators;
import org.transparent.eureka.util.Utils;

import java.util.Objects;

import static com.github.javaparser.ast.expr.AssignExpr.Operator.ASSIGN;
import static com.sun.tools.javac.tree.JCTree.*;

public class ExpressionVisitor extends GenericVisitorAdapter<JCExpression, Void> {
    private final TreeMaker maker;
    private final Utils utils;
    private final JCClassDecl target;
    private final TypeVisitor tv;

    public ExpressionVisitor(TreeMaker maker, Utils utils, JCClassDecl target) {
        this.maker = maker;
        this.utils = utils;
        this.target = target;
        this.tv = new TypeVisitor(maker, utils);
    }

    private JCExpression accept(Expression n) {
        return n.accept(this, null);
    }

    private <N extends Node, T extends JCTree> T accept(
            N n, GenericVisitor<T, Void> visitor) {
        return n.accept(visitor, null);
    }

    @Override
    public JCExpression visit(NameExpr n, Void arg) {
        return utils.id(n.getNameAsString());
    }

    @Override
    public JCExpression visit(CastExpr n, Void arg) {
        final JCExpression type = accept(n.getType(), tv);
        return maker.TypeCast(type, accept(n.getExpression()));
    }

    @Override
    public JCExpression visit(ThisExpr n, Void arg) {
        // MIGHT NOT WORK
        return maker.This(target.type);
    }

    @Override
    public JCExpression visit(ClassExpr n, Void arg) {
        // MIGHT NOT WORK
        return maker.ClassLiteral(target.type);
    }

    @Override
    public JCExpression visit(SuperExpr n, Void arg) {
        // MIGHT NOT WORK
        return maker.Super(target.type, target.sym);
    }

    @Override
    public JCExpression visit(UnaryExpr n, Void arg) {
        return maker.Unary(
                Operators.unOp(n.getOperator()),
                accept(n.getExpression()));
    }

    @Override
    public JCExpression visit(AssignExpr n, Void arg) {
        final JCExpression target = accept(n.getTarget());
        final JCExpression value = accept(n.getValue());
        if (n.getOperator() == ASSIGN)
            return maker.Assign(target, value);
        return maker.Assignop(
                Operators.asOp(n.getOperator()),
                target, value);
    }

    @Override
    public JCExpression visit(BinaryExpr n, Void arg) {
        return maker.Binary(
                Operators.binOp(n.getOperator()),
                accept(n.getLeft()),
                accept(n.getRight()));
    }

    @Override
    public JCExpression visit(LambdaExpr n, Void arg) {
        List<JCVariableDecl> params = List.nil();
        final ParameterVisitor visitor = new ParameterVisitor();
        for (Parameter param : n.getParameters())
            params.add(accept(param, visitor));
        return maker.Lambda(params, accept(n.getBody(),
                new StatementVisitor(maker, utils, target)));
    }

    @Override
    public JCExpression visit(EnclosedExpr n, Void arg) {
        return maker.Parens(n.accept(this, arg));
    }

    @Override
    public JCExpression visit(InstanceOfExpr n, Void arg) {
        return maker.TypeTest(
                accept(n.getExpression()),
                accept(n.getType(), tv));
    }

    @Override
    public JCExpression visit(MethodCallExpr n, Void arg) {
        List<JCExpression> typeArgs = List.nil();
        n.getTypeArguments().ifPresent(list ->
                list.forEach(typeArg -> typeArgs
                        .add(accept(typeArg, tv))));
        JCExpression expr = utils.id(n.getScope()
                .map(Objects::toString)
                .orElse("") + '.' + n.getNameAsString());
        List<JCExpression> args = List.nil();
        for (Expression argument : n.getArguments())
            args = args.append(accept(argument));
        return maker.Apply(typeArgs, expr, args);
    }

    @Override
    public JCExpression visit(ArrayAccessExpr n, Void arg) {
        // maker.Indexed
        return super.visit(n, arg);
    }

    @Override
    public JCExpression visit(CharLiteralExpr n, Void arg) {
        return maker.Literal(TypeTag.CHAR, n.asChar());
    }

    @Override
    public JCExpression visit(ConditionalExpr n, Void arg) {
        return maker.Conditional(
                accept(n.getCondition()),
                accept(n.getThenExpr()),
                accept(n.getElseExpr()));
    }

    @Override
    public JCExpression visit(FieldAccessExpr n, Void arg) {
        return utils.id(n.getScope() + "." + n.getNameAsString());
    }

    @Override
    public JCExpression visit(LongLiteralExpr n, Void arg) {
        return maker.Literal(TypeTag.LONG, n.asNumber());
    }

    @Override
    public JCExpression visit(NullLiteralExpr n, Void arg) {
        return maker.Literal(TypeTag.BOT, null);
    }

    @Override
    public JCExpression visit(ArrayCreationExpr n, Void arg) {
        List<JCExpression> dims = List.nil();
        n.getLevels().forEach(
                level -> level.getDimension().ifPresent(dim ->
                        dims.append(accept(dim))));
        List<JCExpression> elems = List.nil();
        n.getInitializer().ifPresent(init -> init.getValues()
                .forEach(elem -> elems.append(accept(elem))));
        return maker.NewArray(
                accept(n.getElementType(), tv),
                dims, elems);
    }

    @Override
    public JCExpression visit(DoubleLiteralExpr n, Void arg) {
        return maker.Literal(TypeTag.DOUBLE, n.asDouble());
    }

    @Override
    public JCExpression visit(StringLiteralExpr n, Void arg) {
        return maker.Literal(n.asString());
    }

    @Override
    public JCExpression visit(BooleanLiteralExpr n, Void arg) {
        return maker.Literal(TypeTag.BOOLEAN, n.getValue());
    }

    @Override
    public JCExpression visit(IntegerLiteralExpr n, Void arg) {
        return maker.Literal(TypeTag.INT, n.asNumber());
    }

    @Override
    public JCExpression visit(ObjectCreationExpr n, Void arg) {
        // maker.NewClass or maker.TypeApply
        return null;
    }

    @Override
    public JCExpression visit(MethodReferenceExpr n, Void arg) {
        // maker.Reference
        return super.visit(n, arg);
    }

    @Override
    public JCExpression visit(NormalAnnotationExpr n, Void arg) {
        // maker.Annotation
        return super.visit(n, arg);
    }

    @Override
    public JCExpression visit(MarkerAnnotationExpr n, Void arg) {
        // maker.Annotation
        return super.visit(n, arg);
    }

    @Override
    public JCExpression visit(VariableDeclarationExpr n, Void arg) {
        // maker.VarDef
        return super.visit(n, arg);
    }

    @Override
    public JCExpression visit(SingleMemberAnnotationExpr n, Void arg) {
        // maker.Annotation
        return super.visit(n, arg);
    }
}