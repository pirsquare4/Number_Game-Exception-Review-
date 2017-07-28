import java.util.Random;
import java.io.IOException;


public class ExceptionHandling {

  static int ASCII_READABLE_RANGE_DECIMAL_START = 33;
  static int ASCII_READABLE_RANGE_DECIMAL_END = 126;
  static int MAXIMUM_SENSIBLE_INPUTS = 10;
  static boolean DEBUG = true;

  public static void main(String[] args) {
    Random rand = new Random();

    char testChar = Character.MIN_VALUE;
    char typedChar = Character.MIN_VALUE;

    int inputCount = 0;

    while (true) {

      char nextChar = (char) (rand.nextInt(ASCII_READABLE_RANGE_DECIMAL_END - ASCII_READABLE_RANGE_DECIMAL_START)
      + ASCII_READABLE_RANGE_DECIMAL_START);

      logMessage("out","Enter Character: " + nextChar + " (" + (int) nextChar + ")", true);


      try {


        while (System.in.available() <= 0);
        testChar = (char) System.in.read();
        inputCount++;


        if (inputCount > MAXIMUM_SENSIBLE_INPUTS)
          throw new PracticeExcessiveInputException("ლ(ಠ益ಠლ) Y U NO GET A LIFE???");

        if (testChar == Character.MIN_VALUE)
          throw new PracticeNullInputException("Empty Character Read");
        if ((int) testChar == 10)
          throw new PracticeBackspaceInputException("Backspace Character Read");
        logMessage("out","Got Character: " + testChar, true);
        logMessage("out","ASCII Value: " + (int) testChar, true);

        typedChar = testChar;

        if (typedChar != nextChar)
          throw new PracticeMismatchInputException("Wrong Character Read");
        logMessage("out","ʚ♡⃛ɞ(ू•ᴗ•ू❁)  SUCCESS", true);
      }
      catch (IOException ioe) {
        logMessage("err","Caught IOException");
        logMessage("err", ioe.toString());
      }
      catch (PracticeExcessiveInputException pee) {
        logMessage("err","Caught PracticeExcessiveInputException");
        logMessage("err",pee.getMessage(), true);
        logMessage("err",pee.toString());
        System.exit(-1);
      }
      catch (PracticeMismatchInputException pme) {
        logMessage("err","Caught PracticeMismatchInputException");
        logMessage("err",pme.getMessage(), true);
        logMessage("err",pme.toString());
      }
      catch (PracticeInputException pie) {
        logMessage("err","Caught PracticeInputException");
        logMessage("err",pie.getMessage());
        logMessage("err",pie.toString());
      }
      typedChar = Character.MIN_VALUE;
    }

  }
  public static void logMessage(String methodType, String message) {
    logMessage(methodType, message, false);

  }
  public static void logMessage(String methodType, String message, boolean force) {
    if (false == DEBUG && !force ){
      return;
    }
    if (methodType == "err"){
      System.err.println(message);
    }
    else {
      System.out.println(message);
    }

  }


}
