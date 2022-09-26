package service.impl;

import service.Writer;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileWriter implements Writer {

    static Logger logger = Logger.getLogger(FileWriter.class.getName());

    @Override
    public void write(StringBuilder message) {
        logger.log(Level.INFO, "FileWriter :: write :: start here");
        PrintWriter out;
        try {
            out = new PrintWriter("filename.txt");
            out.println(message);
            out.close();
            System.out.println("Final Message :: " + message);
            logger.log(Level.INFO, "FileWriter :: write :: ends here");
        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, "Exception occured in FileWriter :: write ");
        }

    }

    @Override
    public boolean close(String isClose) {
        logger.log(Level.INFO, "FileWriter :: close :: start here" + isClose);
        if (isClose !=null && (isClose.equalsIgnoreCase("no") || isClose.equalsIgnoreCase("n"))) {
            return true;
        }
        logger.log(Level.INFO, "FileWriter :: close :: ends here");
        return false;
    }

}
