/*
@Author: Dynamic Duo Learners

REAL WORLD EXAMPLE:
Notification System.

An application may need to send different types of notifications:

- Email
- SMS
- WhatsApp

WITHOUT FACTORY:
Client directly creates concrete notification objects.

Example:

new EmailNotification();
new SmsNotification();
new WhatsAppNotification();

Problems:
- Client becomes tightly coupled to concrete notification classes.
- Object creation logic gets scattered across the application.
- Changing how an object is created may require changing client code.
- Adding new notification types increases creation-related logic.

WITH FACTORY:
Client asks one factory to create the required Notification.

Example:

Notification notification =
        NotificationFactory.create("EMAIL");

Client works with the common Notification interface instead of
directly creating concrete implementations.

MAIN IDEA:
"Centralize object creation and return objects through a common
 interface without exposing concrete creation details to the client."


STRUCTURE:

Client
  |
  v
NotificationFactory
  |
  v
Notification
  |
  +--> EmailNotification
  +--> SmsNotification
  +--> WhatsAppNotification
*/

public class FactoryMethodDemo {

    public static void main(String[] args) {

        Notification email =
                NotificationFactory.create("EMAIL");
        email.send();

        Notification sms =
                NotificationFactory.create("SMS");
        sms.send();

        Notification whatsapp =
                NotificationFactory.create("WHATSAPP");
        whatsapp.send();
    }
}


interface Notification {
    void send();
}

class EmailNotification implements Notification {

    @Override
    public void send() {
        System.out.println("Sending Email Notification");
    }
}

class SmsNotification implements Notification {

    @Override
    public void send() {
        System.out.println("Sending SMS Notification");
    }
}

class WhatsAppNotification implements Notification {

    @Override
    public void send() {
        System.out.println("Sending WhatsApp Notification");
    }
}


class NotificationFactory {

    public static Notification create(String type) {

        if ("EMAIL".equalsIgnoreCase(type)) {
            return new EmailNotification();
        }

        if ("SMS".equalsIgnoreCase(type)) {
            return new SmsNotification();
        }

        if ("WHATSAPP".equalsIgnoreCase(type)) {
            return new WhatsAppNotification();
        }

        throw new IllegalArgumentException(
                "Invalid Notification Type");
    }
}

/*

Output:

H:\Design Patterns\Creational>java FactoryMethodDemo
Sending Email Notification
Sending SMS Notification
Sending WhatsApp Notification

*/