package SOLID.ISP;

public class DevOpsEngineer implements Deployer{
    @Override
    public void deployApp() {
        System.out.println("DevOps can deploy app.");
    }
}
