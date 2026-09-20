package DesignPatterns.Structural.Proxy.Protection;

public class MySqlDatabase implements Database{
    @Override
    public void delete() {
        System.out.println("User deleted");
    }
}
