package org.transparent.eureka.api.factory;

import com.sun.tools.javac.tree.JCTree.*;
import org.transparent.eureka.api.builder.TreeBuilder;

public interface StatementFactory extends Factory {
    TreeBuilder<JCNewArray> array();

    TreeBuilder<JCBlock> block();

    JCReturn returnStat(Object value);

    JCAssert assertion(JCExpression condition, Object detail);

    JCWhileLoop whileLoop(JCExpression condition, Object block);

    JCDoWhileLoop doLoop(JCExpression condition, Object block);

    TreeBuilder<JCForLoop> forLoop();

    JCEnhancedForLoop forEach(JCVariableDecl variable, JCExpression condition,
                              Object block);

    JCLabeledStatement label(String label, JCStatement statement);

    JCIf ifStat(JCExpression condition, Object then, Object or);

    JCIf ifStat(JCExpression condition, Object then);

    TreeBuilder<JCSwitch> switchStat(JCExpression selector);

    JCContinue continueStat(String label);

    JCBreak breakStat(String label);

    JCExpressionStatement exprStat(JCExpression expr);

    JCBinary bin(Object lhs, Tag operator, Object rhs);

    JCAssignOp assign(Tag operator, Object lhs, Object rhs);

    JCAssign assign(Object lhs, Object rhs);

    JCExpressionStatement call(String name, Object... args);
}
