package pl.project13.protoscala

import gen.ScalaProtoBufCodeGenerator
import google.protobuf.compiler.Plugin
import java.io._

/**
 * Date: 3/27/11
 *
 * @author Konrad Malawski
 */

object MainRunner {
  def main(args: Array[String]): Unit = {
    new MainRunner().run
  }
}

class MainRunner {

  def run = {
    val request: Plugin.CodeGeneratorRequest = Plugin.CodeGeneratorRequest.parseFrom(in)
    val response: Plugin.CodeGeneratorResponse = scalaProtoBufPlugin.handle(request)

    response.writeTo(out)
    out.flush
  }

  private var scalaProtoBufPlugin = new ScalaProtoBufCodeGenerator
  private var in                  = System.in
  private var out                 = System.out
  private var debugMode           = false
}