package pl.project13.protoscala.gen;

import com.google.protobuf.DescriptorProtos;
import google.protobuf.compiler.Plugin;
import pl.project13.protoscala.utils.CommentsGenerator;
import pl.project13.protoscala.utils.ScalaNameMangler;

import java.util.logging.Logger;

/**
 * Date: 3/27/11
 *
 * @author Konrad Malawski
 */
public class ScalaProtoBufPluginInJava {

  Logger log = Logger.getLogger(getClass().getSimpleName());

  private StringBuilder sourceStringBuilder = new StringBuilder(); // todo may get more specialized?

  private ScalaNameMangler  nameManglerNameMangler = new ScalaNameMangler();
  private CommentsGenerator commentsGenerator      = new CommentsGenerator();

  public Plugin.CodeGeneratorResponse handle(Plugin.CodeGeneratorRequest request) {
    Plugin.CodeGeneratorResponse.Builder responseBuilder = Plugin.CodeGeneratorResponse.newBuilder();

    try {
      // todo the app's heart and soul
      for (DescriptorProtos.FileDescriptorProtoOrBuilder protoFile : request.getProtoFileOrBuilderList()) {
        log.info("handleOneProtoFile: name: " + protoFile.getName() + ", package: " + protoFile.getPackage());
        handleOneProtoFile(responseBuilder, protoFile);
      }

    } catch (Exception ex) {
      responseBuilder.setError("An '" + ex.getClass().getSimpleName() + "' exception occurred, could not compile proto file!");
    }

    log.info("Done.");
    return responseBuilder.build();
  }

  private void handleOneProtoFile(Plugin.CodeGeneratorResponse.Builder responseBuilder, DescriptorProtos.FileDescriptorProtoOrBuilder protoFile) {
    Plugin.CodeGeneratorResponse.File.Builder fileBuilder = Plugin.CodeGeneratorResponse.File.getDefaultInstance().newBuilderForType();

    commentsGenerator.initialComment(sourceStringBuilder, protoFile);

    // todo that's wrong ;-)
    handleDependencies(protoFile);

    DescriptorProtos.FileOptions options = protoFile.getOptions();
    String javaPackage = options.getJavaPackage(); // todo how do I set the file's output package? hmm

    fileBuilder.setContent(sourceStringBuilder.toString());
    fileBuilder.setName(nameManglerNameMangler.escapeFileName("TestFile"));

    responseBuilder.addFile(fileBuilder.build());
  }

  private void handleDependencies(DescriptorProtos.FileDescriptorProtoOrBuilder protoFile) {
    // todo that's wrong ;-)
    for (String dependency : protoFile.getDependencyList()) {
      log.info("Add dependency + " + dependency);
      sourceStringBuilder.append("import ").append(dependency);
    }
  }

}
