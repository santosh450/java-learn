package DesignPatterns.Behavioral.ChainOfResponsibilities;

public class TechTeam implements SupportHandler {
    @Override
    public void handleRequest(Issue issue) {
        if(issue.getPriority().equals("Level 3")){
            //tech team logic
            System.out.println("tech team issue resolved");
            issue.setStatus("SUCCESS");
        }else {
            System.out.println("Issue couldn't be resolved.");
            issue.setStatus("Failed");
        }
    }
}
