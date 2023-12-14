package hu.czmarkob;

import java.net.URISyntaxException;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws URISyntaxException {
        CardResolver cardResolver = new CardResolver(
                Paths.get(ClassLoader.getSystemResource("input.txt").toURI()));
        cardResolver.resolve();
    }
}