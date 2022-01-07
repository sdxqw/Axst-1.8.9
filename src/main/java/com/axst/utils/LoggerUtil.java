package com.axst.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtil {

    public static final Logger LOGGER = LogManager.getLogger();

    public static void info (Object msg, Object... params)
    {
        LOGGER.info(String.valueOf(msg), params);
    }
    public static void error (Object msg, Object... params)
    {
        LOGGER.error(String.valueOf(msg), params);
    }
}
