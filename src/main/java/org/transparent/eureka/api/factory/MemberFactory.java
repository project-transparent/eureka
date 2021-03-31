package org.transparent.eureka.api.factory;

import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.tree.JCTree.*;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Name;
import org.transparent.eureka.api.builder.TreeBuilder;

public interface MemberFactory extends Factory {
    TreeBuilder<JCVariableDecl> field();

    TreeBuilder<JCMethodDecl> method();

    Name name(String name);

    JCModifiers mods(long flags);

    JCModifiers mods(long flags, JCAnnotation... annotations);

    JCModifiers annotations(JCAnnotation... annotations);

    JCExpression id(String id);

    JCPrimitiveTypeTree id(TypeTag tag);

    JCExpression id(JCVariableDecl parameter);

    List<JCExpression> ids(JCVariableDecl... parameters);

    JCLiteral literal(TypeTag tag, Object value);

    JCLiteral literal(Object value);

    JCLiteral literal();
}