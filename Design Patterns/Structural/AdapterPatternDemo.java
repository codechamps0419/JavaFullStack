/*
@Author: Dynamic Duo Learners

REAL WORLD EXAMPLE:
Integrating an external Legacy Payment Gateway into a modern
E-commerce application.

Modern application expects:

    processTransaction(double amount)
        -> returns boolean

But LegacyBankAPI provides:

    makePayment(double amount, int currencyCode)
        -> returns int (1 = success, 0 = failure)


WITHOUT ADAPTER PATTERN:
Client directly calls LegacyBankAPI and handles its incompatible
method signature, currency code and return type.

Problems:
- Client becomes tightly coupled to the legacy API.
- Client must understand legacy parameters and status codes.
- Legacy-specific conversion logic gets mixed with business logic.
- Replacing the legacy provider later may require client changes.


WITH ADAPTER PATTERN:
BankPaymentAdapter sits between the modern application and
LegacyBankAPI.

It converts:

    processTransaction(amount)
              |
              v
    makePayment(amount, 840)

and converts the response:

    1 / 0
      |
      v
 true / false


MAIN IDEA:
"Convert an existing incompatible interface into the interface
 expected by the client."


STRUCTURE:

Client
  |
  v
ModernPaymentGateway
(Target Interface)
  |
  v
BankPaymentAdapter
(Adapter)
  |
  v
LegacyBankAPI
(Adaptee)


ROLE MAPPING:

ModernPaymentGateway -> Target
LegacyBankAPI         -> Adaptee
BankPaymentAdapter    -> Adapter
AdapterPatternDemo    -> Client

*/

public class AdapterPatternDemo {

    public static void main(String[] args) {
        System.out.println("====== STARTING E-COMMERCE CHECKOUT ======");

        // 1. We have our old, unmodifiable legacy system dependency
        LegacyBankAPI oldBankSystem = new LegacyBankAPI();

        // 2. We wrap it inside our Structural Adapter to match our expected interface
        ModernPaymentGateway paymentProcessor = new BankPaymentAdapter(oldBankSystem);

        // 3. Our e-commerce client interacts ONLY with the modern interface seamlessly
        double orderTotal = 49.99;
        boolean isSuccess = paymentProcessor.processTransaction(orderTotal);

        System.out.println("\n--- Finalizing Order Status ---");
        if (isSuccess) {
            System.out.println("Checkout Status: SUCCESS! Order has been placed safely.");
        } else {
            System.out.println("Checkout Status: FAILED! Payment was declined.");
        }
        System.out.println("==========================================");
    }
}

// 1. THE TARGET INTERFACE (What our modern application expects)
interface ModernPaymentGateway {
    boolean processTransaction(double amountInUSD);
}

// 2. THE ADAPTEE (The legacy banking portal code we cannot change)
class LegacyBankAPI {
    public int makePayment(double amount, int currencyCode) {
        System.out.println("Legacy Bank API: Processing charge of $" + amount + " using ISO Currency Code: " + currencyCode);
        return 1; // 1 means Success, 0 means Failure
    }
}

// 3. THE ADAPTER (The structural translation bridge)
class BankPaymentAdapter implements ModernPaymentGateway {
    
    // Structural Pattern feature: Composition (Nesting the old object inside the new structure)
    private final LegacyBankAPI legacyBankSystem;

    public BankPaymentAdapter(LegacyBankAPI legacyBankSystem) {
        this.legacyBankSystem = legacyBankSystem;
    }

    @Override
    public boolean processTransaction(double amountInUSD) {
        System.out.println("Adapter: Intercepting call. Translating parameters for Legacy Bank...");
        
        // Step 1: Adapt the parameters (Translate context to expected ISO 840 numeric code for USD)
        int usdCode = 840;

        // Step 2: Route the call to the legacy system's mismatched method
        int statusCode = legacyBankSystem.makePayment(amountInUSD, usdCode);

        // Step 3: Adapt the return type (Convert integer status code to our modern boolean)
        return statusCode == 1;
    }
}

/*

Output:

H:\Design Patterns\Structural>java AdapterPatternDemo
====== STARTING E-COMMERCE CHECKOUT ======
Adapter: Intercepting call. Translating parameters for Legacy Bank...
Legacy Bank API: Processing charge of $49.99 using ISO Currency Code: 840

--- Finalizing Order Status ---
Checkout Status: SUCCESS! Order has been placed safely.
==========================================

*/