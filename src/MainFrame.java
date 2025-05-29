import frameContinenti.*;
import javax.swing.*;
import java.awt.*;// POint
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MainFrame extends JFrame {
    private BufferedImage immagine;
    private final int originalImageWidth = 1800; // Larghezza originale dell'immagine
    private final int originalImageHeight = 900; // Altezza originale dell'immagine

    public MainFrame() {
        // Configura il JFrame
        setTitle("Geografia");// titolo della finestra
        setSize(800, 600);// dimensione finestra in pixel 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// chiusura finestra
        setExtendedState(JFrame.MAXIMIZED_BOTH);// apre in schermo intero 
        setLocationRelativeTo(null);

        // Carica l'immagine
        try {
            immagine = ImageIO.read(new File("src/images/mondo.jpg"));// legge l immagine
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Image Failed");
        }

        // Crea un pannello personalizzato per visualizzare l'immagine
        JPanel pannelloImmagine = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (immagine != null) {
                    g.drawImage(immagine, 0, 0, getWidth(), getHeight(), this);
                }

                // Calcola il fattore di scala
                double xScale = getWidth() / (double) originalImageWidth;
                double yScale = getHeight() / (double) originalImageHeight;

                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(Color.RED);
                g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, new float[]{10.0f}, 0.0f));

                //drawHitbox(g2d, ContinentPolygons.getEuropePolygon(), xScale, yScale); // Europa
                //drawHitbox(g2d, ContinentPolygons.getAsiaPolygon(), xScale, yScale); // Asia
                //drawHitbox(g2d, ContinentPolygons.getAfricaPolygon(), xScale, yScale); // Africa
                //drawHitbox(g2d, ContinentPolygons.getAmericaPolygon(), xScale, yScale); // America
                //drawHitbox(g2d, ContinentPolygons.getOceaniaPolygon(), xScale, yScale); // Oceania
            }

            private void drawHitbox(Graphics2D g2d, Polygon polygon, double xScale, double yScale) {
                Polygon scaledPolygon = new Polygon();
                for (int i = 0; i < polygon.npoints; i++) {
                    scaledPolygon.addPoint(
                            (int) (polygon.xpoints[i] * xScale),
                            (int) (polygon.ypoints[i] * yScale)
                    );
                }
                g2d.draw(scaledPolygon);
            }
        };

        // Aggiungi il MouseAdapter per gestire i click del mouse
        pannelloImmagine.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point clickPoint = e.getPoint();// e è l evento e genererà un POint
                String continent = getContinentAt(clickPoint);
                if (continent != null) {
                    openContinentFrame(continent);
                    System.out.println("Hai Premuto su " + continent);
                }
            }
        });

        // Aggiungi il pannello al frame
        add(pannelloImmagine);
    }
    
    private String getContinentAt(Point point) {
        double xScale = getWidth() / (double) originalImageWidth;// scala l immaggine in base alla macchina
        double yScale = getHeight() / (double) originalImageHeight;

        Point scaledPoint = new Point((int) (point.x / xScale),(int) (point.y / yScale));
        
        if(ContinentPolygons.getAmericaNordPolygon().contains(scaledPoint)){
            return "America del nord";
        }
        if (ContinentPolygons.getEuropePolygon().contains(scaledPoint)) {// se le cooordinate dell europa sono = a quelle cliccate dal mouse
            return "Europa";
        }
        
        // Asia
        if (ContinentPolygons.getAsiaPolygon().contains(scaledPoint)) {
            return "Asia";
        }

        // Africa
        if (ContinentPolygons.getAfricaPolygon().contains(scaledPoint)) {
            return "Africa";
        }

        // America
        if (ContinentPolygons.getAmericaPolygon().contains(scaledPoint)) {
            return "America";
        }

        // Oceania
        if (ContinentPolygons.getOceaniaPolygon().contains(scaledPoint)) {
            return "Oceania";
        }

        return null; // Nessun continente trovato alla posizione del click
    }

    private void openContinentFrame(String continent) {
        JFrame continentFrame = null;

        switch (continent) {
            case "Europa":
                continentFrame = new EuropaFrame(this);
                break;
            // Aggiungi altri case per gli altri continenti con le rispettive classi frame
            case "Asia":
                JOptionPane.showMessageDialog(this, "Asia ancora non implementata", "Informazione", JOptionPane.INFORMATION_MESSAGE);
                // continentFrame = new AsiaFrame(this);
                break;
            case "Africa":
                JOptionPane.showMessageDialog(this, "Africa ancora non implementata", "Informazione", JOptionPane.INFORMATION_MESSAGE);
                // continentFrame = new AfricaFrame(this);
                break;
            case "America": 
                continentFrame = new AmericaFrame(this);
                break;
            case "America del nord":
                JOptionPane.showMessageDialog(this, "America del Nord ancora non implementata", "Informazione", JOptionPane.INFORMATION_MESSAGE);
                // continentFrame = new AmericaFrame(this);
                break;
            case "Oceania":
                JOptionPane.showMessageDialog(this, "Oceania ancora non implementata", "Informazione", JOptionPane.INFORMATION_MESSAGE);
                // continentFrame = new OceaniaFrame(this);
                break;
        }

        if (continentFrame != null) {
            continentFrame.setVisible(true);
            setVisible(false);
        }
    }
}
