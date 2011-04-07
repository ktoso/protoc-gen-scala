package pl.project13.protoscala

import google.protobuf.compiler.Plugin
import pl.project13.protoscala.gen.ScalaProtoBufPluginInJava
import java.io._

/**
 * Date: 3/27/11
 *
 * @author Konrad Malawski
 */

trait MainRunnerComponent {
  def mainRunner: MainRunner

  trait MainRunner {
    def run: Unit
  }

}

object MainRunner {
  def main(args: Array[String]): Unit = {
    new MainRunner().run
  }
}

class MainRunner extends MainRunnerComponent with {

  def run = {
    val codeGeneratorRequest: Plugin.CodeGeneratorRequest = Plugin.CodeGeneratorRequest.parseFrom(in)
    val codeGeneratorResponse: Plugin.CodeGeneratorResponse = scalaProtoBufPlugin.handle(codeGeneratorRequest)
    codeGeneratorResponse.writeTo(out)
    out.flush
  }

  private var scalaProtoBufPlugin: ScalaProtoBufPluginInJava = new ScalaProtoBufPluginInJava
  private var in                 : InputStream               = System.in
  private var out                : OutputStream              = System.out
  private var debugMode          : Boolean                   = false
}