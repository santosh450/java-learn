package DesignPatterns.Behavioral.Strategy;

public class GZIPCompression implements CompressionStrategy{
    @Override
    public void compress() {
        System.out.println("GZIP Compression");
    }
}
