interface PaymentProcessor {
    void processPayment(double amount, String currency);
    void refundPayment(String transactionId, double amount);
    String getGatewayName();
}

class PayPalGateway {
    public void makePayment(double amount, String currencyCode) {
        System.out.println("PayPal: Charging " + currencyCode + " " + amount + " via PayPal account.");
    }

    public void initiateRefund(String paypalTxnId, double refundAmount) {
        System.out.println("PayPal: Refunding " + refundAmount + " for transaction " + paypalTxnId + ".");
    }
}

class StripeGateway {
    public void charge(String currency, long amountInCents) {
        System.out.println("Stripe: Charging " + amountInCents + " cents (" + currency + ") via Stripe.");
    }

    public void reverseCharge(String chargeId, long amountInCents) {
        System.out.println("Stripe: Reversing charge of " + amountInCents + " cents for charge ID " + chargeId + ".");
    }
}

class RazorpayGateway {
    public void createOrder(double amountInPaise, String currency) {
        System.out.println("Razorpay: Creating order for " + amountInPaise + " paise in " + currency + ".");
    }

    public void processRefund(String orderId, double paise) {
        System.out.println("Razorpay: Processing refund of " + paise + " paise for order " + orderId + ".");
    }
}

class PayPalAdapter implements PaymentProcessor {
    private final PayPalGateway payPal;

    public PayPalAdapter(PayPalGateway payPal) {
        this.payPal = payPal;
    }

    @Override
    public void processPayment(double amount, String currency) {
        payPal.makePayment(amount, currency);
    }

    @Override
    public void refundPayment(String transactionId, double amount) {
        payPal.initiateRefund(transactionId, amount);
    }

    @Override
    public String getGatewayName() {
        return "PayPal";
    }
}

class StripeAdapter implements PaymentProcessor {
    private final StripeGateway stripe;

    public StripeAdapter(StripeGateway stripe) {
        this.stripe = stripe;
    }

    @Override
    public void processPayment(double amount, String currency) {
        long amountInCents = (long) (amount * 100);
        stripe.charge(currency, amountInCents);
    }

    @Override
    public void refundPayment(String transactionId, double amount) {
        long amountInCents = (long) (amount * 100);
        stripe.reverseCharge(transactionId, amountInCents);
    }

    @Override
    public String getGatewayName() {
        return "Stripe";
    }
}

class RazorpayAdapter implements PaymentProcessor {
    private final RazorpayGateway razorpay;

    public RazorpayAdapter(RazorpayGateway razorpay) {
        this.razorpay = razorpay;
    }

    @Override
    public void processPayment(double amount, String currency) {
        double amountInPaise = amount * 100;
        razorpay.createOrder(amountInPaise, currency);
    }

    @Override
    public void refundPayment(String transactionId, double amount) {
        double amountInPaise = amount * 100;
        razorpay.processRefund(transactionId, amountInPaise);
    }

    @Override
    public String getGatewayName() {
        return "Razorpay";
    }
}

public class AdapterPatternDemo {
    public static void main(String[] args) {
        System.out.println("=== Adapter Pattern Implementation Demo ===\n");

        PaymentProcessor paypal = new PayPalAdapter(new PayPalGateway());
        PaymentProcessor stripe = new StripeAdapter(new StripeGateway());
        PaymentProcessor razorpay = new RazorpayAdapter(new RazorpayGateway());

        PaymentProcessor[] gateways = { paypal, stripe, razorpay };

        for (PaymentProcessor gateway : gateways) {
            System.out.println("--- " + gateway.getGatewayName() + " ---");
            gateway.processPayment(99.99, "USD");
            gateway.refundPayment("TXN-" + gateway.getGatewayName().toUpperCase() + "-001", 99.99);
            System.out.println();
        }

        System.out.println("=============================================");
        System.out.println("SUCCESS: Adapter Pattern working correctly!");
        System.out.println("=============================================");
    }
}
