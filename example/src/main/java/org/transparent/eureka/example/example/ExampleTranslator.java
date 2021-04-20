package org.transparent.eureka.example.example;

import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.JCTree.*;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.Names;
import org.transparent.eureka.EurekaFactory;
import org.transparent.lucent.transform.LucentTranslator;
import org.transparent.lucent.transform.LucentValidator;

import javax.lang.model.element.Element;

import static org.transparent.eureka.util.Modifiers.PRIVATE_FINAL;
import static org.transparent.eureka.util.Modifiers.PUBLIC_STATIC;

public final class ExampleTranslator extends LucentTranslator {
    private final EurekaFactory factory;

    public ExampleTranslator(Names names, TreeMaker factory, LucentValidator validator) {
        super(names, factory, validator);
        this.factory = new EurekaFactory(names, factory);
    }

    @Override
    public void visitClassDef(JCClassDecl tree) {
        super.visitClassDef(tree);
        tree.defs = tree.defs.append(factory.field()
                .mods(PRIVATE_FINAL)
                .type("String[]")
                .name("myArray")
                .value("new String[]{\"one\", \"two\"}")
                .build());
        tree.defs = tree.defs.append(factory.field()
                .mods(PRIVATE_FINAL)
                .type(String.class)
                .name("myField")
                .value("\"This is my field.\"")
                .build());
        tree.defs = tree.defs.append(factory.method()
                .mods(PUBLIC_STATIC)
                .type(TypeTag.VOID)
                .name("myMethod")
                .body(factory.statements(
                        "if (\"Clearly not null\" != null)",
                        "    System.out.println(\"If statement test.\");",
                        "System.out.println(\"Hello, world!\");"
                ))
                .build());
        System.out.println(tree);
        result = tree;
    }
}