#!/bin/sh

JAVA_HOME=/Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home
LIB_PATH=/Users/dam808/Desktop/program1/libs
JAVA_SRC_PATH=/Users/dam808/Desktop/program1/src
JAVA_FILE=/Users/dam808/Desktop/program1/src/Main.java
EXCUTE_CLASS=Main

#classpath
for jarfile in $LIB_PATH/*.jar; do
	CP=$CP:$jarfile
done

CP=$CP:$JAVA_SRC_PATH

echo "Classpath: " $CP

$JAVA_HOME/bin/javac -cp $CP $JAVA_FILE

echo "passed"

$JAVA_HOME/bin/java -cp $CP $EXCUTE_CLASS




