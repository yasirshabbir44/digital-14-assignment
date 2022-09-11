import java.io.*;
import java.util.Arrays;
import java.util.Optional;

public class StringWriterUtil implements Closeable {

    private BufferedWriter bufferedWriter;
    private StringBuffer stringBuffer;

    private static final String REGEX_DUPLICATE = "(?i)\\b(\\w+)(\\b\\W+\\1\\b)+";


    /**
    * Constructor that initialize instance member of this class
    * */
    public StringWriterUtil(String val) throws IOException {
        bufferedWriter = new BufferedWriter(new FileWriter("myfile.dat"));
        stringBuffer = new StringBuffer(val);
    }


    /**
    * Utility method that convert String into Lower case rather than using Built-in method of java
    * */
    public String lowerCase() {

        StringBuffer newStr = new StringBuffer(stringBuffer);
        for (int i = 0; i < stringBuffer.length(); i++) {

            //Checks for lower case character
            if (Character.isLowerCase(stringBuffer.charAt(i))) {
                //Convert it into upper case using toUpperCase() function
                newStr.setCharAt(i, Character.toUpperCase(stringBuffer.charAt(i)));
            }

        }

        this.stringBuffer = newStr;
        return newStr.toString();
    }


    /**
     * Utility method that convert String into Upper case rather than using Built-in method of java
     *
     * */
    public String upperCase() {

        StringBuffer newStr = new StringBuffer(stringBuffer);
        for (int i = 0; i < stringBuffer.length(); i++) {

            //Checks for upper case character
            if (Character.isUpperCase(stringBuffer.charAt(i))) {
                //Convert it into upper case using toLowerCase() function
                newStr.setCharAt(i, Character.toLowerCase(stringBuffer.charAt(i)));
            }
        }

        this.stringBuffer = newStr;
        return newStr.toString();
    }


    /**
    * This Utility method removes consecutive duplicated words.
    * */
    public String duplicateRemover() {
        this.stringBuffer = Optional.ofNullable(stringBuffer)
                .orElseGet(() -> new StringBuffer());

        String value = stringBuffer.toString().replaceAll(REGEX_DUPLICATE, "$1");
        this.stringBuffer = new StringBuffer(value);
        return stringBuffer.toString();
    }


    /**
    * This replaces the word stupid (only in lower case) to s*****
    * */
    public void removeStupid() {
        this.stringBuffer = new StringBuffer(this.stringBuffer.toString().replace("stupid", "s*****"));
    }


    /**
    * This method will write into file.
    * */
    public void write(String value) throws IOException {
        bufferedWriter.write(value);
    }


    /**
    *
    * Method will return the current value of StringBuffer holding it.
    * */
    public String getValue() {
        return this.stringBuffer.toString();
    }

    /**
     * This Autocloseable feature of Java 8 override the method from interface Closeable.
     * */
    @Override
    public void close() throws IOException {
        bufferedWriter.close();
    }


    public static void main(String[] args) throws IOException {
        StringWriterUtil test = new StringWriterUtil("This is really really stupid!!!");

        test.duplicateRemover();
        test.removeStupid();

        test.write(test.getValue());
        test.close();
        System.out.println(test.getValue());

    }
}
