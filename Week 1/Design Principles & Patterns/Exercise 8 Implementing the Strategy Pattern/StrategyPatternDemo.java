interface PaymentStrategy {
    void pay(double amount);
    String getMethodName();
}

class CreditCardPayment implements PaymentStrategy {
    private final String cardHolderName;
    private final String cardNumber;
    private final String cvv;
    private final String expiryDate;

    public CreditCardPayment(String cardHolderName, String cardNumber, String cvv, String expiryDate) {
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
    }

    @Override
    public void pay(double amount) {
        String maskedCard = "**** **** **** " + cardNumber.substring(cardNumber.length() - 4);
        System.out.println("Paid $" + amount + " using Credit Card.");
        System.out.println("  Card Holder : " + cardHolderName);
        System.out.println("  Card Number : " + maskedCard);
        System.out.println("  Expiry      : " + expiryDate);
        System.out.println("  Status      : Payment Approved.");
    }

    @Override
    public String getMethodName() {
        return "Credit Card";
    }
}

class PayPalPayment implements PaymentStrategy {
    private final String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using PayPal.");
        System.out.println("  PayPal Account : " + email);
        System.out.println("  Status         : Payment Successful.");
    }

    @Override
    public String getMethodName() {
        return "PayPal";
    }
}

class UPIPayment implements PaymentStrategy {
    private final String upiId;

    public UPIPayment(String upiId) {
        this.upiId = upiId;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using UPI.");
        System.out.println("  UPI ID : " + upiId);
        System.out.println("  Status : Transaction Complete.");
    }

    @Override
    public String getMethodName() {
        return "UPI";
    }
}

class PaymentContext {
    private PaymentStrategy strategy;

    public PaymentContext(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void executePayment(double amount) {
        System.out.println("Initiating payment via " + strategy.getMethodName() + "...");
        strategy.pay(amount);
    }
}

public class StrategyPatternDemo {
    public static void main(String[] args) {
        System.out.println("=== Strategy Pattern Implementation Demo ===\n");

        PaymentContext context = new PaymentContext(
                new CreditCardPayment("Ashwani Arya", "4111111111111234", "123", "12/27")
        );

        System.out.println("--- Order 1: Laptop ($999.99) ---");
        context.executePayment(999.99);

        System.out.println("\n--- Order 2: Headphones ($149.50) - switching to PayPal ---");
        context.setStrategy(new PayPalPayment("ashwani@example.com"));
        context.executePayment(149.50);

        System.out.println("\n--- Order 3: Phone Case ($19.99) - switching to UPI ---");
        context.setStrategy(new UPIPayment("ashwani@upi"));
        context.executePayment(19.99);

        System.out.println("\n=============================================");
        System.out.println("SUCCESS: Strategy Pattern working correctly!");
        System.out.println("=============================================");
    }
}
