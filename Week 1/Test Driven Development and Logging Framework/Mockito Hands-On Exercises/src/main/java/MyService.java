public class MyService {
    private final ExternalApi api;

    public MyService(ExternalApi api) {
        this.api = api;
    }

    public String fetchData() {
        return api.getData();
    }

    public String fetchDataWithArg(String param) {
        return api.getDataWithArg(param);
    }

    public void processAndSave(String data) {
        api.saveData(data);
    }

    public void executeAction() {
        api.performAction();
    }
}
