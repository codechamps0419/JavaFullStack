/*
@author CodeChamps0419

Demonstrates Java 17 Pattern Matching for instanceof:
1. Combines type checking and automatic casting into a single concise binding declaration.
2. Eliminates explicit casting boilerplate and prevents ClassCastException errors.
*/

package com.codechamps.language;

public class PatternMatchingDemo {

    // 1. NOTIFICATION CLASSES FOR POLYMORPHIC PROCESSING
    public sealed interface Notification 
        permits EmailNotification, SmsNotification, PushNotification {}

    public record EmailNotification(String recipient, String subject) implements Notification {}
    public record SmsNotification(String phoneNumber, String message) implements Notification {}
    public record PushNotification(String deviceId, String title) implements Notification {}

    // 2. PROCESS NOTIFICATION USING JAVA 17 PATTERN MATCHING FOR instanceof
    public static String processNotification(Object notification) {
        if (notification instanceof EmailNotification email) {
            // 'email' is automatically type-cast and in scope here!
            return "Sending Email to: " + email.recipient() + " | Subject: " + email.subject();
        } else if (notification instanceof SmsNotification sms) {
            // 'sms' is automatically type-cast and in scope here!
            return "Sending SMS to: " + sms.phoneNumber() + " | Message: " + sms.message();
        } else if (notification instanceof PushNotification push) {
            // 'push' is automatically type-cast and in scope here!
            return "Sending Push to Device: " + push.deviceId() + " | Title: " + push.title();
        } else {
            return "Unknown notification type";
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Java 17 Pattern Matching for instanceof Demo ---\n");

        Notification email = new EmailNotification("dynamicduolearners0419@gmail.com", "Project Architecture Update");
        Notification sms = new SmsNotification("+919075259303", "Your verification code is 0419");
        Notification push = new PushNotification("device_token_abc123", "New Build Deployed");

        System.out.println("1. " + processNotification(email));
        System.out.println("2. " + processNotification(sms));
        System.out.println("3. " + processNotification(push));
        System.out.println("4. " + processNotification("Plain String Object"));
    }
}

/*
Output:

--- Java 17 Pattern Matching for instanceof Demo ---

1. Sending Email to: dynamicduolearners0419@gmail.com | Subject: Project Architecture Update
2. Sending SMS to: +919075259303 | Message: Your verification code is 0419
3. Sending Push to Device: device_token_abc123 | Title: New Build Deployed
4. Unknown notification type

*/