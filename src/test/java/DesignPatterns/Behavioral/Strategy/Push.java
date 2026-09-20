package DesignPatterns.Behavioral.Strategy;

public class Push extends Notification{

    public Push(EncryptionStrategy encryptionStrategy, CompressionStrategy compressionStrategy) {
        super(encryptionStrategy, compressionStrategy);
    }

    @Override
    public void send() {
        System.out.println("Push Notification");
    }

//    @Override
//    public void encrypt() {
//        System.out.println("RSA encryption");
//    }
//
//    @Override
//    public void compress() {
//        System.out.println("ZIP compression");
//    }
}
