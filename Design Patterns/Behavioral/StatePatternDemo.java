/*
@Author: Dynamic Duo Learners

STATE DESIGN PATTERN - REAL WORLD EXAMPLE
==========================================

REAL WORLD EXAMPLE:
Order lifecycle.

An order behaves differently depending on its current state:

NEW -> PAID -> SHIPPED -> DELIVERED

Examples:
- NEW order can be paid.
- PAID order can be shipped.
- SHIPPED order can be delivered.
- DELIVERED order cannot be paid again.

WITHOUT STATE PATTERN:
Order logic usually contains multiple if/else or switch conditions
to check the current state before performing every action.

Problems:
- State-based if/else or switch logic keeps growing.
- Adding a new state requires changing existing business logic.
- State-specific behavior gets mixed inside one class.
- Valid and invalid state transitions become harder to manage.
- Code becomes difficult to maintain as states and actions increase.

WITH STATE PATTERN:
Each state is represented by a separate State object.
OrderContext delegates the requested action to its current state,
and that state decides the behavior and possible next state.

MAIN IDEA:
"An object changes its behavior when its internal state changes."

STRUCTURE:

OrderContext
   |
   v
OrderState
   |
   +--> NewOrderState
   +--> PaidOrderState
   +--> ShippedOrderState
   +--> DeliveredOrderState

*/

public class StatePatternDemo {

    public static void main(String[] args) {
        System.out.println("====== ORDER LIFECYCLE USING STATE PATTERN ======\n");

        OrderContext order = new OrderContext("ORD-101");

        order.printState();
        order.pay();

        order.printState();
        order.ship();

        order.printState();
        order.deliver();

        order.printState();

        System.out.println("\n--- Invalid action after delivery ---");
        order.pay();
    }
}

// 1. STATE
interface OrderState {
    void pay(OrderContext context);
    void ship(OrderContext context);
    void deliver(OrderContext context);
    String getName();
}

// 2. CONTEXT
class OrderContext {
    private final String orderId;
    private OrderState state;

    public OrderContext(String orderId) {
        this.orderId = orderId;
        this.state = new NewOrderState();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setState(OrderState state) {
        System.out.println("Order: State changed "
                + this.state.getName() + " -> " + state.getName());
        this.state = state;
    }

    public void pay() {
        state.pay(this);
    }

    public void ship() {
        state.ship(this);
    }

    public void deliver() {
        state.deliver(this);
    }

    public void printState() {
        System.out.println("Current State: " + state.getName());
    }
}

// 3. CONCRETE STATE
class NewOrderState implements OrderState {
    @Override
    public void pay(OrderContext context) {
        System.out.println("NEW State: Payment accepted for "
                + context.getOrderId());
        context.setState(new PaidOrderState());
    }

    @Override
    public void ship(OrderContext context) {
        System.out.println("NEW State: Cannot ship before payment.");
    }

    @Override
    public void deliver(OrderContext context) {
        System.out.println("NEW State: Cannot deliver before shipping.");
    }

    @Override
    public String getName() {
        return "NEW";
    }
}

// 4. CONCRETE STATE
class PaidOrderState implements OrderState {
    @Override
    public void pay(OrderContext context) {
        System.out.println("PAID State: Order is already paid.");
    }

    @Override
    public void ship(OrderContext context) {
        System.out.println("PAID State: Shipping order "
                + context.getOrderId());
        context.setState(new ShippedOrderState());
    }

    @Override
    public void deliver(OrderContext context) {
        System.out.println("PAID State: Cannot deliver before shipping.");
    }

    @Override
    public String getName() {
        return "PAID";
    }
}

// 5. CONCRETE STATE
class ShippedOrderState implements OrderState {
    @Override
    public void pay(OrderContext context) {
        System.out.println("SHIPPED State: Payment already completed.");
    }

    @Override
    public void ship(OrderContext context) {
        System.out.println("SHIPPED State: Order is already shipped.");
    }

    @Override
    public void deliver(OrderContext context) {
        System.out.println("SHIPPED State: Delivering order "
                + context.getOrderId());
        context.setState(new DeliveredOrderState());
    }

    @Override
    public String getName() {
        return "SHIPPED";
    }
}

// 6. CONCRETE STATE
class DeliveredOrderState implements OrderState {
    @Override
    public void pay(OrderContext context) {
        System.out.println("DELIVERED State: Cannot pay again.");
    }

    @Override
    public void ship(OrderContext context) {
        System.out.println("DELIVERED State: Order is already delivered.");
    }

    @Override
    public void deliver(OrderContext context) {
        System.out.println("DELIVERED State: Order is already delivered.");
    }

    @Override
    public String getName() {
        return "DELIVERED";
    }
}

/*
Output:

====== ORDER LIFECYCLE USING STATE PATTERN ======

Current State: NEW
NEW State: Payment accepted for ORD-101
Order: State changed NEW -> PAID
Current State: PAID
PAID State: Shipping order ORD-101
Order: State changed PAID -> SHIPPED
Current State: SHIPPED
SHIPPED State: Delivering order ORD-101
Order: State changed SHIPPED -> DELIVERED
Current State: DELIVERED

--- Invalid action after delivery ---
DELIVERED State: Cannot pay again.

*/
