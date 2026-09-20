package DesignPatterns.Structural.Proxy.Protection;

public class DatabaseProxy implements Database{
    private Database db;
    private String role;

    public DatabaseProxy(String role){
        this.db = new MySqlDatabase();
        this.role = role;
    }
    @Override
    public void delete() {
        if(role.equals("Admin"))
            System.out.println("User deleted");
        else
            System.out.println("Access denied");
    }
}
