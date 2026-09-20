package DesignPatterns.Behavioral.Strategy;

public class Email extends Notification{

    public Email(EncryptionStrategy encryptionStrategy, CompressionStrategy compressionStrategy) {
        super(encryptionStrategy, compressionStrategy);
    }

    @Override
    public void send() {
        System.out.println("sending email");
    }

////    @Override
////    public void encrypt() {
////        System.out.println("AES encryption");
////    }
////
////    @Override
////    public void compress() {
////        System.out.println("ZIP compression");
//    }
}
