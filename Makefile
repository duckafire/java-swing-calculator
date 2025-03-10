src_root:=Calculator Main
src_root:=$(addprefix ./src/, ${src_root})
src_root:=$(addsuffix .java, ${src_root})

interface:=Display Keyboard Key
interface:=$(addprefix ./src/presentation/, ${interface})
interface:=$(addsuffix .java, ${interface})

destine_dir=./build/

ifneq ($(wildcard ./build/*),)
	toclear:=--recursive ./build/*
else
	toclear:=--force
endif

all:
	@rm ${toclear}
	@javac -g -Xlint:unchecked ${src_root} ${interface} -d ${destine_dir}

run:
	@java -cp ${destine_dir} Main
