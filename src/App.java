import java.util.ArrayList;
import java.util.List;

// Subject class
class InventorySubject {
    private List<InventoryObserver> observers = new ArrayList<>();
    private String productName;
    private int stockLevel;

    // Register an observer
    public void registerObserver(InventoryObserver observer) {
        observers.add(observer);
    }

    // Deregister an observer
    public void removeObserver(InventoryObserver observer) {
        observers.remove(observer);
    }

    // Notify all observers about state changes
    public void notifyObservers() {
        for (InventoryObserver observer : observers) {
            observer.update(productName, stockLevel);
        }
    }

    // Update inventory state and notify observers
    public void setInventoryState(String productName, int stockLevel) {
        this.productName = productName;
        this.stockLevel = stockLevel;
        notifyObservers();
    }
}

// Observer interface
interface InventoryObserver {
    void update(String productName, int stockLevel);
}

// Concrete Observer: AdminNotificationObserver
class AdminNotificationObserver implements InventoryObserver {
    @Override
    public void update(String productName, int stockLevel) {
        if (stockLevel < 5) {
            System.out.println("Admin Alert: Low stock for product: " + productName + " (Stock: " + stockLevel + ")");
        }
    }
}

// Concrete Observer: CustomerNotificationObserver
class CustomerNotificationObserver implements InventoryObserver {
    @Override
    public void update(String productName, int stockLevel) {
        if (stockLevel > 0) {
            System.out.println("Customer Notification: " + productName + " is back in stock! (Stock: " + stockLevel + ")");
        }
    }
}

// Concrete Observer: SalesReportObserver
class SalesReportObserver implements InventoryObserver {
    @Override
    public void update(String productName, int stockLevel) {
        System.out.println("Sales Report Updated: " + productName + " now has " + stockLevel + " units in stock.");
    }
}

// Main class
public class App {
    public static void main(String[] args) {
        // Create subject
        InventorySubject inventory = new InventorySubject();

        // Create observers
        InventoryObserver adminObserver = new AdminNotificationObserver();
        InventoryObserver customerObserver = new CustomerNotificationObserver();
        InventoryObserver salesObserver = new SalesReportObserver();

        // Register observers
        inventory.registerObserver(adminObserver);
        inventory.registerObserver(customerObserver);
        inventory.registerObserver(salesObserver);

        // Simulate inventory updates
        System.out.println("=== Inventory Update 1 ===");
        inventory.setInventoryState("Laptop", 10);

        System.out.println("\n=== Inventory Update 2 ===");
        inventory.setInventoryState("Laptop", 4);

    }
}