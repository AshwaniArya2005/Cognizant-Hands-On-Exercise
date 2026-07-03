import java.util.ArrayList;
import java.util.List;

interface Observer {
    void update(String stockSymbol, double oldPrice, double newPrice);
}

interface Stock {
    void registerObserver(Observer observer);
    void deregisterObserver(Observer observer);
    void notifyObservers(String stockSymbol, double oldPrice, double newPrice);
}

class StockMarket implements Stock {
    private final List<Observer> observers = new ArrayList<>();
    private String stockSymbol;
    private double price;

    public StockMarket(String stockSymbol, double initialPrice) {
        this.stockSymbol = stockSymbol;
        this.price = initialPrice;
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
        System.out.println(observer.getClass().getSimpleName() + " registered for " + stockSymbol + ".");
    }

    @Override
    public void deregisterObserver(Observer observer) {
        observers.remove(observer);
        System.out.println(observer.getClass().getSimpleName() + " deregistered from " + stockSymbol + ".");
    }

    @Override
    public void notifyObservers(String stockSymbol, double oldPrice, double newPrice) {
        for (Observer observer : observers) {
            observer.update(stockSymbol, oldPrice, newPrice);
        }
    }

    public void setPrice(double newPrice) {
        double oldPrice = this.price;
        this.price = newPrice;
        System.out.println("\n[Market] " + stockSymbol + " price changed: $" + oldPrice + " -> $" + newPrice);
        notifyObservers(stockSymbol, oldPrice, newPrice);
    }

    public double getPrice() {
        return price;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }
}

class MobileApp implements Observer {
    private final String appName;

    public MobileApp(String appName) {
        this.appName = appName;
    }

    @Override
    public void update(String stockSymbol, double oldPrice, double newPrice) {
        String direction = newPrice > oldPrice ? "UP" : "DOWN";
        System.out.println("  [MobileApp - " + appName + "] ALERT: " + stockSymbol +
                " is " + direction + " to $" + newPrice);
    }
}

class WebApp implements Observer {
    private final String siteName;

    public WebApp(String siteName) {
        this.siteName = siteName;
    }

    @Override
    public void update(String stockSymbol, double oldPrice, double newPrice) {
        double change = newPrice - oldPrice;
        double changePct = (change / oldPrice) * 100;
        System.out.printf("  [WebApp - %s] %s updated: $%.2f (%.2f%%)%n",
                siteName, stockSymbol, newPrice, changePct);
    }
}

public class ObserverPatternDemo {
    public static void main(String[] args) {
        System.out.println("=== Observer Pattern Implementation Demo ===\n");

        StockMarket apple = new StockMarket("AAPL", 189.50);
        StockMarket tesla = new StockMarket("TSLA", 245.00);

        MobileApp robinhood = new MobileApp("Robinhood");
        MobileApp groww = new MobileApp("Groww");
        WebApp yahoo = new WebApp("Yahoo Finance");
        WebApp bloomberg = new WebApp("Bloomberg");

        System.out.println("--- Registering Observers ---");
        apple.registerObserver(robinhood);
        apple.registerObserver(yahoo);
        apple.registerObserver(bloomberg);

        tesla.registerObserver(groww);
        tesla.registerObserver(yahoo);

        System.out.println("\n--- Price Updates ---");
        apple.setPrice(192.75);
        apple.setPrice(188.10);

        tesla.setPrice(261.30);

        System.out.println("\n--- Deregistering Robinhood from AAPL ---");
        apple.deregisterObserver(robinhood);

        apple.setPrice(195.00);

        System.out.println("\n=============================================");
        System.out.println("SUCCESS: Observer Pattern working correctly!");
        System.out.println("=============================================");
    }
}
