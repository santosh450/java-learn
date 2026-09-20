package DesignPatterns.Behavioral.Observer;

import java.util.ArrayList;
import java.util.List;

public class YoutubeChannel {
    private
    List<YoutubeSubscriber> subscriberList = new ArrayList<>();

    public void subscribe(YoutubeSubscriber subscriber){
        subscriberList.add(subscriber);
    }

    public void notifySubscribers(){
        for(YoutubeSubscriber subscriber: subscriberList){
            subscriber.update();
        }
    }

    public void uploadVideo(){
        System.out.println("Video uploaded");
        notifySubscribers();
    }

}
