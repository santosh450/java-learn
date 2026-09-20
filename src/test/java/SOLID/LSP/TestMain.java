package SOLID.LSP;

import java.util.List;

public class TestMain {
    public static void main(String[] args) {

        PaymentClient paymentClient = new PaymentClient();
        List<Payment> payments = List.of(new UPI(), new CreditCard());
        payments.forEach(paymentClient::processTransaction);
        List<NonRefundablePayments> nonRefundablePayments = List.of(new Crypto());
        nonRefundablePayments.forEach(paymentClient::processTransaction);
    }
}
