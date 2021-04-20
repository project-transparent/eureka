package com.transparent.eureka.test;

import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.Names;
import org.transparent.diamond.compiletest.AnnotationForTesting;
import org.transparent.lucent.processor.LucentProcessor;
import org.transparent.lucent.transform.LucentTranslator;
import org.transparent.lucent.transform.LucentValidator;

import javax.lang.model.element.Element;
import java.lang.annotation.Annotation;

public abstract class TestProcessorBase extends LucentProcessor {

    static class TestTranslatorBase extends LucentTranslator {
        public TestTranslatorBase(Names names, TreeMaker factory, LucentValidator validator) {
            super(names, factory, validator);
        }
    }

    private JCTree.Visitor visitor = null;

    // Visitor is not passed in through the constructor
    // because `super.factory` is used in most visitors.
    protected void setVisitor(JCTree.Visitor visitor) {
        if(this.visitor == null) {
            this.visitor = visitor;
        } else {
            throw new IllegalStateException("Cannot set visitor twice!");
        }
    }

    @Override
    public Class<? extends Annotation> getSupportedAnnotation() {
        return AnnotationForTesting.class;
    }

    @Override
    public LucentTranslator getTranslator() {
        return translator(TestTranslatorBase::new);
    }

}
