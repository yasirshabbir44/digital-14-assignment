import service.Operation;
import service.Writer;
import service.impl.OperationImpl;
import service.impl.WriterFactory;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {


    static Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String... args) {
        logger.log(Level.INFO, "Main App :: Starting");
        Main.build();
        logger.log(Level.INFO, "Main App :: Ending");
    }

    public static void build() {
        Scanner scanner = new Scanner(System.in);
        StringBuilder result = new StringBuilder();
        String operations = "";
        String sentence;
        try {

            System.out.println("Please select Writer method:: ");
            System.out.println("Currently supported only Writer Names: StringWriter, FileWriter");

            WriterFactory writerFactory = new WriterFactory(scanner.nextLine());
            Writer writer = writerFactory.getWriter();

            if (null == writer) {
                System.out.println("Unknown writer. Kindly try again.");
                System.exit(0);
            }

            while (true) {
                System.out.println("Please enter the Message :: ");
                result.append(scanner.nextLine());
                result.append(" ");
                System.out.print("Do you want to continue.Yes/No ::  ");
                sentence = scanner.nextLine();

                if (writer.close(sentence)) {
                    System.out.println("Select Operations by comma seperated :");
                    System.out.println(
                            "Currently supported Writer Names: Lowercase, Uppercase, StupidRemover, DuplicateRemover");
                    operations = scanner.nextLine();
                    break;
                }

            }

            writer.write(applyOperations(operations, result.toString()));
            logger.log(Level.INFO, "DigitalApp :: build() :: ends here");

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception occured in DigitalApp :: build() :: " + e.getMessage());
        }
    }

    private static StringBuilder applyOperations(String operations, String result) {
        logger.log(Level.INFO, "DigitalApp :: applyOperations() :: starts here");
        String[] opsList = operations.split(",");
        String isOperationdone = "";
        String opsResult = result;
        if (opsList.length > 0) {
            for (String opName : opsList) {
                isOperationdone = getOperations(opName, opsResult);
                if (!isOperationdone.equalsIgnoreCase("Unknown Operation")) {
                    opsResult = isOperationdone;
                }
            }
        }
        logger.log(Level.INFO, "DigitalApp :: applyOperations() :: ends here");
        return new StringBuilder(opsResult);
    }

    private static String getOperations(String opsName, String message) {
        logger.log(Level.INFO, "DigitalApp :: getOperations() :: starts here");
        Operation opsImpl = new OperationImpl();
        switch (opsName.toLowerCase()) {
            case "lowercase":
                return opsImpl.toLowercase(message);
            case "uppercase":
                return opsImpl.toUppercase(message);
            case "stupidremover":
                return opsImpl.stupidRemover(message);
            case "duplicateremover":
                return opsImpl.duplicateRemover(message);
            default:
                return "Unknown Operation";
        }
    }

}
