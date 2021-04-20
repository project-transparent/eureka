package org.transparent.eureka.util;

import com.sun.tools.javac.tree.JCTree.JCExpression;
import com.sun.tools.javac.tree.JCTree.JCLiteral;
import com.sun.tools.javac.util.List;
import org.transparent.eureka.EurekaFactory;

public final class Lists {
    private Lists() {}

    public static List<JCExpression> literalList(EurekaFactory factory, Object... values) {
        List<JCExpression> literals = List.nil();
        for (Object value : values) {
            final JCLiteral literal = (value == null)
                    ? factory.utils.literal(null)
                    : factory.utils.literal(value);
            literals = literals.append(literal);
        }
        return literals;
    }
}
