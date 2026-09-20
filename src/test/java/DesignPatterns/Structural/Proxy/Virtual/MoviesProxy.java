package DesignPatterns.Structural.Proxy.Virtual;

public class MoviesProxy implements Video{

    private String fileName;
    private Video video;

    public MoviesProxy(String fileName) {
        this.fileName = fileName;
        loadVideo();
    }

    private void loadVideo() {
        System.out.println("loading video from server");
    }


    @Override
    public void play() {
        System.out.println("Video is playing: "+fileName);
        if(video == null) {
            video = new Movies(fileName);
        }
        video.play();
    }
}
