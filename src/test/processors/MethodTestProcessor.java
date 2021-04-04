import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeTranslator;
import com.transparent.eureka.test.TestProcessorBase;
import org.transparent.diamond.compiletest.ProcessorTest;
import org.transparent.eureka.EurekaFactory;

import java.lang.annotation.ElementType;

import static com.sun.tools.javac.code.Flags.PUBLIC;
import static com.sun.tools.javac.code.TypeTag.VOID;

@ProcessorTest(
        target = ElementType.TYPE,
        expected = {
                "public class Example {",
                "    public void generated() {}",
                "}",
        }
)
public class MethodTestProcessor extends TestProcessorBase {
    public MethodTestProcessor() {
        setVisitor(new TreeTranslator() {
            @Override
            public void visitClassDef(JCTree.JCClassDecl tree) {
                super.visitClassDef(tree);
                EurekaFactory factory = new EurekaFactory(names, MethodTestProcessor.this.factory);
                tree.defs = tree.defs.append(factory.method()
                        .mods(PUBLIC)
                        .type(VOID)
                        .name("generated")
                        .build()
                );
                result = tree;
            }
        });
    }
}