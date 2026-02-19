/*
@Author: Dynamic Duo Learners

REAL WORLD EXAMPLE:
E-commerce checkout with multiple payment methods.

The checkout process is the same, but the payment algorithm can vary:
- Credit Card
- UPI
- Wallet

WITHOUT STRATEGY PATTERN:
CheckoutService directly contains logic for every payment type using
if/else or switch conditions.

Problems:
- Too many conditional blocks.
- Adding a new payment method requires changing CheckoutService.
- Payment algorithms become tightly coupled with checkout logic.
- Individual payment methods are harder to maintain and test.

MAIN IDEA:
"Define interchangeable algorithms behind a common interface and
 choose the required algorithm at runtime."

WITH STRATEGY:
Each algorithm becomes a separate interchangeable object.

Client
  |
  v
CheckoutService (Context)
  |
  v
PaymentStrategy
  |
  +--> CreditCardPayment
  +--> UpiPayment
  +--> WalletPayment
*/

public class StrategyPatternDemo {

    public static void main(String[] args) {
        System.out.println("====== CHECKOUT USING STRATEGY PATTERN ======\n");

        CheckoutService checkout = new CheckoutService();

        checkout.setPaymentStrategy(
                new CreditCardPayment("4111-XXXX-XXXX-1111"));
        checkout.checkout(2500.00);

        System.out.println();

        checkout.setPaymentStrategy(
                new UpiPayment("9075259303@icici"));
        checkout.checkout(419.00);

        System.out.println();

        checkout.setPaymentStrategy(
                new WalletPayment("WALLET-101"));
        checkout.checkout(1904.00);
    }
}

// 1. STRATEGY
interface PaymentStrategy {
    void pay(double amount);
}

// 2. CONCRETE STRATEGY
class CreditCardPayment implements PaymentStrategy {
    private final String maskedCardNumber;

    public CreditCardPayment(String maskedCardNumber) {
        this.maskedCardNumber = maskedCardNumber;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Credit Card Strategy: Paid Rs. " + amount
                + " using " + maskedCardNumber);
    }
}

// 3. CONCRETE STRATEGY
class UpiPayment implements PaymentStrategy {
    private final String upiId;

    public UpiPayment(String upiId) {
        this.upiId = upiId;
    }

    @Override
    public void pay(double amount) {
        System.out.println("UPI Strategy: Paid Rs. " + amount
                + " using " + upiId);
    }
}

// 4. CONCRETE STRATEGY
class WalletPayment implements PaymentStrategy {
    private final String walletId;

    public WalletPayment(String walletId) {
        this.walletId = walletId;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Wallet Strategy: Paid Rs. " + amount
                + " using wallet " + walletId);
    }
}

// 5. CONTEXT
class CheckoutService {
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void checkout(double amount) {
        if (paymentStrategy == null) {
            throw new IllegalStateException("Payment strategy is not selected.");
        }

        System.out.println("Checkout: Processing order amount Rs. " + amount);
        paymentStrategy.pay(amount);
    }
}

/*
Output:

====== CHECKOUT USING STRATEGY PATTERN ======

Checkout: Processing order amount Rs. 2500.0
Credit Card Strategy: Paid Rs. 2500.0 using 4111-XXXX-XXXX-1111

Checkout: Processing order amount Rs. 419.0
UPI Strategy: Paid Rs. 419.0 using 9075259303@icici

Checkout: Processing order amount Rs. 1904.0
Wallet Strategy: Paid Rs. 1904.0 using wallet WALLET-101

*/
