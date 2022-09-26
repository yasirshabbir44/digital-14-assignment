package service.impl;

import service.Writer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class StringWriter implements Writer {

    static Logger logger = Logger.getLogger(StringWriter.class.getName());

    @Override
    public void write(StringBuilder message) {
        logger.log(Level.INFO, "StringWriter :: write :: start here");
        System.out.println("Final Message :: " +message);
        logger.log(Level.INFO, "StringWriter :: write :: end here");
    }

    @Override
    public boolean close(String isClose) {
        logger.log(Level.INFO, "StringWriter :: close :: start here" + isClose);
        if(isClose !=null && (isClose.equalsIgnoreCase("no") || isClose.equalsIgnoreCase("n"))) {
            return true;
        }
        logger.log(Level.INFO, "StringWriter :: close :: ends here");
        return false;
    }

}
