package org.transparent.eureka.impl.builder;

import com.sun.tools.javac.tree.JCTree.*;
import com.sun.tools.javac.util.List;
import org.transparent.eureka.EurekaFactory;
import org.transparent.eureka.api.builder.TreeBuilder;
import org.transparent.eureka.impl.builder.statement.ArrayBuilder;
import org.transparent.eureka.impl.builder.statement.ForBuilder;
import org.transparent.eureka.impl.builder.statement.SwitchBuilder;

public class BlockBuilder extends TreeBuilder<JCBlock> {
    private long flags = 0L;
    private List<JCStatement> statements = List.nil();

    public BlockBuilder(EurekaFactory factory) {
        super(factory);
    }

    public BlockBuilder flags(long flags) {
        this.flags = flags;
        return this;
    }

    public BlockBuilder array(ArrayBuilder builder) {
        return add(builder.variable());
    }

    public BlockBuilder returnStat(Object value) {
        return add(factory.returnStat(value));
    }

    public BlockBuilder assertion(JCExpression condition, Object detail) {
        return add(factory.assertion(condition, detail));
    }

    public BlockBuilder whileLoop(JCExpression condition, Object block) {
        return add(factory.whileLoop(condition, block));
    }

    public BlockBuilder doLoop(JCExpression condition, Object block) {
        return add(factory.doLoop(condition, block));
    }

    public BlockBuilder forLoop(ForBuilder builder) {
        return add(builder.build());
    }

    public BlockBuilder forEach(JCVariableDecl variable, JCExpression condition,
                                Object block) {
        return add(factory.forEach(variable, condition, block));
    }

    public BlockBuilder label(String label, JCStatement statement) {
        return add(factory.label(label, statement));
    }

    public BlockBuilder ifStat(JCExpression condition, Object then, Object or) {
        return add(factory.ifStat(condition, then, or));
    }

    public BlockBuilder ifStat(JCExpression condition, Object then) {
        return add(factory.ifStat(condition, then));
    }

    public BlockBuilder switchStat(SwitchBuilder builder) {
        return add(builder.build());
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

    public BlockBuilder call(String name, Object... args) {
        return add(factory.call(name, args));
    }

    private BlockBuilder add(JCStatement stat) {
        statements = statements.append(stat);
        return this;
    }

    @Override
    public JCBlock build() {
        return factory.maker().Block(flags, statements);
    }
}
