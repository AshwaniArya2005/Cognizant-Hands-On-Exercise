import java.util.Arrays;

class Order {
    private int orderId;
    private String customerName;
    private double totalPrice;

    public Order(int orderId, String customerName, double totalPrice) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
    }

    public int getOrderId() { return orderId; }
    public String getCustomerName() { return customerName; }
    public double getTotalPrice() { return totalPrice; }

    @Override
    public String toString() {
        return String.format("Order { ID: %3d, Customer: %-18s, Total: $%8.2f }",
                orderId, customerName, totalPrice);
    }
}

class BubbleSort {
    public static void sort(Order[] orders) {
        int n = orders.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (orders[j].getTotalPrice() > orders[j + 1].getTotalPrice()) {
                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }
}

class QuickSort {
    public static void sort(Order[] orders, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(orders, low, high);
            sort(orders, low, pivotIndex - 1);
            sort(orders, pivotIndex + 1, high);
        }
    }

    private static int partition(Order[] orders, int low, int high) {
        double pivot = orders[high].getTotalPrice();
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (orders[j].getTotalPrice() <= pivot) {
                i++;
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }

        Order temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;

        return i + 1;
    }
}

public class SortingOrders {
    private static void printOrders(Order[] orders) {
        for (Order o : orders) System.out.println("  " + o);
    }

    public static void main(String[] args) {
        System.out.println("=== Sorting Customer Orders ===");

        Order[] original = {
            new Order(1001, "Ashwani Arya",  1299.99),
            new Order(1002, "Priya Mehta",    249.50),
            new Order(1003, "Rahul Sharma",  3750.00),
            new Order(1004, "Anita Singh",     89.99),
            new Order(1005, "Vikram Nair",   2100.00),
        };

        Order[] bubbleArr = Arrays.copyOf(original, original.length);
        Order[] quickArr  = Arrays.copyOf(original, original.length);

        long bubbleStart = System.nanoTime();
        BubbleSort.sort(bubbleArr);
        long bubbleEnd = System.nanoTime();

        long quickStart = System.nanoTime();
        QuickSort.sort(quickArr, 0, quickArr.length - 1);
        long quickEnd = System.nanoTime();

        System.out.println("\nBubble Sort result (" + (bubbleEnd - bubbleStart) + " ns):");
        printOrders(bubbleArr);

        System.out.println("\nQuick Sort result (" + (quickEnd - quickStart) + " ns):");
        printOrders(quickArr);

        System.out.println("\nComplexity: Bubble=O(n^2), QuickSort=O(n log n) avg.");
        System.out.println("Quick Sort preferred: faster avg, cache-friendly, scales better.");
    }
}
