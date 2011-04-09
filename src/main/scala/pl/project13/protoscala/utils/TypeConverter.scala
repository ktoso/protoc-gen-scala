package pl.project13.protoscala.utils

import com.google.protobuf.DescriptorProtos

/**
 * Date: 4/9/11
 * @author Konrad Malawski
 */

object TypeConverter {

  val protoBufTypes = Map(
    "TYPE_STRING" -> "String",
    "TYPE_INT32" -> "Int",
    "TYPE_INT64" -> "Long"
    //    'TYPE_MESSAGE -> _
  )

  def asScalaType(typeId: String, protoField: DescriptorProtos.FieldDescriptorProto): String = {
    protoBufTypes.getOrElse(typeId, protoField.getTypeName) //todo obvious fail, for java compatibility, I WILL remove this when the generator is rewritten to scala
  }
}