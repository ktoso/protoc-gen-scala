package pl.project13.protoscala;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Date: 4/9/11
 *
 * @author Konrad Malawski
 */
public class MainRunnerTest {

  MainRunner main = new MainRunner();

  String input = "";

  InputStream inputStream;
  ByteArrayOutputStream outputStream;

  @Before
  public void setUp() throws Exception {
    inputStream = new ByteArrayInputStream(input.getBytes("UTF-8"));
    outputStream = new ByteArrayOutputStream();

    main.setIn(inputStream);

    main.setOut(outputStream);
  }

  @Test
  public void testRun() throws Exception {
    main.run();

    String output = outputStream.toString("UTF-8");

    System.out.println(output);

    assertThat(output).contains("case class Person");
  }
}
