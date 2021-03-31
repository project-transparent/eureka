package org.transparent.eureka.impl.builder;

import com.sun.tools.javac.tree.JCTree.*;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Names;
import org.transparent.eureka.EurekaFactory;
import org.transparent.eureka.api.builder.TreeBuilder;
import org.transparent.eureka.api.factory.MemberFactory;

public class BlockBuilder extends TreeBuilder<JCBlock> {
    private long flags = 0L;
    private List<JCStatement> statements = List.nil();

    public BlockBuilder(Names names, EurekaFactory factory) {
        super(names, factory);
    }

    public BlockBuilder flags(long flags) {
        this.flags = flags;
        return this;
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
