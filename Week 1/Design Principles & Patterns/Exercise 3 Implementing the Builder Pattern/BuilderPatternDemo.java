public class BuilderPatternDemo {

    public static void main(String[] args) {
        System.out.println("=== Builder Pattern Implementation Demo ===\n");

        Computer gamingPC = new Computer.Builder("Intel Core i9-13900K", "32GB DDR5", "2TB NVMe SSD")
                .gpu("NVIDIA RTX 4090")
                .os("Windows 11 Pro")
                .bluetoothEnabled(true)
                .wifiEnabled(true)
                .build();

        Computer officePC = new Computer.Builder("Intel Core i5-12400", "16GB DDR4", "512GB SSD")
                .os("Windows 11 Home")
                .wifiEnabled(true)
                .build();

        Computer serverPC = new Computer.Builder("AMD EPYC 9654", "256GB DDR5", "10TB HDD RAID")
                .os("Ubuntu Server 22.04")
                .build();

        System.out.println("--- Gaming PC ---");
        System.out.println(gamingPC);

        System.out.println("\n--- Office PC ---");
        System.out.println(officePC);

        System.out.println("\n--- Server PC ---");
        System.out.println(serverPC);

        System.out.println("\n=============================================");
        System.out.println("SUCCESS: Builder Pattern working correctly!");
        System.out.println("=============================================");
    }
}
