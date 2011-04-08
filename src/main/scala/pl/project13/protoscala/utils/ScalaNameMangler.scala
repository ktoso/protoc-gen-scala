package pl.project13.protoscala.utils

import java.lang.String

/**
 * Date: 3/28/11
 *
 * @author Konrad Malawski
 */
class ScalaNameMangler {

  val scalaKeywords = "abstract" :: "do"        :: "finally"  :: "import"  :: "object"    ::
                      "return"   :: "trait"     :: "var"      :: "_"       :: ":"         ::
                      "case"     :: "else"      :: "for"      :: "="       :: "=>"        ::
                      "lazy"     :: "override"  :: "sealed"   :: "try"     :: "while"     ::
                      "package"  :: "super"     :: "if"       :: "new"     :: "private"   ::
                      "<-"       :: "catch"     :: "extends"  :: "forSome" :: "match"     ::
                      "true"     :: "with"      :: "<:"       :: "class"   :: "false"     ::
                      "this"     :: "type"      :: "yield"    :: "<%"      :: ">:"        ::
                      "def"      :: "final"     :: "implicit" :: "null"    :: "protected" ::
                      "throw"    :: "val"       :: "#"        :: "@"       :: Nil


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

  def mangle(possiblyUnsafeName: String): String = {
    possiblyUnsafeName + "_"
  }
}