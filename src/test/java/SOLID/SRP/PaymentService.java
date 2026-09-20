// One class with different services

package SOLID.SRP;

public class PaymentService {

    public void makePayment(String paymentType){
        if(paymentType.equals("UPI")){
            System.out.println("UPI payment made.");
        } else if (paymentType.equals("CC")) {
            System.out.println("CC Payment made.");
        }
    }
}
