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


    public static void main(String[] args) {
        Image image = new Image();

        image.loadImage("C:\\Users\\joann\\Desktop\\test.jpg");
        image.saveImage("C:\\Users\\joann\\Desktop\\test2.jpg", "jpg");
    }
}
