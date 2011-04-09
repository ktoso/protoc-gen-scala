package pl.project13.protoscala.gen;

import com.google.protobuf.DescriptorProtos;
import google.protobuf.compiler.Plugin;
import pl.project13.protoscala.utils.CommentsGenerator;
import pl.project13.protoscala.utils.ScalaNameMangler;
import pl.project13.protoscala.utils.SourceStringBuilder;

import java.util.logging.Logger;

/**
 * Date: 3/27/11
 *
 * @author Konrad Malawski
 */
public class ScalaProtoBufPluginInJava {

  Logger log = Logger.getLogger(getClass().getSimpleName());

  private SourceStringBuilder sourceStringBuilder = new SourceStringBuilder(); // todo may get more specialized?

  private ScalaNameMangler  nameManglerNameMangler = new ScalaNameMangler();
  private CommentsGenerator commentsGenerator      = new CommentsGenerator();

  // code generators
  private MessageFieldGenerator  messageFieldGenerator;
  private RepeatedFieldGenerator repeatedFieldGenerator;

  public Plugin.CodeGeneratorResponse handle(Plugin.CodeGeneratorRequest request) {
    Plugin.CodeGeneratorResponse.Builder responseBuilder = Plugin.CodeGeneratorResponse.newBuilder();

    try {
      // todo the app's heart and soul
      for (DescriptorProtos.FileDescriptorProto protoFile : request.getProtoFileList()) {
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

    handleComments(protoFile);

    handlePackage(protoFile);

    handleDependencies(protoFile);

    handleClassBody(protoFile);

    String fileName = nameManglerNameMangler.escapeFileName(protoFile.getName());
    fileBuilder.setName(fileName);

    String sourceCode = sourceStringBuilder.toString();
    fileBuilder.setContent(sourceCode);

    responseBuilder.addFile(fileBuilder);
  }

  private void handlePackage(DescriptorProtos.FileDescriptorProtoOrBuilder protoFile) {
    String javaPackage = protoFile.getOptions().getJavaPackage();
    sourceStringBuilder.declarePackage(javaPackage);
  }

  private void handleComments(DescriptorProtos.FileDescriptorProtoOrBuilder protoFile) {
    commentsGenerator.initialComment(sourceStringBuilder, protoFile);
  }

  private void handleDependencies(DescriptorProtos.FileDescriptorProtoOrBuilder protoFile) {
    // todo that's most probably wrong ;-)
    for (String dependency : protoFile.getDependencyList()) {
      log.info("Add dependency + " + dependency);
      sourceStringBuilder.append("import ").append(dependency);
    }
  }

  /**
   * todo this will have a better architecture (a waaaay batter one, I'm just looking at what we have to code against :-))
   */
  private void handleClassBody(DescriptorProtos.FileDescriptorProtoOrBuilder protoFile) {
    //todo fix this, inner loops suck

    for (DescriptorProtos.DescriptorProto descriptorProto : protoFile.getMessageTypeList()) {

      sourceStringBuilder.append("case class ").append(descriptorProto.getName()).append("()");

      // handle all fields
      for (DescriptorProtos.FieldDescriptorProto fieldDescriptorProto : descriptorProto.getFieldList()) {
        handleField(fieldDescriptorProto, protoFile);
      }

      // handle enum types
      for (DescriptorProtos.EnumDescriptorProto enumDescriptor : descriptorProto.getEnumTypeList()) {
        handleEnumType(enumDescriptor, protoFile);
      }

      // handle nested types
      // todo this would be generated inner classes etc, hm...
      for (DescriptorProtos.DescriptorProto nestedType : descriptorProto.getNestedTypeList()) {
        handleNestedType(sourceStringBuilder, nestedType); // for example like this
      }

      // handle extensions
      // todo maybe latter on
      for (DescriptorProtos.FieldDescriptorProto fieldDescriptorProto : descriptorProto.getExtensionList()) {
        handleExtension(fieldDescriptorProto, protoFile);
      }

      // handle extension ranges
      // todo maybe latter on
      for (DescriptorProtos.DescriptorProto.ExtensionRange extensionRange : descriptorProto.getExtensionRangeList()) {
        handleExtensionRange(extensionRange, protoFile); //todo not sure what "extension range" is for now
      }
    }
    String name = protoFile.getName();
    sourceStringBuilder.append("case class ").append(name); // todo possibly encapsulate this
  }

  private void handleNestedType(SourceStringBuilder sourceStringBuilder, DescriptorProtos.DescriptorProto nestedType) {
    // todo handle this with THIS class? Rename it to Type generator maybe etc?
  }

  private void handleExtensionRange(DescriptorProtos.DescriptorProto.ExtensionRange extensionRange, DescriptorProtos.FileDescriptorProtoOrBuilder protoFile) {
    //todo handle me (use *FieldGenerator)
  }

  private void handleExtension(DescriptorProtos.FieldDescriptorProto fieldDescriptorProto, DescriptorProtos.FileDescriptorProtoOrBuilder protoFile) {
    //todo handle me (use *FieldGenerator)
  }

  private void handleEnumType(DescriptorProtos.EnumDescriptorProto enumDescriptor, DescriptorProtos.FileDescriptorProtoOrBuilder protoFile) {
    //todo handle me (use *FieldGenerator)
  }

  private void handleField(DescriptorProtos.FieldDescriptorProto fieldDescriptorProto, DescriptorProtos.FileDescriptorProtoOrBuilder protoFile) {
    fieldDescriptorProto.getDefaultValue();
  }

}
