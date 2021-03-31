package org.transparent.eureka.api.factory;

import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.tree.JCTree.*;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Name;
import org.transparent.eureka.impl.builder.FieldBuilder;
import org.transparent.eureka.impl.builder.MethodBuilder;

public interface MemberFactory extends Factory {
    FieldBuilder field();

    MethodBuilder method();

    Name name(String name);

    JCModifiers mods(long flags);

    JCModifiers mods(long flags, List<JCAnnotation> annotations);

    JCModifiers annotations(List<JCAnnotation> annotations);

    JCExpression id(String id);

    JCPrimitiveTypeTree id(TypeTag tag);

    JCExpression id(JCVariableDecl parameter);

    List<JCExpression> ids(List<JCVariableDecl> parameters);

    JCLiteral literal(TypeTag tag, Object value);

    JCLiteral literal(Object value);

    JCLiteral literal();
}