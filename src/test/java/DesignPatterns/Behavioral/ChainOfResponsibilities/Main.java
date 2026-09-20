package DesignPatterns.Behavioral.ChainOfResponsibilities;

public class Main {
    public static void main(String[] args) {
        Client client01 = new Client(new ChatBot(new CustomerExecutive(new TechTeam())));
        client01.raiseIssue(new Issue("Level3", "New"));

        Client client02 = new Client(new CustomerExecutive(new TechTeam()));
    }

}
