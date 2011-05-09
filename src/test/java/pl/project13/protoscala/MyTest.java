package pl.project13.protoscala;

import junit.framework.Assert;
import org.testng.annotations.Test;

import java.util.Random;

/**
 * Date: 4/13/11
 *
 * @author Konrad Malawski
 */
public class MyTest {

  int it = 0;
  private Random random = new Random();

  @Test
  public void testIt() throws Exception {
    for (int i = 0; i < 100; i++) {
      doTest();
    }
  }

  private void doTest() {
    it = 0;
    Assert.assertEquals(0, it);

    stepUp();

    Assert.assertEquals(1, it);
  }

  void stepUp() {
    while (!step()) {
      step();
    }
  }

  boolean step() {
    boolean success = random.nextBoolean();
    if (success) {
      return true;
    } else {
      it--;
      return false;
    }
  }
}
