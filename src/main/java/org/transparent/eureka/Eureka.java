package org.transparent.eureka;

import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.Names;

public final class Eureka {
    private Eureka() {}

    public static EurekaFactory factory(Names names, TreeMaker factory) {
        return new EurekaFactory(names, factory);
    }
}
