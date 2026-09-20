package SOLID.DIP;

public class MongoDBDatabase implements Database{

    @Override
    public void save(String user) {
        System.out.println("Mango store data: "+ user);
    }
}
