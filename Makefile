src=Calculator Display Keyboard KeyIndexes Key Main NumberGroup Viewer MathParser
src:=$(addprefix ./src/, ${src})
src:=$(addsuffix .java, ${src})

destine_dir=./classes

all:
	@javac -g -Xlint:unchecked ${src} -d ${destine_dir}

run:
	@java -cp "./classes/" Main
