package DesignPatterns.Structural.Adapter;

public class PaymentClient {

//    CreditCardPayment payment = new CreditCardPayment();
    //or
//    PaymentProcessor payment = new CreditCardPayment();
    //or
//    PaymentProcessor payment = new PayPalAdapter(new PayPalGateway());
    //or
    PaymentProcessor payment = new RazoPayAdapter(new RazoPayGateway());

    public void makePayment(){
        String result = payment.pay(1000);
        if(result.equals("SUCCESS")){
            System.out.println("Payment done successfully");
        }else {
            System.out.println("Payment failed");
        }
    }
}
