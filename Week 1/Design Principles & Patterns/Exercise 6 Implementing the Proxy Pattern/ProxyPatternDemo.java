import java.util.HashMap;
import java.util.Map;

interface Image {
    void display();
    String getFileName();
}

class RealImage implements Image {
    private final String fileName;
    private byte[] imageData;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromServer();
    }

    private void loadFromServer() {
        System.out.println("Loading \"" + fileName + "\" from remote server...");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        imageData = new byte[1024];
        System.out.println("\"" + fileName + "\" loaded successfully (" + imageData.length + " bytes).");
    }

    @Override
    public void display() {
        System.out.println("Displaying \"" + fileName + "\".");
    }

    @Override
    public String getFileName() {
        return fileName;
    }
}

class ProxyImage implements Image {
    private final String fileName;
    private RealImage realImage;
    private static final Map<String, RealImage> cache = new HashMap<>();

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if (cache.containsKey(fileName)) {
            System.out.println("Cache hit — reusing \"" + fileName + "\" from cache.");
            realImage = cache.get(fileName);
        } else {
            realImage = new RealImage(fileName);
            cache.put(fileName, realImage);
        }
        realImage.display();
    }

    @Override
    public String getFileName() {
        return fileName;
    }
}

public class ProxyPatternDemo {
    public static void main(String[] args) {
        System.out.println("=== Proxy Pattern Implementation Demo ===\n");

        Image img1 = new ProxyImage("nature.jpg");
        Image img2 = new ProxyImage("cityscape.png");
        Image img3 = new ProxyImage("nature.jpg");
        Image img4 = new ProxyImage("portrait.jpg");
        Image img5 = new ProxyImage("cityscape.png");

        System.out.println("--- First access (loads from server) ---");
        img1.display();

        System.out.println("\n--- First access (loads from server) ---");
        img2.display();

        System.out.println("\n--- Second access of nature.jpg (served from cache) ---");
        img3.display();

        System.out.println("\n--- First access (loads from server) ---");
        img4.display();

        System.out.println("\n--- Second access of cityscape.png (served from cache) ---");
        img5.display();

        System.out.println("\n=============================================");
        System.out.println("SUCCESS: Proxy Pattern working correctly!");
        System.out.println("=============================================");
    }
}
