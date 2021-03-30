package org.transparent.eureka.api.factory;

import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.tree.JCTree.*;
import com.sun.tools.javac.util.List;
import org.transparent.eureka.tree.builder.BlockBuilder;

public interface TreeFactory {
    JCModifiers mods();

    JCModifiers mods(long flags);

    JCModifiers mods(long flags, List<JCAnnotation> annotations);

    JCModifiers mods(List<JCAnnotation> annotations);

    JCExpression id(JCVariableDecl parameter);

    JCExpression id(String id);

    JCIdent id(Symbol symbol);

    List<JCExpression> ids(List<JCVariableDecl> parameters);

    JCPrimitiveTypeTree id(TypeTag tag);

    JCLiteral literal(TypeTag tag, Object value);

    JCLiteral literal(Object value);

    JCLiteral literal();

    JCVariableDecl field(JCModifiers mods, String name,
                          JCExpression type, JCExpression init);

    JCVariableDecl field(String name, JCExpression type, JCExpression init);

    JCVariableDecl field(JCModifiers mods, String name,
                         JCExpression type);

    JCVariableDecl field(String name, JCExpression type);

    JCMethodDecl method(JCModifiers mods, String name, JCExpression returnType, JCBlock body);

    JCMethodDecl method(String name, JCExpression returnType, JCBlock body);

    JCMethodDecl method(String name, JCBlock body);

    JCBlock block(long flags);

    BlockBuilder block();
}