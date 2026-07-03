interface Notifier {
    void send(String message);
}

class EmailNotifier implements Notifier {
    private final String emailAddress;

    public EmailNotifier(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public void send(String message) {
        System.out.println("Email to " + emailAddress + ": " + message);
    }
}

abstract class NotifierDecorator implements Notifier {
    protected final Notifier wrapped;

    public NotifierDecorator(Notifier wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public void send(String message) {
        wrapped.send(message);
    }
}

class SMSNotifierDecorator extends NotifierDecorator {
    private final String phoneNumber;

    public SMSNotifierDecorator(Notifier wrapped, String phoneNumber) {
        super(wrapped);
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void send(String message) {
        super.send(message);
        System.out.println("SMS to " + phoneNumber + ": " + message);
    }
}

class SlackNotifierDecorator extends NotifierDecorator {
    private final String slackChannel;

    public SlackNotifierDecorator(Notifier wrapped, String slackChannel) {
        super(wrapped);
        this.slackChannel = slackChannel;
    }

    @Override
    public void send(String message) {
        super.send(message);
        System.out.println("Slack [" + slackChannel + "]: " + message);
    }
}

class WhatsAppNotifierDecorator extends NotifierDecorator {
    private final String whatsappNumber;

    public WhatsAppNotifierDecorator(Notifier wrapped, String whatsappNumber) {
        super(wrapped);
        this.whatsappNumber = whatsappNumber;
    }

    @Override
    public void send(String message) {
        super.send(message);
        System.out.println("WhatsApp to " + whatsappNumber + ": " + message);
    }
}

public class DecoratorPatternDemo {
    public static void main(String[] args) {
        System.out.println("=== Decorator Pattern Implementation Demo ===\n");

        Notifier emailOnly = new EmailNotifier("user@example.com");
        System.out.println("--- Email Only ---");
        emailOnly.send("Your order has been placed.");

        System.out.println("\n--- Email + SMS ---");
        Notifier emailAndSMS = new SMSNotifierDecorator(
                new EmailNotifier("user@example.com"),
                "+91-9876543210"
        );
        emailAndSMS.send("Your payment was successful.");

        System.out.println("\n--- Email + SMS + Slack ---");
        Notifier emailSMSSlack = new SlackNotifierDecorator(
                new SMSNotifierDecorator(
                        new EmailNotifier("user@example.com"),
                        "+91-9876543210"
                ),
                "#alerts"
        );
        emailSMSSlack.send("System outage detected.");

        System.out.println("\n--- Email + SMS + Slack + WhatsApp ---");
        Notifier allChannels = new WhatsAppNotifierDecorator(
                new SlackNotifierDecorator(
                        new SMSNotifierDecorator(
                                new EmailNotifier("user@example.com"),
                                "+91-9876543210"
                        ),
                        "#general"
                ),
                "+91-9876543210"
        );
        allChannels.send("Critical: Server is down!");

        System.out.println("\n=============================================");
        System.out.println("SUCCESS: Decorator Pattern working correctly!");
        System.out.println("=============================================");
    }
}
