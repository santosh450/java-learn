package DesignPatterns.Behavioral.Observer;

public class YoutubeSubscriber {
    private String name;
    private int id;

    public YoutubeSubscriber(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public void update(){
        System.out.println("Upload video receive notification");
    }
}
