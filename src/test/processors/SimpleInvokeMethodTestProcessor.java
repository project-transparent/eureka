import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeTranslator;
import com.transparent.eureka.test.TestProcessorBase;
import org.transparent.diamond.compiletest.ProcessorTest;
import org.transparent.eureka.EurekaFactory;

import java.lang.annotation.ElementType;

import static com.sun.tools.javac.code.Flags.PUBLIC;
import static com.sun.tools.javac.code.TypeTag.VOID;

@ProcessorTest(
        target = ElementType.METHOD,
        expected = {
                "public class Example {",
                "    public void example() {",
                "        System.out.println(\"made with smiles\");",
                "    }",
                "}",
        }
)
public class SimpleInvokeMethodTestProcessor extends TestProcessorBase {
    public SimpleInvokeMethodTestProcessor() {
        setVisitor(new TreeTranslator() {
            @Override
            public void visitMethodDef(JCTree.JCMethodDecl tree) {
                super.visitMethodDef(tree);
                if(!tree.name.contentEquals("example")) return;
                EurekaFactory factory = new EurekaFactory(names, SimpleInvokeMethodTestProcessor.this.factory);
                tree.body = factory
                        .block()
                        .call("System.out.println", "made with smiles")
                        .build();
                result = tree;
            }
        });
    }
}