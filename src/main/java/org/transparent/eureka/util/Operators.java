package org.transparent.eureka.util;

import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.UnaryExpr;
import com.sun.tools.javac.tree.JCTree.Tag;

import static com.sun.tools.javac.tree.JCTree.Tag.*;

public final class Operators {
    private Operators() {}

    public static Tag unOp(UnaryExpr.Operator op) {
        if (op == null) return null;
        switch (op) {
            case PLUS:                  return PLUS;
            case MINUS:                 return MINUS;
            case LOGICAL_COMPLEMENT:    return NOT;
            case BITWISE_COMPLEMENT:    return COMPL;
            case PREFIX_INCREMENT:      return PREINC;
            case PREFIX_DECREMENT:      return PREDEC;
            case POSTFIX_DECREMENT:     return POSTDEC;
            case POSTFIX_INCREMENT:     return POSTINC;
            default: return null;
        }
    }

    public static Tag binOp(BinaryExpr.Operator op) {
        if (op == null) return null;
        switch (op) {
            case OR:                    return OR;
            case AND:                   return AND;
            case BINARY_OR:             return BITOR;
            case XOR:                   return BITXOR;
            case BINARY_AND:            return BITAND;
            case EQUALS:                return EQ;
            case NOT_EQUALS:            return NE;
            case LESS:                  return LT;
            case GREATER:               return GT;
            case LESS_EQUALS:           return LE;
            case GREATER_EQUALS:        return GE;
            case LEFT_SHIFT:            return SL;
            case SIGNED_RIGHT_SHIFT:    return SR;
            case UNSIGNED_RIGHT_SHIFT:  return USR;
            case PLUS:                  return PLUS;
            case MINUS:                 return MINUS;
            case MULTIPLY:              return MUL;
            case DIVIDE:                return DIV;
            case REMAINDER:             return MOD;
            default: return null;
        }
    }

    public static Tag asOp(AssignExpr.Operator op) {
        if (op == null) return null;
        switch (op) {
            case BINARY_OR:             return BITOR;
            case XOR:                   return BITXOR;
            case BINARY_AND:            return BITAND;
            case LEFT_SHIFT:            return SL_ASG;
            case SIGNED_RIGHT_SHIFT:    return SR_ASG;
            case UNSIGNED_RIGHT_SHIFT:  return USR_ASG;
            case PLUS:                  return PLUS_ASG;
            case MINUS:                 return MINUS_ASG;
            case MULTIPLY:              return MUL_ASG;
            case DIVIDE:                return DIV_ASG;
            case REMAINDER:             return MOD_ASG;
            default: return null;
        }
    }

    public static Tag asOp(BinaryExpr.Operator op) {
        return asOp(op.toAssignOperator()
                .orElse(null));
    }
}