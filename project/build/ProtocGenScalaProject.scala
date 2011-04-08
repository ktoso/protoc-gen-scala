import sbt._

class ProtocGenScalaProject(info: ProjectInfo) extends DefaultProject(info) {
  val scalaj_collection = "org.scalaj" %% "scalaj-collection" % "1.0"
}