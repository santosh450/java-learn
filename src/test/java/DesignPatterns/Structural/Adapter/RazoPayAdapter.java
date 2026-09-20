package DesignPatterns.Structural.Adapter;

public class RazoPayAdapter implements PaymentProcessor {

    private final RazoPayGateway razoPayGateway;

    public RazoPayAdapter(RazoPayGateway razoPayGateway) {
        this.razoPayGateway = razoPayGateway;
    }

    @Override
    public String pay(double amount) {

        boolean res = razoPayGateway.makePayment("INR", (int)amount);
        if(res){
            return "SUCESS";
        }
        return "FAIL";
    }
}
