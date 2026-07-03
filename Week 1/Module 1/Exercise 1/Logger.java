/**
 * Logger class implementing the Singleton pattern.
 * This class ensures that only one instance of the logger exists throughout the application.
 */
public class Logger {

    // Private static instance of the class, declared volatile to ensure visibility across threads
    private static volatile Logger instance;

    /**
     * Private constructor to prevent instantiation from other classes.
     */
    private Logger() {
        // Guard against reflection instantiation
        if (instance != null) {
            throw new IllegalStateException("Instance already created. Use getInstance() method.");
        }
    }

    /**
     * Public static method to provide global access to the singleton instance.
     * Uses double-checked locking for thread safety and performance.
     *
     * @return the single instance of Logger
     */
    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    /**
     * A simple log method to output messages to the console.
     *
     * @param message The message to log
     */
    public void log(String message) {
        System.out.println("[LOG] " + java.time.LocalDateTime.now() + " - " + message);
    }
}
