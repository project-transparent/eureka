package org.transparent.eureka.impl.visitor;

import com.github.javaparser.ast.type.*;
import com.github.javaparser.ast.visitor.GenericVisitorAdapter;
import com.sun.tools.javac.code.BoundKind;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.List;
import org.transparent.eureka.util.Utils;

import static com.sun.tools.javac.code.TypeTag.*;
import static com.sun.tools.javac.tree.JCTree.*;

public class TypeVisitor extends GenericVisitorAdapter<JCExpression, Void> {
    private final TreeMaker maker;
    private final Utils utils;

    public TypeVisitor(TreeMaker maker, Utils utils) {
        this.maker = maker;
        this.utils = utils;
    }

    @Override
    public JCExpression visit(VoidType n, Void arg) {
        return maker.TypeIdent(VOID);
    }

    @Override
    public JCExpression visit(ArrayType n, Void arg) {
        return maker.TypeArray(n.accept(this, arg));
    }

    @Override
    public JCExpression visit(UnionType n, Void arg) {
        List<JCExpression> exprs = List.nil();
        for (ReferenceType type : n.getElements())
            exprs = exprs.append(type.accept(this, arg));
        return maker.TypeUnion(exprs);
    }

    @Override
    public JCExpression visit(WildcardType n, Void arg) {
        if (n.getExtendedType().isPresent()) {
            return maker.Wildcard(
                    maker.TypeBoundKind(BoundKind.EXTENDS),
                    n.getExtendedType().get()
                            .accept(this, arg));
        } else if (n.getSuperType().isPresent()) {
            return maker.Wildcard(
                    maker.TypeBoundKind(BoundKind.SUPER),
                    n.getSuperType().get()
                            .accept(this, arg));
        }
        return null;
    }

    @Override
    public JCExpression visit(PrimitiveType n, Void arg) {
        switch (n.getType()) {
            case BOOLEAN:   return utils.id(BOOLEAN);
            case BYTE:      return utils.id(BYTE);
            case SHORT:     return utils.id(SHORT);
            case INT:       return utils.id(INT);
            case LONG:      return utils.id(LONG);
            case FLOAT:     return utils.id(FLOAT);
            case DOUBLE:    return utils.id(DOUBLE);
            case CHAR:      return utils.id(CHAR);
            default: return null;
        }
    }

    @Override
    public JCExpression visit(ClassOrInterfaceType n, Void arg) {
        return utils.id(n.getNameWithScope());
    }
}