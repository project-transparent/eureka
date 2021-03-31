package org.transparent.eureka.impl.builder.statement;

import com.sun.tools.javac.tree.JCTree.*;
import com.sun.tools.javac.util.List;
import org.transparent.eureka.EurekaFactory;
import org.transparent.eureka.api.builder.TreeBuilder;

public class SwitchBuilder extends TreeBuilder<JCSwitch> {
    private final JCExpression selector;
    private List<JCCase> cases;

    public SwitchBuilder(EurekaFactory factory, JCExpression selector) {
        super(factory);
        this.selector = selector;
    }

    public SwitchBuilder caseStat(JCExpression pattern, JCStatement... statements) {
        cases = cases.append(factory.maker()
                .Case(pattern, List.from(statements)));
        return this;
    }

    public SwitchBuilder defaultStat(JCStatement... statements) {
        cases = cases.append(factory.maker()
                .Case(factory.id("default"), List.from(statements)));
        return this;
    }

    @Override
    public JCSwitch build() {
        return factory.maker().Switch(selector, cases);
    }
}
