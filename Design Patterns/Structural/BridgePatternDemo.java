/*
@Author: Dynamic Duo Learners

REAL WORLD EXAMPLE:
Notification system with different notification types and delivery channels.

Notification TYPES:
- Alert Notification
- Promotional Notification

Delivery CHANNELS:
- Email
- SMS
- Push Notification

WITHOUT BRIDGE PATTERN:
We may create a separate class for every combination:

AlertEmailNotification
AlertSmsNotification
AlertPushNotification
PromotionalEmailNotification
PromotionalSmsNotification
PromotionalPushNotification

Problems:
- Number of classes grows rapidly.
- Notification type becomes tightly coupled to delivery channel.
- Adding a new channel requires many new subclasses.
- Adding a new notification type also creates many combinations.
- Code becomes harder to maintain.

WITH BRIDGE PATTERN:
Separate the two dimensions:

1. Abstraction     -> Notification type
2. Implementation  -> Delivery channel

Notification HAS-A MessageSender.

MAIN IDEA:
"Separate abstraction from implementation so both can vary independently."

STRUCTURE:

                 Notification
                 (Abstraction)
                      |
              +-------+-------+
              |               |
              v               v
      AlertNotification   PromotionalNotification
              |
              | HAS-A
              v
         MessageSender
       (Implementation)
        /      |       \
       v       v        v
 EmailSender SmsSender PushSender
*/

public class BridgePatternDemo {

    public static void main(String[] args) {

        System.out.println("====== BRIDGE PATTERN: NOTIFICATION SYSTEM ======\n");

        Notification alertByEmail =
                new AlertNotification(new EmailSender());

        alertByEmail.send("CPU usage crossed 90%.");

        System.out.println();

        Notification alertBySms =
                new AlertNotification(new SmsSender());

        alertBySms.send("Database connection pool is almost full.");

        System.out.println();

        Notification promoByPush =
                new PromotionalNotification(new PushSender());

        promoByPush.send("Flat 20% OFF on selected products.");

        System.out.println();

        Notification promoByEmail =
                new PromotionalNotification(new EmailSender());

        promoByEmail.send("Your weekend sale coupon is ready.");
    }
}


// IMPLEMENTATION
interface MessageSender {

    void sendMessage(String subject, String message);
}


// CONCRETE IMPLEMENTATION
class EmailSender implements MessageSender {

    @Override
    public void sendMessage(String subject, String message) {

        System.out.println("[EMAIL] Subject: " + subject);
        System.out.println("[EMAIL] Message: " + message);
    }
}


// CONCRETE IMPLEMENTATION
class SmsSender implements MessageSender {

    @Override
    public void sendMessage(String subject, String message) {

        System.out.println("[SMS] " + subject + " - " + message);
    }
}


// CONCRETE IMPLEMENTATION
class PushSender implements MessageSender {

    @Override
    public void sendMessage(String subject, String message) {

        System.out.println("[PUSH] " + subject + " -> " + message);
    }
}


// ABSTRACTION
abstract class Notification {

    /*
     * This reference is the BRIDGE.
     *
     * Notification does not depend on a concrete sender.
     * It depends on the MessageSender abstraction.
     */
    protected final MessageSender messageSender;

    protected Notification(MessageSender messageSender) {

        this.messageSender = messageSender;
    }

    public abstract void send(String message);
}


// REFINED ABSTRACTION
class AlertNotification extends Notification {

    public AlertNotification(MessageSender messageSender) {

        super(messageSender);
    }

    @Override
    public void send(String message) {

        System.out.println(
                "Alert Notification: Preparing urgent alert..."
        );

        messageSender.sendMessage(
                "SYSTEM ALERT",
                message
        );
    }
}


// REFINED ABSTRACTION
class PromotionalNotification extends Notification {

    public PromotionalNotification(MessageSender messageSender) {

        super(messageSender);
    }

    @Override
    public void send(String message) {

        System.out.println(
                "Promotional Notification: Preparing marketing message..."
        );

        messageSender.sendMessage(
                "PROMOTIONAL OFFER",
                message
        );
    }
}


/*
Output:

H:\Design Patterns\Structural>java BridgePatternDemo

====== BRIDGE PATTERN: NOTIFICATION SYSTEM ======

Alert Notification: Preparing urgent alert...
[EMAIL] Subject: SYSTEM ALERT
[EMAIL] Message: CPU usage crossed 90%.

Alert Notification: Preparing urgent alert...
[SMS] SYSTEM ALERT - Database connection pool is almost full.

Promotional Notification: Preparing marketing message...
[PUSH] PROMOTIONAL OFFER -> Flat 20% OFF on selected products.

Promotional Notification: Preparing marketing message...
[EMAIL] Subject: PROMOTIONAL OFFER
[EMAIL] Message: Your weekend sale coupon is ready.

*/
