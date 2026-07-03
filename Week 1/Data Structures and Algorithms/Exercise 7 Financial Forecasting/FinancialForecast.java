import java.util.HashMap;
import java.util.Map;

public class FinancialForecast {

    public static double forecastRecursive(double presentValue, double growthRate, int years) {
        if (years == 0) return presentValue;
        return forecastRecursive(presentValue, growthRate, years - 1) * (1 + growthRate);
    }

    private static Map<Integer, Double> memo = new HashMap<>();

    public static double forecastMemo(double presentValue, double growthRate, int years) {
        if (years == 0) return presentValue;
        if (memo.containsKey(years)) return memo.get(years);
        double result = forecastMemo(presentValue, growthRate, years - 1) * (1 + growthRate);
        memo.put(years, result);
        return result;
    }

    public static double forecastIterative(double presentValue, double growthRate, int years) {
        double value = presentValue;
        for (int i = 0; i < years; i++) value *= (1 + growthRate);
        return value;
    }

    public static void main(String[] args) {
        System.out.println("=== Financial Forecasting Tool ===");

        double presentValue = 100000;
        double growthRate   = 0.08;

        System.out.printf("%nPresent Value : $%.2f%n", presentValue);
        System.out.printf("Growth Rate   : %.0f%%%n%n", growthRate * 100);
        System.out.printf("%-6s  %-18s  %-18s  %-18s%n", "Years", "Recursive", "Memoized", "Iterative");
        System.out.println("-".repeat(66));

        for (int y : new int[]{1, 3, 5, 10, 20}) {
            memo.clear();
            double r = forecastRecursive(presentValue, growthRate, y);
            double m = forecastMemo(presentValue, growthRate, y);
            double i = forecastIterative(presentValue, growthRate, y);
            System.out.printf("%-6d  $%-17.2f  $%-17.2f  $%-17.2f%n", y, r, m, i);
        }

        System.out.println("\nComplexity: Recursive=O(n), Memoized=O(n) with O(n) space, Iterative=O(n) O(1) space.");
        System.out.println("Memoization eliminates repeated subproblems; iterative is most efficient.");
    }
}
