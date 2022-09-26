package service.impl;

import service.Operation;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OperationImpl implements Operation {

    static Logger logger = Logger.getLogger(OperationImpl.class.getName());


    private final static String DUPLICATE_REGEX = "\\b(\\w+)(?:\\W+\\1\\b)+";

    private final static String STUPID = "stupid";

    private final static String REPLACEMENT_STUPID = "s****";
    @Override
    public String toLowercase(String message) {
        logger.log(Level.INFO, "OperationsImpl :: toLowercase :: start here");
        if (null == message || message.isEmpty())
            return "";
        return message.toLowerCase();
    }

    @Override
    public String toUppercase(String message) {
        logger.log(Level.INFO, "OperationsImpl :: toUppercase :: start here");
        if (null == message || message.isEmpty())
            return "";
        return message.toUpperCase();
    }

    @Override
    public String stupidRemover(String message) {
        logger.log(Level.INFO, "OperationsImpl :: stupidRemover :: start here");
        if (null == message || message.isEmpty())
            return "";
        return message.replace(STUPID, REPLACEMENT_STUPID);
    }

    @Override
    public String duplicateRemover(String message) {
        logger.log(Level.INFO, "OperationsImpl :: duplicateRemover :: start here");
        if (null == message || message.isEmpty())
            return "";

        Pattern pattern = Pattern.compile(DUPLICATE_REGEX, Pattern.CASE_INSENSITIVE);
        Matcher match = pattern.matcher(message);

        while (match.find()) {
            message = message.replace(match.group(), match.group(1));
        }
        return message;
    }

}