package DesignPatterns.Behavioral.Strategy;

public class Main {
    public static void main(String[] args) {
        Notification email = new Email(new AESEncryption(), new ZIPCompression());
        email.encrypt();
        email.compress();

        Notification sms = new Sms(new AESEncryption(), new GZIPCompression());
        sms.encrypt();
        sms.compress();
    }
}
