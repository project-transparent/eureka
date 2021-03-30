package org.transparent.eureka.api.factory;

import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.tree.JCTree.*;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Name;
import org.transparent.eureka.tree.builder.BlockBuilder;

public interface TreeFactory {
    Name name(String name);

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

    JCReturn returnStat(JCExpression value);

    JCReturn returnStat(Object value);

    JCAssert assertStat(JCExpression condition, JCExpression detail);

    JCAssert assertStat(JCExpression condition, Object detail);

    JCWhileLoop whileStat(JCExpression condition, BlockBuilder block);

    JCWhileLoop whileStat(JCExpression condition, JCStatement statement);

    JCDoWhileLoop doStat(JCExpression condition, BlockBuilder block);

    JCDoWhileLoop doStat(JCExpression condition, JCStatement statement);

    JCForLoop forStat(List<JCStatement> initializers, JCExpression condition,
                      List<JCExpressionStatement> steps, BlockBuilder block);

    JCForLoop forStat(List<JCStatement> initializers, JCExpression condition,
                      List<JCExpressionStatement> steps, JCStatement statement);

    JCEnhancedForLoop forEachStat(JCVariableDecl variable, JCExpression condition,
                                  BlockBuilder block);

    JCEnhancedForLoop forEachStat(JCVariableDecl variable, JCExpression condition,
                                  JCStatement statement);

    JCLabeledStatement labelledStat(String label, JCStatement statement);

    JCIf ifStat(JCExpression condition, BlockBuilder then, BlockBuilder or);

    JCIf ifStat(JCExpression condition, BlockBuilder then, JCStatement or);

    JCIf ifStat(JCExpression condition, JCStatement then, BlockBuilder or);

    JCIf ifStat(JCExpression condition, JCStatement then, JCStatement or);

    JCIf ifStat(JCExpression condition, BlockBuilder then);

    JCIf ifStat(JCExpression condition, JCStatement then);

    JCSwitch switchStat(JCExpression selector, List<JCCase> cases);

    JCContinue continueStat(String label);

    JCBreak breakStat(String label);

    JCExpressionStatement exprStat(JCExpression expr);

    JCBinary binary(Tag operator, JCExpression lhs, JCExpression rhs);

    JCBinary binary(Tag operator, Object lhs, Object rhs);

    JCBinary binary(Tag operator, Object lhs, JCExpression rhs);

    JCBinary binary(Tag operator, JCExpression lhs, Object rhs);

    JCAssignOp assign(Tag operator, JCExpression lhs, JCExpression rhs);

    JCAssignOp assign(Tag operator, Object lhs, Object rhs);

    JCAssignOp assign(Tag operator, Object lhs, JCExpression rhs);

    JCAssignOp assign(Tag operator, JCExpression lhs, Object rhs);

    JCAssign assign(JCExpression lhs, JCExpression rhs);

    JCAssign assign(Object lhs, Object rhs);

    JCAssign assign(Object lhs, JCExpression rhs);

    JCAssign assign(JCExpression lhs, Object rhs);

    BlockBuilder block();
}