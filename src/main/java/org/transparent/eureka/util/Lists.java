package org.transparent.eureka.util;

import com.sun.tools.javac.tree.JCTree.JCExpression;
import com.sun.tools.javac.util.List;
import org.transparent.eureka.api.factory.TreeFactory;

public final class Lists {
    private Lists() {}

    public static List<JCExpression> literals(TreeFactory factory, Object... values) {
        List<JCExpression> literals = List.nil();
        for (Object value : values) {
            literals = literals.append(
                    factory.literal(value));
        }
        return literals;
    }
}