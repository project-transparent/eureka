package org.transparent.eureka.example.endless;

import org.transparent.lucent.processor.LucentProcessor;
import org.transparent.lucent.transform.LucentTranslator;
import org.transparent.lucent.util.TypeKind;

import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.Set;

public final class EndlessProcessor extends LucentProcessor {
    @Override
    public LucentTranslator getTranslator() {
        return translator(EndlessTranslator::new);
    }

    @Override
    public Set<TypeKind> getSupportedTypeKinds() {
        return Collections.singleton(TypeKind.CLASS);
    }

    @Override
    public Class<? extends Annotation> getSupportedAnnotation() {
        return Endless.class;
    }
}
