package org.transparent.eureka.domain;

import com.sun.source.tree.Tree;
import com.sun.tools.javac.tree.JCTree.*;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.Names;

import java.util.List;

public class EurekaAnnotation implements EurekaObject<JCAnnotation> {
    private final String name;
    private final List<AnnotationAttribute> attributes;

    public EurekaAnnotation(String name, List<AnnotationAttribute> attributes) {
        this.name = name;
        this.attributes = attributes;
    }

    // TODO
    @Override
    public JCAnnotation toTree(TreeMaker maker, Names names) {
        return null;
    }

    @Override
    public Tree.Kind getKind() {
        return Tree.Kind.ANNOTATION;
    }

    @Override
    public Tag getTag() {
        return Tag.ANNOTATION;
    }

    public String getName() {
        return name;
    }

    public List<AnnotationAttribute> getAttributes() {
        return attributes;
    }

    public static class AnnotationAttribute {
        private final String name;
        private final JCExpression value;

        public AnnotationAttribute(String name, JCExpression value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public JCExpression getValue() {
            return value;
        }
    }
}
