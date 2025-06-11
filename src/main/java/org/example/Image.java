package org.example;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image {
    private BufferedImage image;

    public void loadImage(String path){
        try {
            // Ścieżka do pliku z obrazem
            File file = new File(path);

            // Wczytanie obrazu do BufferedImage
            image = ImageIO.read(file);

            // Sprawdzenie czy obraz został wczytany poprawnie
            if (image != null) {
                System.out.println("Obraz został wczytany poprawnie.");
            } else {
                System.out.println("Nie udało się wczytać obrazu.");
            }
        } catch (IOException e) {
            System.err.println("Błąd podczas wczytywania obrazu: " + e.getMessage());
        }

    }

    public void saveImage(String path, String format){
            try {
                File plikWyjsciowy = new File(path);
                ImageIO.write(image, format, plikWyjsciowy);
                System.out.println("Obraz zapisano do pliku: " + path);
            } catch (IOException e) {
                System.err.println("Błąd podczas zapisywania obrazu: " + e.getMessage());
            }
        }

    public void chagneBrihtness(int value){
        int szerokosc = image.getWidth();
        int wysokosc = image.getHeight();

        for (int i = 0; i < szerokosc; i++) {
            for (int j = 0; j < wysokosc; j++) {
                int rgb = image.getRGB(i, j);
                int czerwony = (rgb >> 16) & 0xFF;
                int zielony = (rgb >> 8) & 0xFF;
                int niebieski = rgb & 0xFF;

                czerwony = Math.min(255, czerwony + value);
                zielony = Math.min(255, zielony + value);
                niebieski = Math.min(255, niebieski + value);

                int nowyRgb = (czerwony << 16) | (zielony << 8) | niebieski;
                image.setRGB(i, j, nowyRgb);
            }

        }
    }



    public static void main(String[] args) {
        Image image = new Image();

        image.loadImage("C:\\Users\\joann\\Desktop\\test.jpg");
        image.chagneBrihtness(128);
        image.saveImage("C:\\Users\\joann\\Desktop\\test2.jpg", "jpg");

    }
}
