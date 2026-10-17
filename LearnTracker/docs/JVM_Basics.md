# JVM Basics

The JDK (Java Development Kit) is the complete software kit developers use to write and compile Java code. The JRE (Java Runtime Environment) provides the libraries needed to run that code.
The JVM (Java Virtual Machine) is the engine inside JRE that actually executes it line by line.

## Bytecode

Bytecode is the intermediate, machine-independent code produced when Java source code is compiled (.class file). It acts as a universal bridge, sitting between human-readable Java code and a computer's raw machine code.

## What “Write Once, Run Anywhere” Means

"Write Once, Run Anywhere" means you only need to write and compile your Java code once into bytecode on any computer. That same bytecode file can then be executed on any other device, as long as that specific system has a JVM installed to translate it locally.