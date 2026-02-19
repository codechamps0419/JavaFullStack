/*
@Author: Dynamic Duo Learners

REAL WORLD EXAMPLE:
API request processing pipeline.

Before an API request reaches business logic it may pass through:
1. Authentication
2. Authorization
3. Rate-limit check
4. Business handler

Without Chain of Responsibility:
All validations and processing steps are usually written in one class or one method using 
large if-else or nested conditions.
This works for small logic, but it becomes hard to maintain, hard to extend, tightly coupled and 
violates single responsibility.

With Chain of Responsibility:
Each handler has one responsibility and decides whether to stop or
forward the request to the next handler.

MAIN IDEA:
"Pass a request through a chain of handlers until it is handled
 or processing is stopped."

STRUCTURE:
Request
  |
  v
AuthenticationHandler
  |
  v
AuthorizationHandler
  |
  v
RateLimitHandler
  |
  v
BusinessHandler
*/

public class ChainOfResponsibilityPatternDemo {

    public static void main(String[] args) {
        System.out.println("====== API PIPELINE USING CHAIN OF RESPONSIBILITY ======\n");

        RequestHandler chain = new AuthenticationHandler();

        chain.setNext(new AuthorizationHandler())
             .setNext(new RateLimitHandler())
             .setNext(new BusinessHandler());

        System.out.println("CASE 1: VALID REQUEST");
        System.out.println("---------------------");
        chain.handle(new ApiRequest("hrushi", true, "ADMIN", 3));

        System.out.println("\nCASE 2: UNAUTHENTICATED REQUEST");
        System.out.println("-------------------------------");
        chain.handle(new ApiRequest("guest", false, "ADMIN", 1));

        System.out.println("\nCASE 3: RATE LIMIT EXCEEDED");
        System.out.println("---------------------------");
        chain.handle(new ApiRequest("hrushi", true, "ADMIN", 15));
    }
}

// REQUEST
class ApiRequest {
    private final String username;
    private final boolean authenticated;
    private final String role;
    private final int requestsInCurrentWindow;

    public ApiRequest(String username,
                      boolean authenticated,
                      String role,
                      int requestsInCurrentWindow) {
        this.username = username;
        this.authenticated = authenticated;
        this.role = role;
        this.requestsInCurrentWindow = requestsInCurrentWindow;
    }

    public String getUsername() { return username; }
    public boolean isAuthenticated() { return authenticated; }
    public String getRole() { return role; }
    public int getRequestsInCurrentWindow() { return requestsInCurrentWindow; }
}

// ABSTRACT HANDLER
abstract class RequestHandler {
    private RequestHandler next;

    public RequestHandler setNext(RequestHandler next) {
        this.next = next;
        return next;
    }

    protected void handleNext(ApiRequest request) {
        if (next != null) {
            next.handle(request);
        }
    }

    public abstract void handle(ApiRequest request);
}

// CONCRETE HANDLER 1
class AuthenticationHandler extends RequestHandler {
    @Override
    public void handle(ApiRequest request) {
        System.out.println("Authentication Handler: Checking user...");

        if (!request.isAuthenticated()) {
            System.out.println("Authentication Handler: REJECTED.");
            return; // stop chain
        }

        System.out.println("Authentication Handler: PASSED.");
        handleNext(request);
    }
}

// CONCRETE HANDLER 2
class AuthorizationHandler extends RequestHandler {
    @Override
    public void handle(ApiRequest request) {
        System.out.println("Authorization Handler: Checking ADMIN role...");

        if (!"ADMIN".equals(request.getRole())) {
            System.out.println("Authorization Handler: ACCESS DENIED.");
            return; // stop chain
        }

        System.out.println("Authorization Handler: PASSED.");
        handleNext(request);
    }
}

// CONCRETE HANDLER 3
class RateLimitHandler extends RequestHandler {
    private static final int LIMIT = 10;

    @Override
    public void handle(ApiRequest request) {
        System.out.println("Rate Limit Handler: Checking request count...");

        if (request.getRequestsInCurrentWindow() > LIMIT) {
            System.out.println("Rate Limit Handler: TOO MANY REQUESTS.");
            return; // stop chain
        }

        System.out.println("Rate Limit Handler: PASSED.");
        handleNext(request);
    }
}

// FINAL HANDLER
class BusinessHandler extends RequestHandler {
    @Override
    public void handle(ApiRequest request) {
        System.out.println("Business Handler: Processing request for "
                + request.getUsername());
    }
}

/*
Output:

CASE 1: VALID REQUEST
---------------------
Authentication Handler: Checking user...
Authentication Handler: PASSED.
Authorization Handler: Checking ADMIN role...
Authorization Handler: PASSED.
Rate Limit Handler: Checking request count...
Rate Limit Handler: PASSED.
Business Handler: Processing request for hrushi

CASE 2: UNAUTHENTICATED REQUEST
-------------------------------
Authentication Handler: Checking user...
Authentication Handler: REJECTED.

CASE 3: RATE LIMIT EXCEEDED
---------------------------
Authentication Handler: Checking user...
Authentication Handler: PASSED.
Authorization Handler: Checking ADMIN role...
Authorization Handler: PASSED.
Rate Limit Handler: Checking request count...
Rate Limit Handler: TOO MANY REQUESTS.

*/
