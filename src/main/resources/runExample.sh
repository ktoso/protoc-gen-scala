#!/bin/sh

PATH=.:$PATH

SCALA_OUT=../src/test/scala/
JAVA_OUT=../src/test/java/

protoc ../src/test/proto/test/test.proto --proto_path=../src/test/proto/ --java_out=$JAVA_OUT --scala_out=$SCALA_OUT