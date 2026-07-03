public class Computer {

    private final String cpu;
    private final String ram;
    private final String storage;
    private final String gpu;
    private final String os;
    private final boolean bluetoothEnabled;
    private final boolean wifiEnabled;

    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
        this.gpu = builder.gpu;
        this.os = builder.os;
        this.bluetoothEnabled = builder.bluetoothEnabled;
        this.wifiEnabled = builder.wifiEnabled;
    }

    public String getCpu() { return cpu; }
    public String getRam() { return ram; }
    public String getStorage() { return storage; }
    public String getGpu() { return gpu; }
    public String getOs() { return os; }
    public boolean isBluetoothEnabled() { return bluetoothEnabled; }
    public boolean isWifiEnabled() { return wifiEnabled; }

    @Override
    public String toString() {
        return "Computer {" +
                "\n  CPU              : " + cpu +
                "\n  RAM              : " + ram +
                "\n  Storage          : " + storage +
                "\n  GPU              : " + (gpu != null ? gpu : "Integrated") +
                "\n  OS               : " + (os != null ? os : "None") +
                "\n  Bluetooth        : " + (bluetoothEnabled ? "Yes" : "No") +
                "\n  Wi-Fi            : " + (wifiEnabled ? "Yes" : "No") +
                "\n}";
    }

    public static class Builder {

        private final String cpu;
        private final String ram;
        private final String storage;
        private String gpu;
        private String os;
        private boolean bluetoothEnabled;
        private boolean wifiEnabled;

        public Builder(String cpu, String ram, String storage) {
            this.cpu = cpu;
            this.ram = ram;
            this.storage = storage;
        }

        public Builder gpu(String gpu) {
            this.gpu = gpu;
            return this;
        }

        public Builder os(String os) {
            this.os = os;
            return this;
        }

        public Builder bluetoothEnabled(boolean bluetoothEnabled) {
            this.bluetoothEnabled = bluetoothEnabled;
            return this;
        }

        public Builder wifiEnabled(boolean wifiEnabled) {
            this.wifiEnabled = wifiEnabled;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }
}
