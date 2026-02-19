/*
@Author: Dynamic Duo Learners

REAL WORLD EXAMPLE:
Order status notifications.

When order status changes, several systems may need to react:
- Customer notification
- Warehouse system
- Analytics

WITHOUT OBSERVER PATTERN:
Order directly calls every dependent service whenever its status changes.

Problems:
- Order becomes tightly coupled to all dependent services.
- Adding a new listener requires modifying Order/business logic.
- Notification/update logic can get duplicated.
- Managing many dependent services becomes harder as the system grows.

MAIN IDEA:
"One subject changes -> all subscribed observers are notified."

WITH OBSERVER:
Subject only notifies subscribed observers.

Order (Subject)
   |
   +--> CustomerNotificationObserver
   +--> WarehouseObserver
   +--> AnalyticsObserver
*/

import java.util.ArrayList;
import java.util.List;

public class ObserverPatternDemo {

    public static void main(String[] args) {
        System.out.println("====== ORDER STATUS USING OBSERVER PATTERN ======\n");

        Order order = new Order("ORD-101");

        OrderObserver customer = new CustomerNotificationObserver();
        OrderObserver warehouse = new WarehouseObserver();
        OrderObserver analytics = new AnalyticsObserver();

        order.subscribe(customer);
        order.subscribe(warehouse);
        order.subscribe(analytics);

        order.setStatus("CONFIRMED");
        System.out.println();
        order.setStatus("SHIPPED");

        System.out.println("\n--- Analytics unsubscribed ---\n");
        order.unsubscribe(analytics);
        order.setStatus("DELIVERED");
    }
}

// 1. OBSERVER
interface OrderObserver {
    void update(String orderId, String newStatus);
}

// 2. CONCRETE OBSERVERS
class CustomerNotificationObserver implements OrderObserver {
    @Override
    public void update(String orderId, String newStatus) {
        System.out.println("Customer Notification: " + orderId
                + " is now " + newStatus);
    }
}

class WarehouseObserver implements OrderObserver {
    @Override
    public void update(String orderId, String newStatus) {
        System.out.println("Warehouse: Updating workflow for " + orderId
                + " -> " + newStatus);
    }
}

class AnalyticsObserver implements OrderObserver {
    @Override
    public void update(String orderId, String newStatus) {
        System.out.println("Analytics: Tracking status change " + orderId
                + " -> " + newStatus);
    }
}

// 3. SUBJECT / PUBLISHER
class Order {
    private final String orderId;
    private String status;
    private final List<OrderObserver> observers = new ArrayList<>();

    public Order(String orderId) {
        this.orderId = orderId;
    }

    public void subscribe(OrderObserver observer) {
        observers.add(observer);
    }

    public void unsubscribe(OrderObserver observer) {
        observers.remove(observer);
    }

    public void setStatus(String status) {
        this.status = status;
        System.out.println("Order: Status changed to " + status);
        notifyObservers();
    }

    private void notifyObservers() {
        for (OrderObserver observer : observers) {
            observer.update(orderId, status);
        }
    }
}


/*
Output:

====== ORDER STATUS USING OBSERVER PATTERN ======

Order: Status changed to CONFIRMED
Customer Notification: ORD-101 is now CONFIRMED
Warehouse: Updating workflow for ORD-101 -> CONFIRMED
Analytics: Tracking status change ORD-101 -> CONFIRMED

Order: Status changed to SHIPPED
Customer Notification: ORD-101 is now SHIPPED
Warehouse: Updating workflow for ORD-101 -> SHIPPED
Analytics: Tracking status change ORD-101 -> SHIPPED

--- Analytics unsubscribed ---

Order: Status changed to DELIVERED
Customer Notification: ORD-101 is now DELIVERED
Warehouse: Updating workflow for ORD-101 -> DELIVERED

*/
