package org.transparent.eureka.util;

import com.sun.tools.javac.tree.JCTree.JCExpression;
import com.sun.tools.javac.tree.JCTree.JCLiteral;
import com.sun.tools.javac.util.List;
import org.transparent.eureka.api.factory.MemberFactory;

public final class Lists {
    private Lists() {}

    public static List<JCExpression> literalList(MemberFactory factory, Object... values) {
        List<JCExpression> literals = List.nil();
        for (Object value : values) {
            final JCLiteral literal = (value == null)
                    ? factory.literal()
                    : factory.literal(value);
            literals = literals.append(literal);
        }
        return literals;
    }
}
