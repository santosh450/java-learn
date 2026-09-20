package DesignPatterns.Structural.Proxy.Virtual;

public class Movies implements Video{

    private String fileName;

    public Movies(String fileName) {
        this.fileName = fileName;
        loadVideo();
    }

    private void loadVideo() {
        System.out.println("loading video from server");
    }


    @Override
    public void play() {
        System.out.println("Video is playing: "+fileName);
    }
}
