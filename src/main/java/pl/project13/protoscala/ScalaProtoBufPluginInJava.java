package pl.project13.protoscala;

import google.protobuf.compiler.Plugin;

import java.io.File;
import java.util.List;

/**
 * Date: 3/27/11
 *
 * @author Konrad Malawski
 */
public class ScalaProtoBufPluginInJava {

  public Plugin.CodeGeneratorResponse handle(Plugin.CodeGeneratorRequest generatorRequest) {
    Plugin.CodeGeneratorResponse.Builder responseBuilder = Plugin.CodeGeneratorResponse.newBuilder().setError("Not yet implemented :-)");

    createFolders(generatorRequest.getFileToGenerateList());

    // todo the app's heart and soul

    return responseBuilder.build();
  }

  private void createFolders(List<String> fileNames) {
    for (String fileName : fileNames) {
      File file = new File(fileName);
      System.out.println(file);
    }
  }
}
