import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class Customer {
    private final String id;
    private final String name;
    private final String email;

    public Customer(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return "Customer { ID: " + id + ", Name: " + name + ", Email: " + email + " }";
    }
}

interface CustomerRepository {
    Optional<Customer> findCustomerById(String id);
    void save(Customer customer);
    void deleteById(String id);
}

class CustomerRepositoryImpl implements CustomerRepository {
    private final Map<String, Customer> store = new HashMap<>();

    public CustomerRepositoryImpl() {
        store.put("C001", new Customer("C001", "Ashwani Arya", "ashwani@example.com"));
        store.put("C002", new Customer("C002", "Priya Mehta", "priya@example.com"));
        store.put("C003", new Customer("C003", "Rahul Sharma", "rahul@example.com"));
    }

    @Override
    public Optional<Customer> findCustomerById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public void save(Customer customer) {
        store.put(customer.getId(), customer);
        System.out.println("Saved: " + customer);
    }

    @Override
    public void deleteById(String id) {
        if (store.remove(id) != null) {
            System.out.println("Deleted customer with ID: " + id);
        } else {
            System.out.println("Customer ID " + id + " not found.");
        }
    }
}

class CustomerService {
    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public void getCustomer(String id) {
        Optional<Customer> customer = repository.findCustomerById(id);
        if (customer.isPresent()) {
            System.out.println("Found: " + customer.get());
        } else {
            System.out.println("Customer with ID \"" + id + "\" not found.");
        }
    }

    public void addCustomer(String id, String name, String email) {
        repository.save(new Customer(id, name, email));
    }

    public void removeCustomer(String id) {
        repository.deleteById(id);
    }
}

public class DependencyInjectionDemo {
    public static void main(String[] args) {
        System.out.println("=== Dependency Injection Implementation Demo ===\n");

        CustomerRepository repository = new CustomerRepositoryImpl();
        CustomerService service = new CustomerService(repository);

        System.out.println("--- Finding existing customers ---");
        service.getCustomer("C001");
        service.getCustomer("C002");

        System.out.println("\n--- Finding non-existent customer ---");
        service.getCustomer("C999");

        System.out.println("\n--- Adding a new customer ---");
        service.addCustomer("C004", "Anita Singh", "anita@example.com");
        service.getCustomer("C004");

        System.out.println("\n--- Deleting a customer ---");
        service.removeCustomer("C003");
        service.getCustomer("C003");

        System.out.println("\n=============================================");
        System.out.println("SUCCESS: Dependency Injection working correctly!");
        System.out.println("=============================================");
    }
}
