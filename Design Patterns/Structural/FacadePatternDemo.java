/*
@Author: Dynamic Duo Learners

REAL WORLD EXAMPLE:
E-commerce Order Placement.

When a customer clicks "Place Order", several internal services
may be involved:

- Inventory Service
- Payment Service
- Shipping Service
- Notification Service


WITHOUT FACADE PATTERN:
Client directly interacts with all subsystem services and manages
the complete order workflow itself.

Problems:
- Client must know many subsystem classes.
- Client must know the correct sequence of calls.
- Client becomes tightly coupled to subsystem details.
- Workflow logic may get duplicated in multiple clients.
- Changes in the internal workflow may require client changes.


WITH FACADE PATTERN:
Create one OrderFacade that provides a simple high-level operation.

Client calls only:

    orderFacade.placeOrder(...);

OrderFacade internally coordinates:

    InventoryService
        |
        v
    PaymentService
        |
        v
    ShippingService
        |
        v
    NotificationService


MAIN IDEA:
"Provide one simple interface to a complex subsystem."


STRUCTURE:

                    Client
                      |
                      v
                 OrderFacade
                      |
        +-------------+-------------+-------------+
        |             |             |             |
        v             v             v             v
   Inventory       Payment       Shipping    Notification
    Service         Service       Service       Service


IMPORTANT:
Facade should mainly coordinate and delegate work to subsystem
classes. It should not contain all business logic itself.

*/

public class FacadePatternDemo {

    public static void main(String[] args) {

        System.out.println("====== E-COMMERCE ORDER USING FACADE PATTERN ======\n");

        // 1. CREATE SUBSYSTEM OBJECTS
        
	InventoryService inventoryService = new InventoryService();
        PaymentService paymentService = new PaymentService();
        ShippingService shippingService = new ShippingService();
        NotificationService notificationService = new NotificationService();

        // 2. CREATE FACADE
        
	OrderFacade orderFacade = new OrderFacade(
                inventoryService,
                paymentService,
                shippingService,
                notificationService
        );

        // 3. CLIENT CALLS ONE SIMPLE METHOD
        
	boolean success = orderFacade.placeOrder(
                "CUST-101",
                "LAPTOP-001",
                1,
                74999.00
        );

        System.out.println("\n--------------------------------------------------");

        if (success) {
            System.out.println("CLIENT: Order completed successfully.");
        } else {
            System.out.println("CLIENT: Order could not be completed.");
        }

        System.out.println("==================================================");
    }
}


// 1. SUBSYSTEM - INVENTORY SERVICE
// Handles only inventory-related work.
// It does not know about payment, shipping, or notifications.

class InventoryService {

    public boolean isAvailable(String productId, int quantity) {

        System.out.println(
                "Inventory Service: Checking stock for "
                        + productId
                        + ", quantity = "
                        + quantity
        );

        // Demo assumption: stock is available.
        return true;
    }

    public void reserve(String productId, int quantity) {

        System.out.println(
                "Inventory Service: Reserved "
                        + quantity
                        + " unit(s) of "
                        + productId
        );
    }
}


// 2. SUBSYSTEM - PAYMENT SERVICE
// Handles only payment-related work.

class PaymentService {

    public boolean processPayment(String customerId, double amount) {

        System.out.println(
                "Payment Service: Processing payment of Rs. "
                        + amount
                        + " for customer "
                        + customerId
        );

        // Demo assumption: payment succeeds.
        return true;
    }
}


// 3. SUBSYSTEM - SHIPPING SERVICE
// Handles only shipment-related work.

class ShippingService {

    public String createShipment(
            String customerId,
            String productId,
            int quantity
    ) {

        String shipmentId = "SHIP-9001";

        System.out.println(
                "Shipping Service: Shipment created. ID = "
                        + shipmentId
        );

        return shipmentId;
    }
}


// 4. SUBSYSTEM - NOTIFICATION SERVICE
// Handles only customer notification work.

class NotificationService {

    public void sendOrderConfirmation(
            String customerId,
            String shipmentId
    ) {

        System.out.println(
                "Notification Service: Confirmation sent to "
                        + customerId
                        + " for shipment "
                        + shipmentId
        );
    }
}


// 5. FACADE
// OrderFacade provides one simple method to the client:
//     placeOrder(...)
//
// Internally it knows:
// 1. which subsystem to call
// 2. in what order
// 3. when to stop the workflow
// 4. what result to return to the client

class OrderFacade {

    private final InventoryService inventoryService;
    private final PaymentService paymentService;
    private final ShippingService shippingService;
    private final NotificationService notificationService;

    public OrderFacade(
            InventoryService inventoryService,
            PaymentService paymentService,
            ShippingService shippingService,
            NotificationService notificationService
    ) {
        this.inventoryService = inventoryService;
        this.paymentService = paymentService;
        this.shippingService = shippingService;
        this.notificationService = notificationService;
    }

    // SIMPLE HIGH-LEVEL API EXPOSED TO THE CLIENT

    public boolean placeOrder(
            String customerId,
            String productId,
            int quantity,
            double amount
    ) {

        System.out.println("Order Facade: Starting order process...");

        // Step 1: Check stock.
        boolean available = inventoryService.isAvailable(
                productId,
                quantity
        );

        if (!available) {
            System.out.println("Order Facade: Product is out of stock.");
            return false;
        }

        // Step 2: Reserve stock.
        inventoryService.reserve(productId, quantity);

        // Step 3: Process payment.
        boolean paymentSuccessful = paymentService.processPayment(
                customerId,
                amount
        );

        if (!paymentSuccessful) {
            System.out.println("Order Facade: Payment failed.");
            return false;
        }

        // Step 4: Create shipment.
        String shipmentId = shippingService.createShipment(
                customerId,
                productId,
                quantity
        );

        // Step 5: Notify customer.
        notificationService.sendOrderConfirmation(
                customerId,
                shipmentId
        );

        System.out.println("Order Facade: Order process completed.");
        return true;
    }
}


/*
Output:

H:\Design Patterns\Structural> java FacadePatternDemo

====== E-COMMERCE ORDER USING FACADE PATTERN ======

Order Facade: Starting order process...
Inventory Service: Checking stock for LAPTOP-001, quantity = 1
Inventory Service: Reserved 1 unit(s) of LAPTOP-001
Payment Service: Processing payment of Rs. 74999.0 for customer CUST-101
Shipping Service: Shipment created. ID = SHIP-9001
Notification Service: Confirmation sent to CUST-101 for shipment SHIP-9001
Order Facade: Order process completed.

--------------------------------------------------
CLIENT: Order completed successfully.
==================================================

*/
