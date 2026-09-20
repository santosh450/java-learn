package DesignPatterns.Structural.Proxy.Protection;

public class Main {
    public static void main(String[] args) {
        Database db = new DatabaseProxy("Admin");
        db.delete();
    }
}
