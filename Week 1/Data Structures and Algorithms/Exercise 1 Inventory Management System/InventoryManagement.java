import java.util.HashMap;
import java.util.Map;
import java.util.Collection;

class Product {
    private int productId;
    private String productName;
    private int quantity;
    private double price;

    public Product(int productId, String productName, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public int getProductId() { return productId; }
    public String getProductName() { return productName; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }

    public void setProductName(String productName) { this.productName = productName; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return String.format("Product { ID: %d, Name: %-20s, Qty: %3d, Price: $%.2f }",
                productId, productName, quantity, price);
    }
}

class Inventory {
    private final Map<Integer, Product> store = new HashMap<>();

    public void addProduct(Product product) {
        if (store.containsKey(product.getProductId())) {
            System.out.println("Product ID " + product.getProductId() + " already exists. Use update instead.");
            return;
        }
        store.put(product.getProductId(), product);
        System.out.println("Added: " + product);
    }

    public void updateProduct(int productId, String newName, int newQuantity, double newPrice) {
        Product product = store.get(productId);
        if (product == null) {
            System.out.println("Product ID " + productId + " not found.");
            return;
        }
        product.setProductName(newName);
        product.setQuantity(newQuantity);
        product.setPrice(newPrice);
        System.out.println("Updated: " + product);
    }

    public void deleteProduct(int productId) {
        Product removed = store.remove(productId);
        if (removed == null) {
            System.out.println("Product ID " + productId + " not found.");
        } else {
            System.out.println("Deleted: " + removed);
        }
    }

    public void getProduct(int productId) {
        Product product = store.get(productId);
        if (product == null) {
            System.out.println("Product ID " + productId + " not found.");
        } else {
            System.out.println("Found  : " + product);
        }
    }

    public void displayAll() {
        if (store.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }
        Collection<Product> products = store.values();
        products.stream()
                .sorted((a, b) -> Integer.compare(a.getProductId(), b.getProductId()))
                .forEach(System.out::println);
    }

    public int size() {
        return store.size();
    }
}

public class InventoryManagement {
    public static void main(String[] args) {
        System.out.println("=== Inventory Management System ===\n");

        Inventory inventory = new Inventory();

        System.out.println("--- Adding Products (O(1) avg) ---");
        inventory.addProduct(new Product(101, "Laptop",        50,  899.99));
        inventory.addProduct(new Product(102, "Wireless Mouse", 200,  29.99));
        inventory.addProduct(new Product(103, "Mechanical Keyboard", 150, 79.99));
        inventory.addProduct(new Product(104, "Monitor 27\"",   75,  349.99));
        inventory.addProduct(new Product(105, "USB-C Hub",     300,  49.99));

        System.out.println("\n--- Full Inventory ---");
        inventory.displayAll();

        System.out.println("\n--- Lookup by ID (O(1) avg) ---");
        inventory.getProduct(103);
        inventory.getProduct(999);

        System.out.println("\n--- Updating a Product (O(1) avg) ---");
        inventory.updateProduct(101, "Laptop Pro", 45, 1099.99);

        System.out.println("\n--- Deleting a Product (O(1) avg) ---");
        inventory.deleteProduct(105);
        inventory.deleteProduct(999);

        System.out.println("\n--- Inventory after operations (" + inventory.size() + " items) ---");
        inventory.displayAll();

        System.out.println("\n--- Duplicate Add Prevention ---");
        inventory.addProduct(new Product(101, "Duplicate Laptop", 10, 500.00));

        System.out.println("\n=============================================");
        System.out.println("Time Complexity Summary (HashMap):");
        System.out.println("  Add    : O(1) average");
        System.out.println("  Update : O(1) average");
        System.out.println("  Delete : O(1) average");
        System.out.println("  Lookup : O(1) average");
        System.out.println("  O(n) worst case due to hash collisions.");
        System.out.println("=============================================");
    }
}
