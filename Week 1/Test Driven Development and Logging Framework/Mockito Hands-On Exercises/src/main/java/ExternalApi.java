public interface ExternalApi {
    String getData();
    String getDataWithArg(String param);
    void saveData(String data);
    void performAction();
}
