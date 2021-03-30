package org.transparent.eureka.tree.builder;

import com.sun.tools.javac.tree.JCTree.*;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Names;
import org.transparent.eureka.api.factory.TreeFactory;

public class BlockBuilder extends EurekaBuilder<JCBlock> {
    private final TreeMaker maker;
    private final TreeFactory factory;

    private long flags = 0L;
    private List<JCStatement> statements = List.nil();

    public BlockBuilder(Names names, TreeMaker maker, TreeFactory tree) {
        super(names, maker);
        this.maker = maker;
        this.factory = tree;
    }

    public BlockBuilder returnStat(JCExpression value) {
        return add(factory.returnStat(value));
    }

    public BlockBuilder returnStat(Object value) {
        return add(factory.returnStat(value));
    }

    public BlockBuilder assertStat(JCExpression condition, JCExpression detail) {
        return add(factory.assertStat(condition, detail));
    }

    public BlockBuilder assertStat(JCExpression condition, Object detail) {
        return add(factory.assertStat(condition, detail));
    }

    public BlockBuilder whileStat(JCExpression condition, BlockBuilder block) {
        return add(factory.whileStat(condition, block));
    }

    public BlockBuilder whileStat(JCExpression condition, JCStatement statement) {
        return add(factory.whileStat(condition, statement));
    }

    public BlockBuilder doStat(JCExpression condition, BlockBuilder block) {
        return add(factory.doStat(condition, block));
    }

    public BlockBuilder doStat(JCExpression condition, JCStatement statement) {
        return add(factory.doStat(condition, statement));
    }

    public BlockBuilder forStat(List<JCStatement> initializers, JCExpression condition,
                                List<JCExpressionStatement> steps, BlockBuilder block) {
        return add(factory.forStat(initializers, condition, steps, block));
    }

    public BlockBuilder forStat(List<JCStatement> initializers, JCExpression condition,
                                List<JCExpressionStatement> steps, JCStatement statement) {
        return add(factory.forStat(initializers, condition, steps, statement));
    }

    public BlockBuilder forEachStat(JCVariableDecl variable, JCExpression condition,
                                    BlockBuilder block) {
        return add(factory.forEachStat(variable, condition, block));
    }

    public BlockBuilder forEachStat(JCVariableDecl variable, JCExpression condition,
                                    JCStatement statement) {
        return add(factory.forEachStat(variable, condition, statement));
    }

    public BlockBuilder labelledStat(String label, JCStatement statement) {
        return add(factory.labelledStat(label, statement));
    }

    public BlockBuilder ifStat(JCExpression condition, BlockBuilder then, BlockBuilder or) {
        return add(factory.ifStat(condition, then, or));
    }

    public BlockBuilder ifStat(JCExpression condition, BlockBuilder then, JCStatement or) {
        return add(factory.ifStat(condition, then, or));
    }

    public BlockBuilder ifStat(JCExpression condition, JCStatement then, BlockBuilder or) {
        return add(factory.ifStat(condition, then, or));
    }

    public BlockBuilder ifStat(JCExpression condition, JCStatement then, JCStatement or) {
        return add(factory.ifStat(condition, then, or));
    }

    public BlockBuilder ifStat(JCExpression condition, BlockBuilder then) {
        return add(factory.ifStat(condition, then));
    }

    public BlockBuilder ifStat(JCExpression condition, JCStatement then) {
        return add(factory.ifStat(condition, then));
    }

    public BlockBuilder switchStat(JCExpression selector, List<JCCase> cases) {
        return add(factory.switchStat(selector, cases));
    }

    public BlockBuilder continueStat(String label) {
        return add(factory.continueStat(label));
    }

    public BlockBuilder breakStat(String label) {
        return add(factory.breakStat(label));
    }

    public BlockBuilder exprStat(JCExpression expr) {
        return add(factory.exprStat(expr));
    }

    private BlockBuilder add(JCStatement stat) {
        statements = statements.append(stat);
        return this;
    }

    @Override
    public JCBlock build() {
        return maker.Block(flags, statements);
    }
}
