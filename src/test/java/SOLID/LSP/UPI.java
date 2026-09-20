package SOLID.LSP;

public class UPI implements Payment {
    @Override
    public void pay() {
        System.out.println("UPI - Pay");
    }

    @Override
    public void refund() {
        System.out.println("UPI - refund");
    }

    public void checkBalance(){
        System.out.println("UPI - balanceCheck");
    }
}
