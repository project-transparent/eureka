package org.transparent.eureka.impl.builder.statement;

import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.JCTree.*;
import com.sun.tools.javac.util.List;
import org.transparent.eureka.EurekaFactory;
import org.transparent.eureka.api.builder.TreeBuilder;

public class ForBuilder extends TreeBuilder<JCForLoop> {
    private List<JCStatement> init = List.nil();
    private JCExpression condition;
    private List<JCExpressionStatement> steps = List.nil();
    private JCStatement body;

    public ForBuilder(EurekaFactory factory) {
        super(factory);
        this.body = factory.maker().Block(0L, List.nil());
    }

    public ForBuilder init(JCStatement... init) {
        this.init = List.from(init);
        return this;
    }

    public ForBuilder condition(Object condition) {
        this.condition = factory.expr(condition);
        return this;
    }

    public ForBuilder steps(JCExpressionStatement... steps) {
        this.steps = List.from(steps);
        return this;
    }

    public ForBuilder body(Object body) {
        this.body = factory.stat(body);
        return this;
    }

    @Override
    public JCForLoop build() {
        return factory.maker().ForLoop(
                init, condition,
                steps, body);
    }
}