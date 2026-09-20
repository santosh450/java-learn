package SOLID.LSP;

public class Crypto implements NonRefundablePayments {
    @Override
    public void pay() {
        System.out.println("Crypo - pay");
    }
}
