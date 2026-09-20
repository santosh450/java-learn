package DesignPatterns.Structural.Adapter;

public class PayPalAdapter implements PaymentProcessor {

    private final PayPalGateway payPalGateway;

    public PayPalAdapter(PayPalGateway payPalGateway) {
        this.payPalGateway = payPalGateway;
    }

    @Override
    public String pay(double amount) {

        int res = payPalGateway.makePayment("INR", amount);
        if(res == 1){
            return "SUCESS";
        }
        return "FAIL";
    }
}
