package DesignPatterns.Structural.Adapter;

public class PayPalGateway {

    public int makePayment(String currency, double value) {

        System.out.println("PayPal payment: " + value + " " + currency);
        return 1;
    }
}
