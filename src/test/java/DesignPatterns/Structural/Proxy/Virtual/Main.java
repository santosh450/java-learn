package DesignPatterns.Structural.Proxy.Virtual;

public class Main {
    public static void main(String[] args) {
        Video video1 = new MoviesProxy("Bhaubali.mp4");
        Video video2 = new MoviesProxy("Phuspa.mp4");
        Video video3 = new MoviesProxy("OG.p4");
    }
}
