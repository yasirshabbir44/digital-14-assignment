package service.impl;

import service.Writer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class WriterFactory {

    static Logger logger = Logger.getLogger(WriterFactory.class.getName());

    private String writerName;

    public WriterFactory(String writerName) {
        logger.log(Level.INFO, "WriterFactory :: constructor :: start here" + writerName);
        this.writerName = writerName;
        logger.log(Level.INFO, "WriterFactory :: constructor :: ends here");
    }

    public Writer getWriter() {
        logger.log(Level.INFO, "WriterFactory :: getWriter :: start here");
        if (null == writerName || writerName.isEmpty())
            return null;
        switch (writerName.toLowerCase()) {
            case "stringwriter":
                return new StringWriter();
            case "filewriter":
                return new FileWriter();
            default:
                return null;
        }
    }

}
