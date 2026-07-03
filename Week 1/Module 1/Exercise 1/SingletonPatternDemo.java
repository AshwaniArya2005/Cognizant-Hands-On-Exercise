/**
 * Demo and verification class for the Singleton Pattern.
 */
public class SingletonPatternDemo {

    public static void main(String[] args) {
        System.out.println("=== Testing Singleton Pattern Implementation ===");

        // 1. Attempt to get the instance of Logger for the first time
        Logger logger1 = Logger.getInstance();
        logger1.log("First log message using logger1.");

        // 2. Attempt to get the instance of Logger for the second time
        Logger logger2 = Logger.getInstance();
        logger2.log("Second log message using logger2.");

        // 3. Verify that both references point to the exact same instance
        System.out.println("\n--- Verification ---");
        System.out.println("Logger 1 Hashcode: " + System.identityHashCode(logger1));
        System.out.println("Logger 2 Hashcode: " + System.identityHashCode(logger2));

        boolean areIdentical = (logger1 == logger2);
        System.out.println("Are logger1 and logger2 references pointing to the same instance? " + areIdentical);

        if (areIdentical) {
            System.out.println("SUCCESS: Singleton pattern is working correctly! Only one instance exists.");
        } else {
            System.out.println("FAILURE: Singleton pattern failed. Multiple instances found.");
        }

        // 4. Try to instantiate the Logger class directly (This will cause a compile-time error if uncommented)
        // Logger logger3 = new Logger();
    }
}
