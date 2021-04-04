import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeTranslator;
import com.transparent.eureka.test.TestProcessorBase;
import org.transparent.diamond.compiletest.ProcessorTest;
import org.transparent.eureka.EurekaFactory;

import java.lang.annotation.ElementType;

import static com.sun.tools.javac.code.Flags.PUBLIC;

@ProcessorTest(
        target = ElementType.TYPE,
        expected = {
                "public class Example {",
                "    public String[] generated = {",
                "            \"javac\",",
                "            \"go\",",
                "            \"brr\"",
                "   };",
                "}",
        }
)
public class ArrayFieldTestProcessor extends TestProcessorBase {
    public ArrayFieldTestProcessor() {
        setVisitor(new TreeTranslator() {
            @Override
            public void visitClassDef(JCTree.JCClassDecl tree) {
                super.visitClassDef(tree);
                EurekaFactory factory = new EurekaFactory(names, ArrayFieldTestProcessor.this.factory);
                tree.defs = tree.defs.append(factory.array()
                        .mods(PUBLIC)
                        .type(String.class)
                        .name("generated")
                        .elements("javac", "go", "brr")
                        .variable()
                );
                result = tree;
            }
        });
    }
}