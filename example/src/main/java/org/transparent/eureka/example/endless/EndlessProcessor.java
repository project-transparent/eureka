package org.transparent.eureka.example.endless;

import org.transparent.lucent.processor.LucentProcessor;
import org.transparent.lucent.transform.LucentTranslator;
import org.transparent.lucent.util.TypeKind;

import java.lang.annotation.Annotation;

public final class EndlessProcessor extends LucentProcessor {
    @Override
    public LucentTranslator getTranslator() {
        return translator(EndlessTranslator::new);
    }

    @Override
    public TypeKind getSupportedTypeKind() {
        return TypeKind.CLASS;
    }

    @Override
    public Class<? extends Annotation> getSupportedAnnotation() {
        return Endless.class;
    }
}
