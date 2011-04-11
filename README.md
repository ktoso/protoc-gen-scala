How to see how it's doing?
--------------------------
to see how the project is going along do:

	git clone git://github.com/ktoso/protoc-gen-scala.git
	cd protoc-gen-scala
	gradle dist
	cd dist
	./runExample.sh
	cat ../out/scala/test/test.proto.scala

The path may change as I'm working on full package = path support etc etc... :-)

Why?
----
This project was suggested as Summer of Code 2011 Idea and I really liked it and started coding right away :-)
I'm also learning Scala along the way. The app will be rewritten to *pure scala* later on. Not now because exploring types in intellij 
still is kind of better in java than scala and it helps a lot when looking for "where do I get this info from?".

It's currently using Gradle but I've been checking out *sbt* and will most likely switch to it. It's faster and smaller... and in Scala :-) Not that I don't like Gradle but it seems kind of natural for a scala project to use sbt, so I'll do the full switch soon enough... :-)

Tips for myself
---------------
The project is likely to follw the outline as designed by <a href="http://code.google.com/p/protobuf-gwt/source/browse/trunk/protoc-gen-gwt/src/net/ltgt/gwt/protobuf/compiler/Main.java?r=15"> this link</a> from now on.


