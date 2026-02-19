/*
@Author: Dynamic Duo Learners

REAL WORLD EXAMPLE:
Secure Payment Service.

A real payment service performs the actual payment operation.

Before allowing access to it, the application may need to:

- Check authentication
- Validate payment amount
- Log the request
- Control when the real service is created
- Forward valid requests to the real service


WITHOUT PROXY PATTERN:
Client directly accesses RealPaymentService.

Example:

PaymentService service =
        new RealPaymentService();

service.pay(...);

Problems:
- Client gets direct access to the sensitive real service.
- Security and validation checks may get duplicated.
- Logging/access-control logic may get mixed with business logic.
- Real service may become responsible for too many concerns.
- It becomes difficult to control when the real object is accessed.


WITH PROXY PATTERN:
Create SecurePaymentProxy between the Client and RealPaymentService.

Both Proxy and Real Service implement the same PaymentService interface.

The Proxy:

1. Checks authentication
2. Validates payment amount
3. Logs the request
4. Creates the real service only when required
5. Delegates the payment to the real service


MAIN IDEA:
"Provide a substitute object that controls access to the real object."


STRUCTURE:

                    PaymentService
                       (Subject)
                      /         \
                     /           \
                    v             v
        RealPaymentService   SecurePaymentProxy
          (Real Subject)         (Proxy)
                                     |
                                     | HAS-A
                                     v
                           RealPaymentService


FLOW:

Client
  |
  v
SecurePaymentProxy
  |
  |-- Authentication
  |-- Validation
  |-- Logging
  |-- Lazy Creation
  |
  v
RealPaymentService
  |
  v
Process Payment

COMMON PROXY TYPES:

- Protection Proxy -> Controls access/security
- Virtual Proxy    -> Lazy creation of expensive objects
- Remote Proxy     -> Represents remote objects/services
- Caching Proxy    -> Returns cached results when possible
- Logging Proxy    -> Adds logging/monitoring around calls

*/

public class ProxyPatternDemo {

    public static void main(String[] args) {

        System.out.println("====== PAYMENT SERVICE USING PROXY PATTERN ======\n");


        // CASE 1: AUTHORIZED USER

        System.out.println("CASE 1: AUTHORIZED USER");
        System.out.println("-----------------------");

        User authorizedUser =
                new User(
                        "AP",
                        true,
                        "CUSTOMER"
                );

        PaymentService paymentService =
                new SecurePaymentProxy(
                        authorizedUser
                );

        paymentService.pay(
                "ORDER-101",
                2499.00
        );


        // CASE 2: UNAUTHENTICATED USER

        System.out.println("\nCASE 2: UNAUTHENTICATED USER");
        System.out.println("----------------------------");

        User unauthenticatedUser =
                new User(
                        "Guest",
                        false,
                        "CUSTOMER"
                );

        PaymentService blockedService =
                new SecurePaymentProxy(
                        unauthenticatedUser
                );

        blockedService.pay(
                "ORDER-102",
                1200.00
        );


        // CASE 3: INVALID PAYMENT AMOUNT

        System.out.println("\nCASE 3: INVALID PAYMENT AMOUNT");
        System.out.println("------------------------------");

        PaymentService invalidPaymentService =
                new SecurePaymentProxy(
                        authorizedUser
                );

        invalidPaymentService.pay(
                "ORDER-103",
                -500.00
        );


        System.out.println("\n=================================================");
    }
}


// 1. SUBJECT
// Common interface implemented by BOTH:
// - Real Subject
// - Proxy
//
// Because both have the same interface, client can use either one transparently.

interface PaymentService {

    void pay(
            String orderId,
            double amount
    );
}


// 2. REAL SUBJECT
// This is the REAL object that performs the actual business operation.
// Its main job is:
//     PROCESS PAYMENT
// It should not be overloaded with:
// - authentication
// - access control
// - request logging
// - proxy-related concerns

class RealPaymentService
        implements PaymentService {

    @Override
    public void pay(
            String orderId,
            double amount
    ) {

        System.out.println(
                "Real Payment Service: Processing payment..."
        );

        System.out.println(
                "Real Payment Service: Order = "
                        + orderId
                        + ", Amount = Rs. "
                        + amount
        );

        System.out.println(
                "Real Payment Service: Payment SUCCESSFUL."
        );
    }
}


// 3. PROXY
// This class stands BETWEEN:
//     Client
// and
//     RealPaymentService
//
// Its job is to CONTROL ACCESS.
//
// It can perform:
// - authentication
// - authorization
// - validation
// - logging
// - lazy object creation
// - caching
// - rate limiting
// depending on the Proxy type.

// In this example:
// 1. Authenticate user
// 2. Validate amount
// 3. Log request
// 4. Forward call to RealPaymentService

class SecurePaymentProxy
        implements PaymentService {

    private final User user;


    /*
     * Notice:
     *
     * We do NOT create RealPaymentService immediately.
     *
     * It starts as null.
     *
     * The real object is created only when an authorized,
     * valid request actually needs it.
     *
     * This also demonstrates a small Virtual Proxy idea:
     *
     *     Lazy Initialization
     */
    private RealPaymentService realPaymentService;


    public SecurePaymentProxy(
            User user
    ) {

        this.user = user;
    }


    @Override
    public void pay(
            String orderId,
            double amount
    ) {

        System.out.println(
                "Proxy: Payment request received."
        );

	// STEP 1: AUTHENTICATION CHECK
        
	if (!user.isAuthenticated()) {

            System.out.println(
                    "Proxy: ACCESS DENIED - User is not authenticated."
            );

            return;
        }


        // STEP 2: VALIDATE PAYMENT AMOUNT
        
        if (amount <= 0) {

            System.out.println(
                    "Proxy: PAYMENT REJECTED - Amount must be greater than zero."
            );

            return;
        }


        // STEP 3: LOG ACCESS
        
        System.out.println(
                "Proxy: User "
                        + user.getName()
                        + " is authenticated."
        );

        System.out.println(
                "Proxy: Request validated. Forwarding to real service..."
        );


        // STEP 4: LAZY CREATION OF REAL OBJECT
        
        if (realPaymentService == null) {

            System.out.println(
                    "Proxy: Creating RealPaymentService only when needed."
            );

            realPaymentService =
                    new RealPaymentService();
        }


        // STEP 5: DELEGATE TO REAL OBJECT
        
        realPaymentService.pay(
                orderId,
                amount
        );


        // STEP 6: OPTIONAL AFTER-CALL LOGGING
        
        System.out.println(
                "Proxy: Payment request completed."
        );
    }
}


// SUPPORTING USER CLASS

class User {

    private final String name;
    private final boolean authenticated;
    private final String role;


    public User(
            String name,
            boolean authenticated,
            String role
    ) {

        this.name = name;
        this.authenticated = authenticated;
        this.role = role;
    }


    public String getName() {

        return name;
    }


    public boolean isAuthenticated() {

        return authenticated;
    }


    public String getRole() {

        return role;
    }
}


/*
Output:

H:\Design Patterns\Structural> java ProxyPatternDemo

====== PAYMENT SERVICE USING PROXY PATTERN ======

CASE 1: AUTHORIZED USER
-----------------------
Proxy: Payment request received.
Proxy: User AP is authenticated.
Proxy: Request validated. Forwarding to real service...
Proxy: Creating RealPaymentService only when needed.
Real Payment Service: Processing payment...
Real Payment Service: Order = ORDER-101, Amount = Rs. 2499.0
Real Payment Service: Payment SUCCESSFUL.
Proxy: Payment request completed.

CASE 2: UNAUTHENTICATED USER
----------------------------
Proxy: Payment request received.
Proxy: ACCESS DENIED - User is not authenticated.

CASE 3: INVALID PAYMENT AMOUNT
------------------------------
Proxy: Payment request received.
Proxy: PAYMENT REJECTED - Amount must be greater than zero.

=================================================

*/
