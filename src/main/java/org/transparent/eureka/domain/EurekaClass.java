package org.transparent.eureka.domain;

import com.sun.source.tree.Tree;
import com.sun.tools.javac.tree.JCTree.*;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;
import org.transparent.eureka.util.CommonUtils;

import javax.lang.model.element.Modifier;

public class EurekaClass implements EurekaObject<JCClassDecl> {
    private final List<EurekaAnnotation> annotations;
    private final List<Modifier> modifiers;
    private final String name;
    private final List<EurekaTypeParameter> typeParameters;
    private final String superclass;
    private final List<String> interfaces;

    public EurekaClass(List<EurekaAnnotation> annotations, List<Modifier> modifiers, String name, List<EurekaTypeParameter> typeParameters, String superclass, List<String> interfaces) {
        this.annotations = annotations;
        this.modifiers = modifiers;
        this.name = name;
        this.typeParameters = typeParameters;
        this.superclass = superclass;
        this.interfaces = interfaces;
    }

    @Override
    public JCClassDecl toTree(TreeMaker maker, Names names) {
        final JCModifiers mods = CommonUtils.getModifiers(annotations, modifiers, maker, names);
        final Name name = names.fromString(this.name);

        return maker.ClassDef(
                mods,
                name,
                CommonUtils.listToTree(typeParameters, maker, names),
                null, // TODO
                List.nil(), // TODO
                List.nil() // TODO
        );
    }

    @Override
    public Tree.Kind getKind() {
        return Tree.Kind.CLASS;
    }

    @Override
    public Tag getTag() {
        return Tag.CLASSDEF;
    }

    public List<EurekaAnnotation> getAnnotations() {
        return annotations;
    }

    public List<Modifier> getModifiers() {
        return modifiers;
    }

    public String getName() {
        return name;
    }

    public List<EurekaTypeParameter> getTypeParameters() {
        return typeParameters;
    }

    public String getSuperclass() {
        return superclass;
    }

    public List<String> getInterfaces() {
        return interfaces;
    }
}