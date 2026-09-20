package DesignPatterns.Behavioral.Strategy;

public class Sms extends Notification{

    public Sms(EncryptionStrategy encryptionStrategy, CompressionStrategy compressionStrategy) {
        super(encryptionStrategy, compressionStrategy);
    }

    @Override
    public void send() {
        System.out.println("Notification sms");
    }

//    @Override
//    public void encrypt() {
//        System.out.println("AES encryption");
//    }
//
//    @Override
//    public void compress() {
//        System.out.println("GZIP compression");
//    }
}
