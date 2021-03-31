package org.transparent.eureka.api.factory;

import com.sun.tools.javac.tree.JCTree.*;
import com.sun.tools.javac.util.List;
import org.transparent.eureka.impl.builder.ArrayBuilder;
import org.transparent.eureka.impl.builder.BlockBuilder;
import org.transparent.eureka.impl.builder.statement.ForBuilder;
import org.transparent.eureka.impl.builder.statement.SwitchBuilder;

public interface StatementFactory extends Factory {
    ArrayBuilder array();

    BlockBuilder block();

    JCReturn returnStat(Object value);

    JCAssert assertion(JCExpression condition, Object detail);

    JCWhileLoop whileLoop(JCExpression condition, Object block);

    JCDoWhileLoop doLoop(JCExpression condition, Object block);

    ForBuilder forLoop();

    JCEnhancedForLoop forEach(JCVariableDecl variable, JCExpression condition,
                                  Object block);

    JCLabeledStatement label(String label, JCStatement statement);

    JCIf ifStat(JCExpression condition, Object then, Object or);

    JCIf ifStat(JCExpression condition, Object then);

    SwitchBuilder switchStat();

    JCContinue continueStat(String label);

    JCBreak breakStat(String label);

    JCExpressionStatement exprStat(JCExpression expr);

    JCBinary bin(Tag operator, Object lhs, Object rhs);

    JCAssignOp assign(Tag operator, Object lhs, Object rhs);

    JCAssign assign(Object lhs, Object rhs);

    JCExpressionStatement call(String name, Object... args);
}
