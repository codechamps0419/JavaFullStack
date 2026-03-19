/*
@author CodeChamps0419

Demonstrates Java 17 Sealed Classes & Controlled Inheritance:
1. Defines a sealed interface with permitted implementations using records.
2. Utilizes pattern matching in switch statements with exhaustive branch checking.
*/

package com.codechamps.language;

import java.math.BigDecimal;
import java.util.Objects;

public class SealedClassDomainDemo {

    // 1. SEALED INTERFACE WITH CONTROLLED PERMITTED SUB-TYPES
    public sealed interface PaymentMethod 
        permits PaymentMethod.CreditCard, PaymentMethod.BankTransfer, PaymentMethod.DigitalWallet {

        BigDecimal processPayment(BigDecimal amount);

        // Permitted Subtype 1: CreditCard Record
        record CreditCard(String cardNumber, String expiryDate, String cvv) implements PaymentMethod {
            public CreditCard {
                Objects.requireNonNull(cardNumber, "Card number cannot be null");
            }

            @Override
            public BigDecimal processPayment(BigDecimal amount) {
                System.out.println("   -> Processing Credit Card payment of Rs. " + amount);
                return amount.multiply(new BigDecimal("0.025")); // 2.5% fee
            }
        }

        // Permitted Subtype 2: BankTransfer Record
        record BankTransfer(String accountNumber, String routingNumber) implements PaymentMethod {
            public BankTransfer {
                Objects.requireNonNull(accountNumber, "Account number cannot be null");
            }

            @Override
            public BigDecimal processPayment(BigDecimal amount) {
                System.out.println("   -> Processing Bank Transfer of Rs. " + amount);
                return BigDecimal.ZERO; // Flat fee
            }
        }

        // Permitted Subtype 3: DigitalWallet Record
        record DigitalWallet(String walletId, String provider) implements PaymentMethod {
            public DigitalWallet {
                Objects.requireNonNull(walletId, "Wallet ID cannot be null");
            }

            @Override
            public BigDecimal processPayment(BigDecimal amount) {
                System.out.println("   -> Processing Digital Wallet (" + provider + ") payment of Rs. " + amount);
                return amount.multiply(new BigDecimal("0.01")); // 1% fee
            }
        }
    }

    // 2. EXHAUSTIVE SWITCH PATTERN MATCHING METHOD (No default branch required!)
    public static void executePaymentWorkflow(PaymentMethod method, BigDecimal amount) {
        System.out.println("Executing Payment via " + method.getClass().getSimpleName() + ":");
        
        BigDecimal fee = switch (method) {
            case PaymentMethod.CreditCard cc -> cc.processPayment(amount);
            case PaymentMethod.BankTransfer bt -> bt.processPayment(amount);
            case PaymentMethod.DigitalWallet dw -> dw.processPayment(amount);
        };

        System.out.println("   - Calculated Transaction Fee: Rs. " + fee + "\n");
    }

    public static void main(String[] args) {
        System.out.println("--- Java 17 Sealed Classes & Exhaustive Matching Demo ---\n");

        PaymentMethod cc = new PaymentMethod.CreditCard("1234-5678-9876-5432", "12/28", "456");
        PaymentMethod bank = new PaymentMethod.BankTransfer("987654321", "021000021");
        PaymentMethod wallet = new PaymentMethod.DigitalWallet("user_wallet_99", "PayPal");

        executePaymentWorkflow(cc, new BigDecimal("250.00"));
        executePaymentWorkflow(bank, new BigDecimal("1500.00"));
        executePaymentWorkflow(wallet, new BigDecimal("75.50"));
    }
}

/*
Output:

--- Java 17 Sealed Classes & Exhaustive Matching Demo ---

Executing Payment via CreditCard:
   -> Processing Credit Card payment of Rs. 250.00
   - Calculated Transaction Fee: Rs. 6.2500

Executing Payment via BankTransfer:
   -> Processing Bank Transfer of Rs. 1500.00
   - Calculated Transaction Fee: 0

Executing Payment via DigitalWallet:
   -> Processing Digital Wallet (PayPal) payment of Rs. 75.50
   - Calculated Transaction Fee: Rs. 0.7550

*/