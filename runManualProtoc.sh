#!/bin/sh
protoc src/test/proto/test/test_dependency.proto --proto_path=src/test/proto/ --java_out=src/test/proto_out/java/
protoc src/test/proto/test/test.proto --proto_path=src/test/proto/ --java_out=src/test/proto_out/java/
