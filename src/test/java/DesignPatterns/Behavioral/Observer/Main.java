package DesignPatterns.Behavioral.Observer;

public class Main {
    public static void main(String[] args) {
        YoutubeSubscriber subscriber1 = new YoutubeSubscriber("Navam", 1);
        YoutubeSubscriber subscriber2 = new YoutubeSubscriber("Bhavan", 2);
        YoutubeSubscriber subscriber3 = new YoutubeSubscriber("Ikls", 3);
        YoutubeSubscriber subscriber4 = new YoutubeSubscriber("Lopp", 4);

        YoutubeChannel youtubeChannel = new YoutubeChannel();
        youtubeChannel.subscribe(subscriber1);
        youtubeChannel.subscribe(subscriber2);
        youtubeChannel.subscribe(subscriber3);
        youtubeChannel.subscribe(subscriber4);

        youtubeChannel.uploadVideo();
    }
}
