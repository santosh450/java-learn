package DesignPatterns.Structural.Adapter;

public class CreditCardPayment implements PaymentProcessor{
    @Override
    public String pay(double amount) {
        System.out.println("Credit card payment: " + amount);
        return "SUCCESS";
    }
}
