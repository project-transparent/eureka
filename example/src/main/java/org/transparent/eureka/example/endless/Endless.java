package org.transparent.eureka.example.endless;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Does the following things in a method:
// Creates a local variable --
//      final String[] array = {"first", "second", "third"}
// Adds an endless while loop
//      while(true)
// Adds a foreach loop inside the while loop
//      for (String s : array)
// Prints s
//      System.out.println(s);
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface Endless {
}
