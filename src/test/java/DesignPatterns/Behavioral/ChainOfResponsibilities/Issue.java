package DesignPatterns.Behavioral.ChainOfResponsibilities;

public class Issue {
    private String priority;
    private String status;

    public Issue(String priority, String status) {
        this.priority = priority;
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public Issue setPriority(String priority) {
        this.priority = priority;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Issue setStatus(String status) {
        this.status = status;
        return this;
    }
}
