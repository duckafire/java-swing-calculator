# Java swing calculator
###### Written with Java 11

There are no what to say about, it is a calculator; no, I did not see nil tutorial
about this before make this project, this fact make me happy :D

### Index
* [Features](#features)
* [Downloading and compiling](#downloading-and-compiling)
* [How to run](#how-to-run)
* [Showcase](#showcase)

## Features

* Operators:
	* [`+`] Addition
	* [`−`] Subtraction
	* [`×`] Multiplication
	* [`÷`] Division
	* [`%`] Percentage calculation
* Support:
	* Negative values
	* Floating-point numbers
* Smart resize (minimum: 220x230)
* Delete values (one by one or all)
* Operators priority (from left to right):
	1. Percentage calculation.
	2. Multiplication / division.
	3. Addition / subtraction.

## Downloading and compiling

##### Dependences:

[openjdk-wiki]: https://en.wikipedia.org/wiki/OpenJDK "openjdk on Wikipedia"
[make-wiki]: https://en.wikipedia.org/wiki/Make_(software) "Make on Wikipedia"
[gnu-make-wiki]: https://www.gnu.org/software/make/ "GNU Make on Wikipedia"
[shell-wiki]: https://en.wikipedia.org/wiki/Shell_%28computing%29 "Shell on Wikipedia"
[sh-wiki]: https://en.wikipedia.org/wiki/Bourne_shell "Bourne Shell on Wikipedia"
[bash-wiki]: https://en.wikipedia.org/wiki/Bash_(Unix_shell) "Bash on Wikipedia"

* [`Git`](https://git-scm.com/)
* [`openjdk`][openjdk-wiki] 11 (or greater)
* [Shell][shell-wiki] based [Bourne Shell][sh-wiki] (like the [Bash][bash-wiki])
* [[GNU]][gnu-make-wiki] [`Make`][make-wiki] (optional)

### With `Make`

```
# download repository
git clone https://github.com/duckafire/java-swing-calculator.git

# enter repository
cd java-swing-calculator

# compile bytecode
make

# compact bytecode to ./bin/
make jar
```

### Without `Make`

```
# download repository
git clone https://github.com/duckafire/java-swing-calculator.git

# enter repository
cd java-swing-calculator

# compile bytecode
mkdir build
javac $(ls ./src/*.java ./src/**/*) -d ./build

# compact bytecode to ./bin/
cd build
jar cfm ../bin/java-swing-calculator.jar ../manifest.txt $(ls ./src/*.java ./src/**/*)
```

> [!NOTE]
> `javac` and `jar` are from `openjdk`.

> [!TIP]
> Maybe some IDE can compile this with *magic*.

## How to run

### With bytecode

###### With `Make`

```
# after bytecode compilation
make run
```

###### Without `Make`

```
# after bytecode compilation
java -cp ./build Main
```

### With `.jar`

```
java -jar ./bin/java-swing-calculator.jar
```

> [!NOTE]
> `java` is from `openjdk`.

## Showcase

> [!NOTE]
> Low FPS (~20).

###### Calculating

![calculating](gifs/calculating.gif "Calculating")

##### Smart resize

![smart-resize](gifs/smart-resize.gif "Smart resize")

> [Buy me a coffee.](https://github.com/sponsors/duckafire)
