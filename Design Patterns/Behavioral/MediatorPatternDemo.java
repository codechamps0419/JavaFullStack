/*
@Author: Dynamic Duo Learners

REAL WORLD EXAMPLE:
E-commerce order workflow coordination.

An order workflow may involve:
- Inventory Service
- Payment Service
- Shipping Service
- Notification Service

WITHOUT MEDIATOR PATTERN:
Services may directly call each other.

Problems:
- Components become tightly coupled.
- Every component needs knowledge of other components.
- Changing workflow may require changes in many classes.
- Adding a new participant increases dependencies.
- Many-to-many communication becomes difficult to maintain.

WITH MEDIATOR PATTERN:
Components communicate through one Mediator.

MAIN IDEA:
"Centralize communication between related objects so they do not
 communicate with each other directly."

STRUCTURE:

          InventoryService
                 |
                 v
           OrderMediator
          /      |       \
         /       |        \
        v        v         v
 Payment     Shipping   Notification
 Service     Service      Service
*/

public class MediatorPatternDemo {

    public static void main(String[] args) {

        System.out.println("====== MEDIATOR PATTERN: ORDER WORKFLOW ======\n");

        OrderMediatorImpl mediator =
                new OrderMediatorImpl();

        InventoryService inventory =
                new InventoryService(mediator);

        PaymentService payment =
                new PaymentService(mediator);

        ShippingService shipping =
                new ShippingService(mediator);

        NotificationService notification =
                new NotificationService(mediator);

        mediator.setInventoryService(inventory);
        mediator.setPaymentService(payment);
        mediator.setShippingService(shipping);
        mediator.setNotificationService(notification);

        // Client starts only the first business action.
        inventory.reserveInventory(
                "ORDER-101",
                "LAPTOP-001"
        );
    }
}


// MEDIATOR INTERFACE
interface OrderMediator {

    void notify(
            OrderComponent sender,
            String event,
            String orderId
    );
}


// BASE COLLEAGUE
abstract class OrderComponent {

    protected final OrderMediator mediator;

    protected OrderComponent(OrderMediator mediator) {

        this.mediator = mediator;
    }
}


// CONCRETE COLLEAGUE
class InventoryService extends OrderComponent {

    public InventoryService(OrderMediator mediator) {

        super(mediator);
    }

    public void reserveInventory(
            String orderId,
            String productId
    ) {

        System.out.println(
                "Inventory Service: Reserving "
                        + productId
                        + " for "
                        + orderId
        );

        System.out.println(
                "Inventory Service: Inventory reserved."
        );

        // Does NOT call PaymentService directly.
        mediator.notify(
                this,
                "INVENTORY_RESERVED",
                orderId
        );
    }
}


// CONCRETE COLLEAGUE
class PaymentService extends OrderComponent {

    public PaymentService(OrderMediator mediator) {

        super(mediator);
    }

    public void processPayment(String orderId) {

        System.out.println(
                "Payment Service: Processing payment for "
                        + orderId
        );

        System.out.println(
                "Payment Service: Payment successful."
        );

        // Does NOT call ShippingService directly.
        mediator.notify(
                this,
                "PAYMENT_SUCCESS",
                orderId
        );
    }
}


// CONCRETE COLLEAGUE
class ShippingService extends OrderComponent {

    public ShippingService(OrderMediator mediator) {

        super(mediator);
    }

    public void createShipment(String orderId) {

        System.out.println(
                "Shipping Service: Creating shipment for "
                        + orderId
        );

        System.out.println(
                "Shipping Service: Shipment created."
        );

        // Does NOT call NotificationService directly.
        mediator.notify(
                this,
                "SHIPMENT_CREATED",
                orderId
        );
    }
}


// CONCRETE COLLEAGUE
class NotificationService extends OrderComponent {

    public NotificationService(OrderMediator mediator) {

        super(mediator);
    }

    public void sendOrderConfirmation(String orderId) {

        System.out.println(
                "Notification Service: Confirmation sent for "
                        + orderId
        );

        mediator.notify(
                this,
                "NOTIFICATION_SENT",
                orderId
        );
    }
}


// CONCRETE MEDIATOR
class OrderMediatorImpl implements OrderMediator {

    private InventoryService inventoryService;
    private PaymentService paymentService;
    private ShippingService shippingService;
    private NotificationService notificationService;


    public void setInventoryService(
            InventoryService inventoryService
    ) {
        this.inventoryService = inventoryService;
    }

    public void setPaymentService(
            PaymentService paymentService
    ) {
        this.paymentService = paymentService;
    }

    public void setShippingService(
            ShippingService shippingService
    ) {
        this.shippingService = shippingService;
    }

    public void setNotificationService(
            NotificationService notificationService
    ) {
        this.notificationService = notificationService;
    }


    @Override
    public void notify(
            OrderComponent sender,
            String event,
            String orderId
    ) {

        System.out.println(
                "Mediator: Received event -> " + event
        );

        switch (event) {

            case "INVENTORY_RESERVED":

                System.out.println(
                        "Mediator: Asking Payment Service to continue."
                );

                paymentService.processPayment(orderId);
                break;


            case "PAYMENT_SUCCESS":

                System.out.println(
                        "Mediator: Asking Shipping Service to continue."
                );

                shippingService.createShipment(orderId);
                break;


            case "SHIPMENT_CREATED":

                System.out.println(
                        "Mediator: Asking Notification Service to continue."
                );

                notificationService.sendOrderConfirmation(orderId);
                break;


            case "NOTIFICATION_SENT":

                System.out.println(
                        "Mediator: Order workflow completed successfully."
                );
                break;


            default:

                System.out.println(
                        "Mediator: Unknown event."
                );
        }
    }
}


/*
Output:

H:\Design Patterns\Behavioral>java MediatorPatternDemo

====== MEDIATOR PATTERN: ORDER WORKFLOW ======

Inventory Service: Reserving LAPTOP-001 for ORDER-101
Inventory Service: Inventory reserved.
Mediator: Received event -> INVENTORY_RESERVED
Mediator: Asking Payment Service to continue.
Payment Service: Processing payment for ORDER-101
Payment Service: Payment successful.
Mediator: Received event -> PAYMENT_SUCCESS
Mediator: Asking Shipping Service to continue.
Shipping Service: Creating shipment for ORDER-101
Shipping Service: Shipment created.
Mediator: Received event -> SHIPMENT_CREATED
Mediator: Asking Notification Service to continue.
Notification Service: Confirmation sent for ORDER-101
Mediator: Received event -> NOTIFICATION_SENT
Mediator: Order workflow completed successfully.

*/
