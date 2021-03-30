![Eureka](https://github.com/project-transparent/eureka/blob/main/eureka.png)

**Eureka** is a fluent API surrounding the internal Javac tree code that allows developers to perform compile-time AST changes in Java more easily.

## Overview

The internal Javac tree code is subject to many issues, including being repetitious and having sparse documentation. It also takes a bit of trial and error to figure out how to achieve a specific task.

Eureka attempts to add an easy-to-use interface for said code by supplying the developer with a fluent structure.

### Requirements
Eureka is designed to use JDK 8 but supports later versions, such versions may be unstable with this project at the moment.

## Installation (Gradle - Local)

1. Clone the repo (https://github.com/project-transparent/eureka).
2. Run `gradlew publishToMavenLocal` in the root directory of the repo.
3. Add `mavenLocal()` to your repositories.
4. Add `implementation 'org.transparent:eureka:<version>'` to your dependencies.
