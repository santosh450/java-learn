package SOLID.DIP;

public class MySQLDatabase implements Database{

    @Override
    public void save(String user) {
        System.out.println("Mysql save data: "+ user);
    }
}
