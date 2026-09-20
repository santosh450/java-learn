package DesignPatterns.Behavioral.ChainOfResponsibilities;

public class Client {

    private SupportHandler handler;

    public Client(SupportHandler handler) {
        this.handler = handler;
    }

    public void raiseIssue(Issue issue){
        handler.handleRequest(issue);
    }
}
