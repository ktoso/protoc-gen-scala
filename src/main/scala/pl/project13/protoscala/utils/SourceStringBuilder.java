package pl.project13.protoscala.utils;

/**
 * Date: 4/9/11
 *
 * @author Konrad Malawski
 */
public class SourceStringBuilder {
  private StringBuilder stringBuilder = new StringBuilder();

  // keywords etc
  private String K_PACKAGE    = "package ";
  private String K_CASE_CLASS = "case class ";

  public SourceStringBuilder appendNewLine() {
    stringBuilder.append("\n");
    return this;
  }

  public SourceStringBuilder appendComment(Object str) {
    stringBuilder.append("// ").append(str);
    return this;
  }

  // delegates ----------------------------------------------------------------

  public SourceStringBuilder append(Object obj) {
    stringBuilder.append(obj);
    return this;
  }

  public SourceStringBuilder append(String str) {
    stringBuilder.append(str);
    return this;
  }

  public SourceStringBuilder append(StringBuffer sb) {
    stringBuilder.append(sb);
    return this;
  }

  public SourceStringBuilder append(CharSequence s) {
    stringBuilder.append(s);
    return this;
  }

  public SourceStringBuilder append(CharSequence s, int start, int end) {
    stringBuilder.append(s, start, end);
    return this;
  }

  public SourceStringBuilder append(char[] str) {
    stringBuilder.append(str);
    return this;
  }

  public SourceStringBuilder append(char[] str, int offset, int len) {
    stringBuilder.append(str, offset, len);
    return this;
  }

  public SourceStringBuilder append(boolean b) {
    stringBuilder.append(b);
    return this;
  }

  public SourceStringBuilder append(char c) {
    stringBuilder.append(c);
    return this;
  }

  public SourceStringBuilder append(int i) {
    stringBuilder.append(i);
    return this;
  }

  public SourceStringBuilder append(long lng) {
    stringBuilder.append(lng);
    return this;
  }

  public SourceStringBuilder append(float f) {
    stringBuilder.append(f);
    return this;
  }

  public SourceStringBuilder append(double d) {
    stringBuilder.append(d);
    return this;
  }

  public SourceStringBuilder appendCodePoint(int codePoint) {
    stringBuilder.appendCodePoint(codePoint);
    return this;
  }

  public SourceStringBuilder delete(int start, int end) {
    stringBuilder.delete(start, end);
    return this;
  }

  public SourceStringBuilder deleteCharAt(int index) {
    stringBuilder.deleteCharAt(index);
    return this;
  }

  public SourceStringBuilder replace(int start, int end, String str) {
    stringBuilder.replace(start, end, str);
    return this;
  }

  public SourceStringBuilder insert(int index, char[] str, int offset, int len) {
    stringBuilder.insert(index, str, offset, len);
    return this;
  }

  public SourceStringBuilder insert(int offset, Object obj) {
    stringBuilder.insert(offset, obj);
    return this;
  }

  public SourceStringBuilder insert(int offset, String str) {
    stringBuilder.insert(offset, str);
    return this;
  }

  public SourceStringBuilder insert(int offset, char[] str) {
    stringBuilder.insert(offset, str);
    return this;
  }

  public SourceStringBuilder insert(int dstOffset, CharSequence s) {
    stringBuilder.insert(dstOffset, s);
    return this;
  }

  public SourceStringBuilder insert(int dstOffset, CharSequence s, int start, int end) {
    stringBuilder.insert(dstOffset, s, start, end);
    return this;
  }

  public SourceStringBuilder insert(int offset, boolean b) {
    stringBuilder.insert(offset, b);
    return this;
  }

  public SourceStringBuilder insert(int offset, char c) {
    stringBuilder.insert(offset, c);
    return this;
  }

  public SourceStringBuilder insert(int offset, int i) {
    stringBuilder.insert(offset, i);
    return this;
  }

  public SourceStringBuilder insert(int offset, long l) {
    stringBuilder.insert(offset, l);
    return this;
  }

  public SourceStringBuilder insert(int offset, float f) {
    stringBuilder.insert(offset, f);
    return this;
  }

  public SourceStringBuilder insert(int offset, double d) {
    stringBuilder.insert(offset, d);
    return this;
  }

  public int indexOf(String str) {
    return stringBuilder.indexOf(str);
  }

  public int indexOf(String str, int fromIndex) {
    return stringBuilder.indexOf(str, fromIndex);
  }

  public int lastIndexOf(String str) {
    return stringBuilder.lastIndexOf(str);
  }

  public int lastIndexOf(String str, int fromIndex) {
    return stringBuilder.lastIndexOf(str, fromIndex);
  }

  @Override
  public String toString() {
    return stringBuilder.toString();
  }

  public int length() {
    return stringBuilder.length();
  }

  public int capacity() {
    return stringBuilder.capacity();
  }

  public void ensureCapacity(int minimumCapacity) {
    stringBuilder.ensureCapacity(minimumCapacity);
  }

  // functionality dedicated methods

  public SourceStringBuilder declarePackage(String javaPackage) {
    append(K_PACKAGE).append(javaPackage);
    appendNewLines(2);
    return this;
  }

  public SourceStringBuilder declareClass(String className) {
    return append(K_CASE_CLASS).append(className).append("(").append("\n").
        appendInsertionPoint(className + "-params");
  }

  private SourceStringBuilder appendInsertionPoint(String insertionPointName) {
    return append("// @@protoc_insertion_point(").append(insertionPointName).append(")");
  }

  private SourceStringBuilder appendNewLines(Integer numberOfNewLines) {
    for (int i = 0; i < numberOfNewLines; i++) {
      appendNewLine();
    }
    return this;
  }
}
