src_root:=Calculator Main
src_root:=$(addprefix ./src/, ${src_root})
src_root:=$(addsuffix .java, ${src_root})

interface:=Display Keyboard Key
interface:=$(addprefix ./src/interface/, ${interface})
interface:=$(addsuffix .java, ${interface})

destine_dir=./build/

all:
	@rm ./build/*
	@javac -g -Xlint:unchecked ${src_root} ${interface} -d ${destine_dir}

run:
	@java -cp ${destine_dir} Main
