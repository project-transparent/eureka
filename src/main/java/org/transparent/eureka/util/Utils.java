package org.transparent.eureka.util;

import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;

import static com.sun.tools.javac.tree.JCTree.*;

public final class Utils {
    private final Names names;
    private final TreeMaker maker;

    public Utils(Names names, TreeMaker maker) {
        this.names = names;
        this.maker = maker;
    }

    public JCModifiers mods(long flags) {
        return maker.Modifiers(flags);
    }

    public JCExpression id(String id) {
        final String[] ids = id.split("\\.");
        return (ids.length == 0)
                ? maker.Ident(name(id))
                : id(ids);
    }

    private JCExpression id(String[] ids) {
        JCExpression expr = null;
        for (String id : ids) {
            if (expr == null) expr = maker.Ident(name(id));
            else expr = maker.Select(expr, name(id));
        }
        return expr;
    }

    public JCPrimitiveTypeTree id(TypeTag tag) {
        return maker.TypeIdent(tag);
    }

    public Name name(String name) {
        return names.fromString(name);
    }

    public JCLiteral literal(Object value) {
        return (value == null)
                ? maker.Literal(TypeTag.BOT, null)
                : maker.Literal(value);
    }

    public JCBlock block() {
        return maker.Block(0L, List.nil());
    }

    public JCBlock block(JCStatement statement) {
        return maker.Block(0L, List.of(statement));
    }
}