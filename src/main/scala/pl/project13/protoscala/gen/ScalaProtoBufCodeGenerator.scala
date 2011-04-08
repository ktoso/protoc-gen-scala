package pl.project13.protoscala.gen

import scalaj.collection.Imports._
import com.google.protobuf.DescriptorProtos
import google.protobuf.compiler.Plugin
import pl.project13.protoscala.utils.CommentsGenerator
import pl.project13.protoscala.utils.ScalaNameMangler
import java.util.logging.Logger

/**
 * Date: 3/27/11
 *
 * @author Konrad Malawski
 */
class ScalaProtoBufCodeGenerator {

  override def handle(request: Plugin.CodeGeneratorRequest): Plugin.CodeGeneratorResponse = {
    val responseBuilder: Plugin.CodeGeneratorResponse.File.Builder = Plugin.CodeGeneratorResponse.newBuilder
    try {
      val orBuilderList = request.getProtoFileOrBuilderList.asScala
      for (protoFile <- orBuilderList) {
        log.info("handleOneProtoFile: name: " + protoFile.getName + ", package: " + protoFile.getPackage)
        handleOneProtoFile(responseBuilder, protoFile)
      }
    } catch {
      case ex: Exception => {
        responseBuilder.setError("An '" + ex.getClass.getSimpleName + "' exception occurred, could not compile proto file!")
      }
    }
    log.info("Done.")
    return responseBuilder.build
  }

  private def handleOneProtoFile(responseBuilder: Plugin.CodeGeneratorResponse.Builder, protoFile: DescriptorProtos.FileDescriptorProtoOrBuilder): Unit = {
    val fileBuilder: Plugin.CodeGeneratorResponse.File.Builder = Plugin.CodeGeneratorResponse.File.getDefaultInstance.newBuilderForType
    commentsGenerator.initialComment(sourceStringBuilder, protoFile)
    handleDependencies(protoFile)
    val options: DescriptorProtos.FileOptions = protoFile.getOptions
    val javaPackage: String = options.getJavaPackage
    fileBuilder.setContent(sourceStringBuilder.toString)
    fileBuilder.setName(nameManglerNameMangler.escapeFileName("TestFile"))
    responseBuilder.addFile(fileBuilder.build)
  }

  private def handleDependencies(protoFile: DescriptorProtos.FileDescriptorProtoOrBuilder): Unit = {
    for (dependency <- protoFile.getDependencyList) {
      log.info("Add dependency + " + dependency)
      sourceStringBuilder.append("import ").append(dependency)
    }
  }

  private var log                   : Logger            = Logger.getLogger(getClass.getSimpleName)
  private var sourceStringBuilder   : StringBuilder     = new StringBuilder
  private var nameManglerNameMangler: ScalaNameMangler  = new ScalaNameMangler
  private var commentsGenerator     : CommentsGenerator = new CommentsGenerator

}

