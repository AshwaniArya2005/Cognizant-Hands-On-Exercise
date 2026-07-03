import java.util.Arrays;
import java.util.Comparator;

class Product {
    private int productId;
    private String productName;
    private String category;

    public Product(int productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }

    public int getProductId() { return productId; }
    public String getProductName() { return productName; }
    public String getCategory() { return category; }

    @Override
    public String toString() {
        return String.format("Product { ID: %3d, Name: %-22s, Category: %s }",
                productId, productName, category);
    }
}

class LinearSearch {
    public static Product searchById(Product[] products, int targetId) {
        for (Product p : products) {
            if (p.getProductId() == targetId) {
                return p;
            }
        }
        return null;
    }

    public static Product searchByName(Product[] products, String targetName) {
        for (Product p : products) {
            if (p.getProductName().equalsIgnoreCase(targetName)) {
                return p;
            }
        }
        return null;
    }
}

class BinarySearch {
    public static Product searchById(Product[] sortedProducts, int targetId) {
        int low = 0;
        int high = sortedProducts.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int midId = sortedProducts[mid].getProductId();

            if (midId == targetId) {
                return sortedProducts[mid];
            } else if (midId < targetId) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }
}

public class EcommercePlatform {
    private static void printResult(String label, Product result) {
        if (result != null) {
            System.out.println(label + "Found    : " + result);
        } else {
            System.out.println(label + "Not Found.");
        }
    }

    public static void main(String[] args) {
        System.out.println("=== E-commerce Platform Search Function ===\n");

        Product[] products = {
            new Product(104, "Laptop",            "Electronics"),
            new Product(201, "Wireless Mouse",    "Accessories"),
            new Product(315, "Running Shoes",     "Footwear"),
            new Product(422, "Coffee Maker",      "Appliances"),
            new Product(537, "Java Programming",  "Books"),
            new Product(618, "Yoga Mat",          "Fitness"),
            new Product(709, "Noise Cancelling Headphones", "Electronics"),
            new Product(812, "Desk Lamp",         "Home"),
        };

        Product[] sortedProducts = Arrays.copyOf(products, products.length);
        Arrays.sort(sortedProducts, Comparator.comparingInt(Product::getProductId));

        System.out.println("--- Unsorted Product Array ---");
        for (Product p : products) System.out.println("  " + p);

        System.out.println("\n--- Sorted by Product ID (for Binary Search) ---");
        for (Product p : sortedProducts) System.out.println("  " + p);

        System.out.println("\n--- Linear Search (works on unsorted array) ---");
        printResult("Search ID=315  -> ", LinearSearch.searchById(products, 315));
        printResult("Search ID=999  -> ", LinearSearch.searchById(products, 999));
        printResult("Search 'Yoga Mat' -> ", LinearSearch.searchByName(products, "Yoga Mat"));
        printResult("Search 'Tablet'   -> ", LinearSearch.searchByName(products, "Tablet"));

        System.out.println("\n--- Binary Search (requires sorted array) ---");
        printResult("Search ID=537  -> ", BinarySearch.searchById(sortedProducts, 537));
        printResult("Search ID=812  -> ", BinarySearch.searchById(sortedProducts, 812));
        printResult("Search ID=999  -> ", BinarySearch.searchById(sortedProducts, 999));

        System.out.println("\n=============================================");
        System.out.println("Time Complexity Analysis:");
        System.out.println();
        System.out.println("  Linear Search:");
        System.out.println("    Best Case    : O(1) - target is first element");
        System.out.println("    Average Case : O(n) - target is in the middle");
        System.out.println("    Worst Case   : O(n) - target is last or absent");
        System.out.println("    Works on     : Unsorted or sorted arrays");
        System.out.println();
        System.out.println("  Binary Search:");
        System.out.println("    Best Case    : O(1) - target is the mid element");
        System.out.println("    Average Case : O(log n) - halves search space each step");
        System.out.println("    Worst Case   : O(log n) - target absent or at boundary");
        System.out.println("    Requires     : Sorted array");
        System.out.println();
        System.out.println("  Recommendation:");
        System.out.println("    Use Binary Search for ID lookups (data sorted by ID).");
        System.out.println("    Use Linear Search for name/category (unsorted fields).");
        System.out.println("    For large catalogs, prefer HashMap for O(1) ID lookup.");
        System.out.println("=============================================");
    }
}
