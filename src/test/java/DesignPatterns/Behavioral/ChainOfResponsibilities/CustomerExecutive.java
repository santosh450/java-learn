package DesignPatterns.Behavioral.ChainOfResponsibilities;

public class CustomerExecutive implements SupportHandler {

    private SupportHandler handler;

    public CustomerExecutive(SupportHandler handler) {
        this.handler = handler;
    }

    @Override
    public void handleRequest(Issue issue) {
        if(issue.getPriority().equals("Level 2")){
            //executive logic
            System.out.println("executive issue resolved");
            issue.setStatus("SUCCESS");
        }else {
            System.out.println("Customer couldn't resolved. Moving to next handler");
            issue.setStatus("Pending");
            handler.handleRequest(issue );
        }
    }
}
