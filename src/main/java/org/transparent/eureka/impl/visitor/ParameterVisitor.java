package org.transparent.eureka.impl.visitor;

import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.visitor.GenericVisitorAdapter;
import com.sun.tools.javac.tree.JCTree.JCVariableDecl;

public class ParameterVisitor extends GenericVisitorAdapter<JCVariableDecl, Void> {
    @Override
    public JCVariableDecl visit(Parameter n, Void arg) {
        return super.visit(n, arg);
    }
}