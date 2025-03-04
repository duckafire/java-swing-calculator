src_root=Main Calculator Display Keyboard
src_root:=$(addprefix ./src/, ${src_root})
src_root:=$(addsuffix .java, ${src_root})

buttons=AdaptableButtonFont ClearButton DeleteButton FloatPointButton NumberButton OperatorButton ResultButton
buttons:=$(addprefix ./src/buttons/, ${buttons})
buttons:=$(addsuffix .java, ${buttons})

destine_dir=./classes

all:
	@javac -g -Xlint:unchecked ${src_root} ${buttons} -d ${destine_dir}

run:
	@java -cp "./classes/" Main
