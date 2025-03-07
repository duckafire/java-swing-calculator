src=Calculator Display Keyboard KeyIndexes Key Main NumberGroup Viewer MathParser Operations
src:=$(addprefix ./src/, ${src})
src:=$(addsuffix .java, ${src})

destine_dir=./build/

all:
	@javac -g -Xlint:unchecked ${src} -d ${destine_dir}

run:
	@java -cp ${destine_dir} Main
