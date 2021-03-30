package org.transparent.eureka.tree.builder;

import com.sun.tools.javac.tree.JCTree.*;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Names;

public class BlockBuilder extends EurekaBuilder<JCBlock> {
    private long flags = 0L;
    private List<JCStatement> statements = List.nil();

    public BlockBuilder(Names names, TreeMaker factory) {
        super(names, factory);
    }

    public BlockBuilder returnStat(JCExpression value) {
        return add(factory.Return(value));
    }

    public BlockBuilder returnStat(Object value) {
        return add(factory.Return(factory.Literal(value)));
    }

    public BlockBuilder assertStat(JCExpression condition, JCExpression detail) {
        return add(factory.Assert(condition, detail));
    }

    public BlockBuilder assertStat(JCExpression condition, Object detail) {
        return add(factory.Assert(condition, factory.Literal(detail)));
    }

    public BlockBuilder whileStat(JCExpression condition, BlockBuilder block) {
        return whileStat(condition, block.build());
    }

    public BlockBuilder whileStat(JCExpression condition, JCStatement statement) {
        return add(factory.WhileLoop(condition, statement));
    }

    public BlockBuilder doStat(JCExpression condition, BlockBuilder block) {
        return doStat(condition, block.build());
    }

    public BlockBuilder doStat(JCExpression condition, JCStatement statement) {
        return add(factory.DoLoop(statement, condition));
    }

    public BlockBuilder forStat(List<JCStatement> initializers, JCExpression condition,
                                List<JCExpressionStatement> steps, BlockBuilder block) {
        return forStat(initializers, condition, steps, block.build());
    }

    public BlockBuilder forStat(List<JCStatement> initializers, JCExpression condition,
                                List<JCExpressionStatement> steps, JCStatement statement) {
        return add(factory.ForLoop(initializers, condition, steps, statement));
    }

    public BlockBuilder forEachStat(JCVariableDecl variable, JCExpression condition,
                                    BlockBuilder block) {
        return forEachStat(variable, condition, block.build());
    }

    public BlockBuilder forEachStat(JCVariableDecl variable, JCExpression condition,
                                    JCStatement statement) {
        return add(factory.ForeachLoop(variable, condition, statement));
    }

    public BlockBuilder labelledStat(String label, JCStatement statement) {
        return add(factory.Labelled(toName(label), statement));
    }

    public BlockBuilder ifStat(JCExpression condition, BlockBuilder then, BlockBuilder or) {
        return ifStat(condition, then.build(), or.build());
    }

    public BlockBuilder ifStat(JCExpression condition, BlockBuilder then, JCStatement or) {
        return ifStat(condition, then.build(), or);
    }

    public BlockBuilder ifStat(JCExpression condition, JCStatement then, BlockBuilder or) {
        return ifStat(condition, then, or.build());
    }

    public BlockBuilder ifStat(JCExpression condition, JCStatement then, JCStatement or) {
        return add(factory.If(condition, then, or));
    }

    public BlockBuilder ifStat(JCExpression condition, BlockBuilder then) {
        return add(factory.If(condition, then.build(), null));
    }

    public BlockBuilder ifStat(JCExpression condition, JCStatement then) {
        return add(factory.If(condition, then, null));
    }

    public BlockBuilder switchStat(JCExpression selector, List<JCCase> cases) {
        return add(factory.Switch(selector, cases));
    }

    public BlockBuilder continueStat(String label) {
        return add(factory.Continue(toName(label)));
    }

    public BlockBuilder breakStat(String label) {
        return add(factory.Break(toName(label)));
    }

    public BlockBuilder exprStat(JCExpression expr) {
        return add(factory.Exec(expr));
    }

    private BlockBuilder add(JCStatement stat) {
        statements = statements.append(stat);
        return this;
    }

    @Override
    public JCBlock build() {
        return factory.Block(flags, statements);
    }
}
