package pl.project13.protoscala.utils

import java.lang.String

/**
 * Date: 3/28/11
 *
 * @author Konrad Malawski
 */
class ScalaNameMangler {

  val scalaKeywords = "type" :: "class" :: "var" :: "val" :: "int" :: "double" ::
    "trait" :: "return" :: "implements" :: Nil

  val fileNameExt = ".scala"

  def escapeIdentifier(possiblyUnsafeName: String): String = {
    if (scalaKeywords contains possiblyUnsafeName) {
      return mangle(possiblyUnsafeName)
    }

    possiblyUnsafeName
  }

  def escapeFileName(possiblyUnsafeFilename: String): String = {
    possiblyUnsafeFilename + fileNameExt
  }

  private def mangle(possiblyUnsafeName: String): String = {
    possiblyUnsafeName + "_"
  }
}