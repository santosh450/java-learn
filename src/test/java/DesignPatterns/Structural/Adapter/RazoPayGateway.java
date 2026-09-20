package DesignPatterns.Structural.Adapter;

public class RazoPayGateway {

    public boolean makePayment(String currency, int value) {

        System.out.println("Razo payment: " + value + " " + currency);
        return true;
    }
}
