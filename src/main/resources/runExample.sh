#!/bin/sh
protoc ../src/test/proto/test/test.proto --proto_path=../src/test/proto/ --java_out=../src/test/java/ --scala_out=../src/test/scala/