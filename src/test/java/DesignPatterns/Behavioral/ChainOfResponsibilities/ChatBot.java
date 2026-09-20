package DesignPatterns.Behavioral.ChainOfResponsibilities;

public class ChatBot implements SupportHandler {

    private SupportHandler handler;

    public ChatBot(SupportHandler handler) {
        this.handler = handler;
    }

    @Override
    public void handleRequest(Issue issue) {
        if(issue.getPriority().equals("Level 1")){
            //chatbot logic
            System.out.println("chatbot issue resolved");
            issue.setStatus("SUCCESS");
        }else{
            System.out.println("chatbot couldn't resolved. Moving to next handler");
            issue.setStatus("Pending");
            handler.handleRequest(issue);
        }

    }
}
