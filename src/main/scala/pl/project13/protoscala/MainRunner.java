package pl.project13.protoscala;

import com.google.common.annotations.VisibleForTesting;
import google.protobuf.compiler.Plugin;
import pl.project13.protoscala.gen.ScalaProtoBufPluginInJava;

import java.io.*;

/**
 * Date: 3/27/11
 *
 * @author Konrad Malawski
 */
public class MainRunner {

  private ScalaProtoBufPluginInJava scalaProtoBufPlugin = new ScalaProtoBufPluginInJava();

  private InputStream  in        = System.in;
  private OutputStream out       = System.out;
  private Boolean      debugMode = false;

  public static void main(String[] args) throws IOException {
    new MainRunner().run();
  }

  public void run() throws IOException {
    Plugin.CodeGeneratorRequest codeGeneratorRequest = Plugin.CodeGeneratorRequest.parseFrom(in);

    Plugin.CodeGeneratorResponse codeGeneratorResponse = scalaProtoBufPlugin.handle(codeGeneratorRequest);

    codeGeneratorResponse.writeTo(out);
    out.flush();
  }

  void setScalaProtoBufPlugin(ScalaProtoBufPluginInJava scalaProtoBufPlugin) {
    this.scalaProtoBufPlugin = scalaProtoBufPlugin;
  }

  void setIn(InputStream in) {
    this.in = in;
  }

  void setOut(OutputStream out) {
    this.out = out;
  }

  public void setDebugMode(Boolean debugMode) {
    this.debugMode = debugMode;
  }
}
