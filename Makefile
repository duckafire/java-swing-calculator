src_root:=Calculator Main
src_root:=$(addprefix ./src/, ${src_root})
src_root:=$(addsuffix .java, ${src_root})

presentation:=Display Keyboard Key KeyIndex
presentation:=$(addprefix ./src/presentation/, ${presentation})
presentation:=$(addsuffix .java, ${presentation})

algorithm:=DataManager FormatValues MathParser Operator Value OperatorsIcon
algorithm:=$(addprefix ./src/algorithm/, ${algorithm})
algorithm:=$(addsuffix .java, ${algorithm})


BUILD_DIR=./build/

JAVAC_FLAGS:=-g -Xlint:all


JAR_NAME:=../bin/java-swing-calculator.jar
JAR_FLAGS:=cfm # --create --file --manifest
JAR_MANIFEST:=../manifest.txt

jar_sub_src:=presentation/ algorithm/
jar_sub_src:=$(addprefix calculator/, ${jar_sub_src})

JAR_SRC_ROOT:=calculator/
JAR_SRC:=Main.class $(addsuffix *.class, ${JAR_SRC_ROOT} ${jar_sub_src})


ifneq ($(wildcard ./build/*),)
	TOCLEAR:=--recursive ./build/*
else
	# to avoid generating an erro/warning
	TOCLEAR:=--force
endif


all:
	@rm ${TOCLEAR}
	@javac ${JAVAC_FLAGS} ${src_root} ${presentation} ${algorithm} -d ${BUILD_DIR}

run:
	@java -cp ${BUILD_DIR} Main

jar:
	@cd ./build && jar ${JAR_FLAGS} ${JAR_NAME} ${JAR_MANIFEST} ${JAR_SRC}
