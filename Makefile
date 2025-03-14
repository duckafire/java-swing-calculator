src_root:=Calculator Main
src_root:=$(addprefix ./src/, ${src_root})
src_root:=$(addsuffix .java, ${src_root})

presentation:=Display Keyboard Key KeyIndex
presentation:=$(addprefix ./src/presentation/, ${presentation})
presentation:=$(addsuffix .java, ${presentation})

algorithm:=DataManager FormatValues MathParser Operator Value OperatorsIcon
algorithm:=$(addprefix ./src/algorithm/, ${algorithm})
algorithm:=$(addsuffix .java, ${algorithm})


destine_dir=./build/

javac_flags:=-g -Xlint:all


ifneq ($(wildcard ./build/*),)
	toclear:=--recursive ./build/*
else
	toclear:=--force
endif


all:
	@rm ${toclear}
	@javac ${javac_flags} ${src_root} ${presentation} ${algorithm} -d ${destine_dir}

run:
	@java -cp ${destine_dir} Main
