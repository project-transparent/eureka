<img src="https://github.com/project-transparent/eureka/blob/main/eureka.png" alt="Eureka" width="500" height="175"/>

**Eureka** is a fluent API surrounding the internal Javac tree code that allows developers to perform compile-time AST changes in Java more easily.

*See also: [Lucent](https://github.com/project-transparent/lucent)*

## Overview
The internal Javac tree code is subject to many issues, including being repetitious and having sparse documentation. It also takes a bit of trial and error to figure out how to achieve a specific task.
Eureka attempts to add an easy-to-use interface for said code by supplying the developer with a fluent structure.

### Requirements
Eureka requires JDK 8 and is incompatible with later versions of Java, as later versions no longer have the required `tools.jar` dependency from the JDK.

### Installation (Gradle)
**TODO**
