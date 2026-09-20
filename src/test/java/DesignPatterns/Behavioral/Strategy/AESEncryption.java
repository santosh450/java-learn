package DesignPatterns.Behavioral.Strategy;

public class AESEncryption implements EncryptionStrategy{
    @Override
    public void encrypt() {
        System.out.println("AES encryption");
    }
}
