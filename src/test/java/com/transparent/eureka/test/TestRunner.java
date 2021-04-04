package com.transparent.eureka.test;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.transparent.diamond.compiletest.CompileTestRunner;
import org.transparent.diamond.compiletest.CompileTestUtils;
import org.transparent.eureka.EurekaFactory;
import org.transparent.lucent.processor.LucentProcessor;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

public class TestRunner {

    @TestFactory
    public Stream<DynamicTest> unitTestingGoBrr() throws CompileTestRunner.CompilerException, IOException {
        return CompileTestRunner.runProcessorTestsIn(
                new File("./src/test/processors/").getAbsoluteFile(),
                Arrays.asList(
                        CompileTestUtils.locationOfJar(LucentProcessor.class),
                        CompileTestUtils.locationOfJar(EurekaFactory.class),
                        CompileTestUtils.locationOfJar(TestRunner.class)
                )
        );
    }

}
