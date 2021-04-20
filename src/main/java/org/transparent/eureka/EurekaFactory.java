package org.transparent.eureka;

import com.github.javaparser.StaticJavaParser;
import com.sun.tools.javac.tree.JCTree.*;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.Names;
import org.transparent.eureka.api.factory.Factory;
import org.transparent.eureka.impl.builder.FieldBuilder;
import org.transparent.eureka.impl.builder.MethodBuilder;
import org.transparent.eureka.impl.visitor.ExpressionVisitor;
import org.transparent.eureka.impl.visitor.StatementVisitor;
import org.transparent.eureka.util.Utils;

public class EurekaFactory implements Factory {
    public final Names names;
    public final TreeMaker maker;
    public final Utils utils;
    private JCClassDecl target;

    public EurekaFactory(Names names, TreeMaker maker) {
        this.names = names;
        this.maker = maker;
        this.utils = new Utils(names, maker);
    }

    public FieldBuilder field() {
        return new FieldBuilder(this);
    }

    public MethodBuilder method() {
        return new MethodBuilder(this);
    }

    @Override
    public JCExpression expression(String expr) {
        return StaticJavaParser
                .parseExpression(expr)
                .accept(new ExpressionVisitor(maker, utils, target), null);
    }

    @Override
    public JCStatement statements(String... stats) {
        return StaticJavaParser
                .parseBlock('{' + String.join("\n", stats) + '}')
                .accept(new StatementVisitor(maker, utils, target), null);
    }

    public void target(JCClassDecl target) {
        this.target = target;
    }
}