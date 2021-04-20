package org.transparent.eureka.impl.visitor;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.stmt.*;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.GenericVisitorAdapter;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.List;
import org.transparent.eureka.util.Utils;

import java.util.Optional;

import static com.sun.tools.javac.tree.JCTree.*;

public class StatementVisitor extends GenericVisitorAdapter<JCStatement, Void> {
    private final TreeMaker maker;
    private final ExpressionVisitor ev;

    public StatementVisitor(TreeMaker maker, Utils utils, JCClassDecl target) {
        this.maker = maker;
        this.ev = new ExpressionVisitor(maker, utils, target);
    }

    private JCStatement accept(Statement n) {
        return n.accept(this, null);
    }

    private <N extends Node, T extends JCTree> T accept(
            N n, GenericVisitor<T, Void> visitor) {
        return n.accept(visitor, null);
    }

    @Override
    public JCStatement visit(DoStmt n, Void arg) {
        return maker.DoLoop(
                accept(n.getBody()),
                accept(n.getCondition(), ev));
    }

    @Override
    public JCStatement visit(IfStmt n, Void arg) {
        final Optional<Statement> elseStmt =
                n.getElseStmt();
        return maker.If(
                accept(n.getCondition(), ev),
                accept(n.getThenStmt()),
                elseStmt.map(this::accept)
                        .orElse(null));
    }

    @Override
    public JCStatement visit(ForStmt n, Void arg) {
        List<JCStatement> init = List.nil();
        for (Expression expr : n.getInitialization())
            init = init.append(maker.Exec(accept(expr, ev)));
        List<JCExpressionStatement> steps = List.nil();
        for (Expression step : n.getUpdate())
            steps = steps.append(maker.Exec(accept(step, ev)));
        return maker.ForLoop(
                init,
                n.getCompare()
                        .map(expr -> accept(expr, ev))
                        .orElse(null),
                steps,
                accept(n.getBody()));
    }

    @Override
    public JCStatement visit(TryStmt n, Void arg) {
        return super.visit(n, arg);
    }

    @Override
    public JCStatement visit(BlockStmt n, Void arg) {
        List<JCStatement> stats = List.nil();
        for (Statement stat : n.getStatements())
            stats = stats.append(accept(stat));
        return maker.Block(0L, stats);
    }

    @Override
    public JCStatement visit(BreakStmt n, Void arg) {
        return super.visit(n, arg);
    }

    @Override
    public JCStatement visit(ThrowStmt n, Void arg) {
        return super.visit(n, arg);
    }

    @Override
    public JCStatement visit(WhileStmt n, Void arg) {
        return super.visit(n, arg);
    }

    @Override
    public JCStatement visit(AssertStmt n, Void arg) {
        return super.visit(n, arg);
    }

    @Override
    public JCStatement visit(ReturnStmt n, Void arg) {
        return super.visit(n, arg);
    }

    @Override
    public JCStatement visit(SwitchStmt n, Void arg) {
        return super.visit(n, arg);
    }

    @Override
    public JCStatement visit(ForEachStmt n, Void arg) {
        return super.visit(n, arg);
    }

    @Override
    public JCStatement visit(ContinueStmt n, Void arg) {
        return super.visit(n, arg);
    }

    @Override
    public JCStatement visit(ExpressionStmt n, Void arg) {
        return maker.Exec(accept(n.getExpression(), ev));
    }

    @Override
    public JCStatement visit(LabeledStmt n, Void arg) {
        return super.visit(n, arg);
    }

    @Override
    public JCStatement visit(SynchronizedStmt n, Void arg) {
        return super.visit(n, arg);
    }

    @Override
    public JCStatement visit(EmptyStmt n, Void arg) {
        return super.visit(n, arg);
    }
}