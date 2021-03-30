package org.transparent.eureka.example.example;

import org.transparent.lucent.processor.LucentProcessor;
import org.transparent.lucent.transform.LucentTranslator;

import java.lang.annotation.Annotation;

public final class ExampleProcessor extends LucentProcessor {
    @Override
    public LucentTranslator getTranslator() {
        return translator(ExampleTranslator::new);
    }

    @Override
    public Class<? extends Annotation> getSupportedAnnotation() {
        return Example.class;
    }
}