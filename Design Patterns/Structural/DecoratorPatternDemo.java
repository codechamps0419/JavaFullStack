/*
@Author: Dynamic Duo Learners

REAL WORLD EXAMPLE:
Adding extra notification channels to an existing Notification Service.

Initially, the application sends only Email notifications.

Later, business may require:

- Email only
- Email + SMS
- Email + Slack
- Email + SMS + Slack


WITHOUT DECORATOR PATTERN:
NotificationService may contain multiple conditions for every
notification type.

Problems:
- Too many if/else or boolean conditions.
- Existing class must be modified whenever a new behavior is added.
- Many subclasses may be needed for different combinations.
- Combining optional behaviors dynamically becomes difficult.


WITH DECORATOR PATTERN:
Wrap the original object with one or more Decorator objects.

Example:

EmailNotification
       |
       v
SMSNotificationDecorator
       |
       v
SlackNotificationDecorator

Each decorator adds extra behavior while keeping the same
Notification interface.


MAIN IDEA:
"Wrap an object to add new behavior dynamically without modifying
 the original object's class."


STRUCTURE:

                    Notification
                    (Component)
                         |
             +-----------+-----------+
             |                       |
             v                       v
     EmailNotification       NotificationDecorator
    (Concrete Component)       (Base Decorator)
                                      |
                                +-----+-----+
                                |           |
                                v           v
                      SMSNotification   SlackNotification
                         Decorator          Decorator
*/

public class DecoratorPatternDemo {

    public static void main(String[] args) {

        System.out.println("====== NOTIFICATION SYSTEM USING DECORATOR PATTERN ======\n");


        // CASE 1: EMAIL ONLY

        System.out.println("CASE 1: EMAIL ONLY");
        System.out.println("------------------");

        Notification emailOnly = new EmailNotification();

        emailOnly.send("Your order #ORD-101 has been confirmed.");


        // CASE 1: EMAIL + SMS
        // We wrap EmailNotification inside SMSNotificationDecorator.

        System.out.println("\nCASE 2: EMAIL + SMS");
        System.out.println("-------------------");

        Notification emailAndSms = new SMSNotificationDecorator(new EmailNotification());

        emailAndSms.send("Your order #ORD-102 is out for delivery.");

        // CASE 3: EMAIL + SMS + SLACK
        // Wrapping happens layer by layer:
        // SlackDecorator
        //      |
        //      v
        // SMSDecorator
        //      |
        //      v
        // EmailNotification
        //
        // Client still sees only: Notification

        System.out.println("\nCASE 3: EMAIL + SMS + SLACK");
        System.out.println("---------------------------");

        Notification allChannels = new SlackNotificationDecorator(new SMSNotificationDecorator(new EmailNotification()));

        allChannels.send("Payment of Rs. 2,499 received successfully.");

        System.out.println("\n========================================================");
    }
}


// 1. COMPONENT
// Common interface used by: Concrete Component, Base Decorator, Concrete Decorators
// Because everyone implements the SAME interface, decorators can be wrapped around one another.

interface Notification {
    void send(String message);
}


// 2. CONCRETE COMPONENT
// This is the ORIGINAL object. It performs the basic/default behavior.
// We do NOT modify this class when we want to add:
//
// - SMS - Slack - WhatsApp - Push notification
// Instead, we decorate it.

class EmailNotification implements Notification {

    @Override
    public void send(String message) {

        System.out.println(
                "[EMAIL] Sent: " + message
        );
    }
}


// 3. BASE DECORATOR
// The base decorator:
// 1. Implements the SAME interface: Notification
// 2. Contains another Notification object.
// This is the most important relationship:
//        NotificationDecorator
//               |
//               | HAS-A
//               v
//          Notification
//
// The wrapped Notification can be:
// - EmailNotification
// - SMSNotificationDecorator
// - SlackNotificationDecorator
// - any future decorator

abstract class NotificationDecorator implements Notification {

    protected final Notification wrappedNotification;

    public NotificationDecorator(
            Notification wrappedNotification
    ) {

        this.wrappedNotification = wrappedNotification;
    }

    @Override
    public void send(String message) {

        /*
         * Delegate the original work to the wrapped object.         *
         * Concrete decorators can call: super.send(message)
         *
         * and then add their own behavior.
         */

        wrappedNotification.send(message);
    }
}


// 4. CONCRETE DECORATOR - SMS
// Adds SMS behavior.
// It does NOT replace the wrapped object's behavior.
// It:
// 1. Calls existing behavior.
// 2. Adds SMS behavior.

class SMSNotificationDecorator
        extends NotificationDecorator {


    public SMSNotificationDecorator(
            Notification wrappedNotification
    ) {

        super(wrappedNotification);
    }


    @Override
    public void send(String message) {

        // First perform existing wrapped behavior.
        super.send(message);

        // Then add new behavior.
        sendSms(message);
    }


    private void sendSms(String message) {

        System.out.println(
                "[SMS] Sent: " + message
        );
    }
}


// 5. CONCRETE DECORATOR - SLACK
// Adds Slack behavior dynamically.

class SlackNotificationDecorator
        extends NotificationDecorator {


    public SlackNotificationDecorator(
            Notification wrappedNotification
    ) {

        super(wrappedNotification);
    }


    @Override
    public void send(String message) {

        // Run behavior of everything wrapped inside.
        super.send(message);

        // Add Slack behavior.
        sendSlack(message);
    }


    private void sendSlack(String message) {

        System.out.println(
                "[SLACK] Sent: " + message
        );
    }
}


/*

Output:

H:\Design Patterns\Structural>java DecoratorPatternDemo
====== NOTIFICATION SYSTEM USING DECORATOR PATTERN ======

CASE 1: EMAIL ONLY
------------------
[EMAIL] Sent: Your order #ORD-101 has been confirmed.

CASE 2: EMAIL + SMS
-------------------
[EMAIL] Sent: Your order #ORD-102 is out for delivery.
[SMS] Sent: Your order #ORD-102 is out for delivery.

CASE 3: EMAIL + SMS + SLACK
---------------------------
[EMAIL] Sent: Payment of Rs. 2,499 received successfully.
[SMS] Sent: Payment of Rs. 2,499 received successfully.
[SLACK] Sent: Payment of Rs. 2,499 received successfully.

========================================================

*/