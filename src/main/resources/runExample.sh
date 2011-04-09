#!/bin/sh

PATH=.:$PATH

SCALA_OUT=../out/scala/
JAVA_OUT=../out/java/

protoc ../src/test/proto/test/test.proto --proto_path=../src/test/proto/ --java_out=$JAVA_OUT --scala_out=$SCALA_OUT