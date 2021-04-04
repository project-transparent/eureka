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
                "    public String test = \"Hello World!\";",
                "}",
        }
)
public class FieldTestProcessor extends TestProcessorBase {
    public FieldTestProcessor() {
        setVisitor(new TreeTranslator() {
            @Override
            public void visitClassDef(JCTree.JCClassDecl tree) {
                super.visitClassDef(tree);
                EurekaFactory factory = new EurekaFactory(names, FieldTestProcessor.this.factory);
                tree.defs = tree.defs.append(factory.field()
                        .mods(PUBLIC)
                        .type(String.class)
                        .name("test")
                        .value("Hello World!")
                        .build()
                );
                result = tree;
            }
        });
    }
}