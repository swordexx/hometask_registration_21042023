package main.java.framework.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
    static {
        String log4jpath = System.getProperty("user.dir") + "/src/maing/resources/log4j.xml";
        System.setProperty("logoutputpath", System.getProperty("user.dir"));
        System.setProperty("log4j.cinfigurationFile", log4jpath);
    }

    public static Logger Log = LogManager.getLogger(Log.class.getName());
}