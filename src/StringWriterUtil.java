import java.io.*;
import java.util.Arrays;
import java.util.Optional;

public class StringWriterUtil implements Closeable {

    private BufferedWriter bufferedWriter;
    private StringBuffer stringBuffer;

    private static final String REGEX_DUPLICATE = "(?i)\\b(\\w+)(\\b\\W+\\1\\b)+";

    public StringWriterUtil(String val) throws IOException {
        bufferedWriter = new BufferedWriter(new FileWriter("myfile.dat"));;
        stringBuffer = new StringBuffer(val);
    }


    public String lowerCase(){

        StringBuffer newStr=new StringBuffer(stringBuffer);
        for(int i = 0; i < stringBuffer.length(); i++) {

            //Checks for lower case character
            if(Character.isLowerCase(stringBuffer.charAt(i))) {
                //Convert it into upper case using toUpperCase() function
                newStr.setCharAt(i, Character.toUpperCase(stringBuffer.charAt(i)));
            }

        }

        this.stringBuffer = newStr;
        return newStr.toString();
    }

    public String upperCase(){

        StringBuffer newStr=new StringBuffer(stringBuffer);
        for(int i = 0; i < stringBuffer.length(); i++) {

            //Checks for upper case character
            if(Character.isUpperCase(stringBuffer.charAt(i))) {
                //Convert it into upper case using toLowerCase() function
                newStr.setCharAt(i, Character.toLowerCase(stringBuffer.charAt(i)));
            }
        }

        this.stringBuffer = newStr;
        return newStr.toString();
    }


    public String duplicateRemover(){
        this.stringBuffer  = Optional.ofNullable(stringBuffer)
                        .orElseGet(()-> new StringBuffer());

        String value = stringBuffer.toString()
                .replaceAll("(?i)\\b([a-z]+)\\b(?:\\s+\\1\\b)+", "$1");
        this.stringBuffer = new StringBuffer(value);
        return stringBuffer.toString();
    }



    public void removeStupid(){
       this.stringBuffer = new StringBuffer(this.stringBuffer.toString().replace("stupid","s*****"));
    }
    public void write(String value) throws IOException {
        bufferedWriter.write(value);
    }


    public String getValue(){
        return this.stringBuffer.toString();
    }
    @Override
    public void close() throws IOException {
       bufferedWriter.close();
    }


    public static void main(String[] args) throws IOException {
        StringWriterUtil test = new StringWriterUtil("This is really really stupid!!!");

        test.duplicateRemover();
        test.removeStupid();


        System.out.println(test.getValue());

    }
}
