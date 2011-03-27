package pl.project13.protoscala

import org.scalatest.junit.AssertionsForJUnit
import scala.collection.mutable.ListBuffer
import org.junit.Assert._
import org.junit.Test
import org.junit.Before
import java.io.{FileOutputStream, FileInputStream, File}
import google.protobuf.compiler.Plugin
import java.util.logging.Logger
import java.lang.String

/**
 * Date: 3/27/11
 * @author Konrad Malawski
 */
class EndToEndTestScala extends AssertionsForJUnit {
  var logger: Logger = Logger.getAnonymousLogger

  var mainRunner: MainRunner = new MainRunner

  var protoInFilePath: String = "src/test/proto/pl/project13/test/test_proto.proto"
  var outFilePath    : String = "src/test/resources/pl/project13/test/test_proto.out"

  @Before def setUp() {
    val inputStream: FileInputStream = new FileInputStream(new File(protoInFilePath))
    mainRunner.setIn(inputStream)

    val outputStream: FileOutputStream = new FileOutputStream(new File(outFilePath))
    mainRunner.setOut(outputStream)

    mainRunner.setDebugMode(true)
  }

  @Test def shouldHaveNotImplementedYetError() {
    // for now... :-)
    mainRunner.run

    val inputStream: FileInputStream = new FileInputStream(new File(outFilePath))
    val codeGeneratorResponse: Plugin.CodeGeneratorResponse = Plugin.CodeGeneratorResponse.parseFrom(inputStream)
    val errorMessage = codeGeneratorResponse.getError()

    logger.info("errorMessage was: " + errorMessage)
    assert(errorMessage === "Not yet implemented :-)")
  }
}
