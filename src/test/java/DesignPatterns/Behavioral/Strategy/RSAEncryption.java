package DesignPatterns.Behavioral.Strategy;

public class RSAEncryption implements EncryptionStrategy{
    @Override
    public void encrypt() {
        System.out.println("RAS encryption");
    }
}
