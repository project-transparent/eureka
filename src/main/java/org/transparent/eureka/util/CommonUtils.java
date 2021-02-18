package org.transparent.eureka.util;

import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.JCTree.*;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Names;
import org.transparent.eureka.domain.EurekaAnnotation;
import org.transparent.eureka.domain.EurekaObject;

import javax.lang.model.element.Modifier;

public final class CommonUtils {
    private CommonUtils() {}

    public static JCModifiers getModifiers(List<EurekaAnnotation> annotations, List<Modifier> modifiers, TreeMaker maker, Names names) {
        List<JCAnnotation> jcAnnotations = listToTree(annotations, maker, names);
        long flags = 0L;
        for (Modifier modifier : modifiers)
            flags |= getModifierFlag(modifier);
        return maker.Modifiers(flags, jcAnnotations);
    }

    public static long getModifierFlag(Modifier modifier) {
        switch (modifier) {
            case PUBLIC:        return Flags.PUBLIC;
            case PROTECTED:     return Flags.PROTECTED;
            case PRIVATE:       return Flags.PRIVATE;
            case ABSTRACT:      return Flags.ABSTRACT;
            case STATIC:        return Flags.STATIC;
            case FINAL:         return Flags.FINAL;
            case TRANSIENT:     return Flags.TRANSIENT;
            case VOLATILE:      return Flags.VOLATILE;
            case SYNCHRONIZED:  return Flags.SYNCHRONIZED;
            case NATIVE:        return Flags.NATIVE;
            case STRICTFP:      return Flags.STRICTFP;

            default: return 0L;
        }
    }

    public static <T extends JCTree> List<T> listToTree(List<? extends EurekaObject<T>> objects, TreeMaker maker, Names names) {
        List<T> trees = List.nil();
        for (EurekaObject<T> object : objects)
            trees = trees.append(object.toTree(maker, names));
        return trees;
    }
}