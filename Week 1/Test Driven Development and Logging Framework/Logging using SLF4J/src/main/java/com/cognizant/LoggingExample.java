package com.cognizant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingExample {
    private static final Logger logger = LoggerFactory.getLogger(LoggingExample.class);

    public static void main(String[] args) {
        logger.error("This is an error message");
        logger.warn("This is a warning message");

        String username = "Ashwani";
        int loginAttempts = 3;
        logger.info("User {} failed to log in after {} attempts", username, loginAttempts);
        logger.debug("Debug information for user: {}", username);
    }
}
