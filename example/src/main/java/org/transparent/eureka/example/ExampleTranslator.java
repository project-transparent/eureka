package org.transparent.eureka.example;

import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.Names;
import org.transparent.lucent.transform.LucentTranslator;

import javax.lang.model.element.Element;

public final class ExampleTranslator extends LucentTranslator {
    public ExampleTranslator(Names names, TreeMaker factory) {
        super(names, factory);
    }

    @Override
    public void translate(JCTree tree, Element element) {
        // TO IMPLEMENT: When Eureka is in a working state.
    }
}