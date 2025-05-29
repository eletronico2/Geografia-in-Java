package frameContinenti;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FunzioniFrame {
    public static BufferedImage caricaImmagine(String percorso) {
        BufferedImage immagine = null;
        try {
            immagine = ImageIO.read(new File(percorso));
            System.out.println("Immagine del continente caricata correttamente");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Caricamento immagine del continente fallito");
        }
        return immagine;
    }

    public static void disegnaImmagine(Graphics g, BufferedImage immagine, int panelWidth, int panelHeight) {
        if (immagine != null) {
            int imgWidth = immagine.getWidth();
            int imgHeight = immagine.getHeight();
            double imgAspect = (double) imgWidth / imgHeight;

            double panelAspect = (double) panelWidth / panelHeight;

            int drawWidth, drawHeight;

            if (imgAspect > panelAspect) {
                drawWidth = panelWidth;
                drawHeight = (int) (panelWidth / imgAspect);
            } else {
                drawHeight = panelHeight;
                drawWidth = (int) (panelHeight * imgAspect);
            }

            g.drawImage(immagine, 0, 0, panelWidth, panelHeight, null);
            System.out.println("Immagine del continente disegnata nel pannello");
        }
    }

    public static void disegnaBandiera(Graphics g, ImageIcon bandieraIcon, int panelWidth, int panelHeight) {
        if (bandieraIcon != null) {
            int imgWidth = bandieraIcon.getIconWidth();
            int imgHeight = bandieraIcon.getIconHeight();
            double imgAspect = (double) imgWidth / imgHeight;

            double panelAspect = (double) panelWidth / panelHeight;

            int drawWidth, drawHeight;

            if (imgAspect > panelAspect) {
                drawWidth = panelWidth;
                drawHeight = (int) (panelWidth / imgAspect);
            } else {
                drawHeight = panelHeight;
                drawWidth = (int) (panelHeight * imgAspect);
            }

            g.drawImage(bandieraIcon.getImage(), 0, 0, panelWidth, panelHeight, null);
            // System.out.println("Bandiera disegnata nel pannello");
        }
    }

    public static void adjustFontSize(Graphics2D g2, JLabel label, JPanel panel) {
        Font font = panel.getFont();
        String text = label.getText();
        int panelWidth = panel.getWidth();
        int panelHeight = panel.getHeight();

        int fontSize = 25; // Font size iniziale
        FontMetrics fm = g2.getFontMetrics(new Font(font.getName(), font.getStyle(), fontSize));
        while ((fm.stringWidth(text) > panelWidth || fm.getHeight() > panelHeight) && fontSize > 10) {
            fontSize--;
            fm = g2.getFontMetrics(new Font(font.getName(), font.getStyle(), fontSize));
        }

        label.setFont(new Font(font.getName(), font.getStyle(), fontSize));
    }
    
    public static void aggiornaIncorretto(JLabel incorrectLabel, int incorrectGuesses) {
        incorrectLabel.setText("Errate: " + incorrectGuesses);
    }
    
    public static void aggiornaCorretto(JLabel correctLabel, int correctGuesses) {
        correctLabel.setText("Corrette: " + correctGuesses);
    }
        
    // Esempio di implementazione del metodo FunzioniFrame.impostaPulsanteNazione
    public static void impostaPulsanteNazione(JButton button, String nazioneNome, String randomNationNome) {
        button.setText(nazioneNome);  // Imposta il testo del pulsante con il nome della nazione
        // Altre impostazioni come icona, colore, ecc. possono essere aggiunte qui
    }

   
    
}
