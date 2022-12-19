package com.chenyo.permission.logrecord;

import java.io.IOException;
import java.util.logging.*;

public class LogRecord {
    public Logger logger;

    public void setLogger(Logger logger){
        if(logger != null){
            this.logger = logger;
        }else{
            this.logger = Logger.getLogger("default");
        }
    }

    public void LogRecorder(){
        setLogger(logger);
        logger.setLevel(Level.INFO);

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.OFF);

        logger.addHandler(consoleHandler);

        try {
            FileHandler fileHandler = new FileHandler("D:\\Project\\log\\terminal_record.txt", true);
            fileHandler.setFormatter(new LogFormat());
            fileHandler.setLevel(Level.ALL);
            logger.addHandler(fileHandler);

        } catch (SecurityException | IOException e) {

            e.printStackTrace();
        }
    }

    public void setINFO(String info){
        logger.info(info);
    }

    public void setWarning(String warning){
        logger.warning(warning);
    }

    public void setError(String error){
        logger.severe(error);
    }

}
