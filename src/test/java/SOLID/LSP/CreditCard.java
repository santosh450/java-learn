package SOLID.LSP;

public class CreditCard implements Payment {
    @Override
    public void pay() {
        System.out.println("CreditCard - pay");
    }

    @Override
    public void refund() {
        System.out.println("CreditCard - refund");
    }
}
