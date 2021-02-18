package org.transparent.eureka.builder;

import com.sun.tools.javac.tree.JCTree.*;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.List;

// TODO
public final class BlockBuilder {
    private long flags = 0L;
    private List<JCStatement> statements = List.nil();

    public BlockBuilder setFlags(long flags) {
        this.flags = flags;
        return this;
    }

    public JCBlock build(TreeMaker maker) {
        return maker.Block(flags, statements);
    }
}
