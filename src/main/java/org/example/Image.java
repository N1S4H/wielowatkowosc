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
        long timeStart = System.currentTimeMillis();
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
        long endTime = System.currentTimeMillis();
        System.out.println("Czas trwania: " + (endTime - timeStart));
    }

    public void chagneBrihtness2(int value){
        long timeStart = System.currentTimeMillis();
        int szerokosc = image.getWidth();
        int wysokosc = image.getHeight();
        int liczbaRdzeni = Runtime.getRuntime().availableProcessors();
//        int liczbaRdzeni = 1;
        System.out.println("liczbaRdzeni " + liczbaRdzeni);

        int iloscWierszyNaWatek = wysokosc/liczbaRdzeni;
        System.out.println("iloscWierszyNaWatek " + iloscWierszyNaWatek);

        for(int w = 0; w<liczbaRdzeni; w++) {
         int poczatkowyWiersz = w * iloscWierszyNaWatek;
            System.out.println("poczatkowyWiersz " + poczatkowyWiersz);

            new Thread({

                odpalWatekRozjasniania(image, iloscWierszyNaWatek, szerokosc, value, poczatkowyWiersz);

            }).start();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Czas trwania: " + (endTime - timeStart));
    }

    private void odpalWatekRozjasniania(BufferedImage image, long iloscWierszyNaWatek, int szerokosc, int value, int poczatek) {
        System.out.println("zaczynam od " + poczatek);
        for(int i = 0; i<szerokosc; i++){
            for(int j = poczatek; j<poczatek + iloscWierszyNaWatek; j++){
                System.out.println(poczatek +"  - i  " + i + " j " + j);
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
       // image.chagneBrihtness(128);
        image.chagneBrihtness2(128);
        image.saveImage("C:\\Users\\joann\\Desktop\\test3.jpg", "jpg");

    }
}
