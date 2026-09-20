package SOLID.LSP;

public interface Payment extends NonRefundablePayments {
    void refund();
}
