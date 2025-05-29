package frameContinenti;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class EuropaFrame extends JFrame{
    private BufferedImage immagine;
    private JFrame parentFrame;

    private FileRandomNation fileRandomNation = new FileRandomNation();
    private FileRandomNation.Nation randomNation = null;
    private ImageIcon bandieraIcon = null;

    private JPanel bandieraPanel;
    private JPanel nomeNazionePanel;
    private JLabel nomeNazione;

    private NazioneControllo nazioneControllo;

    private int incorrectGuesses = 0;
    private JLabel incorrectLabel;
    private int correctGuesses = 0;
    private JLabel correctLabel;
    
    private Thread timerThread;
    private int timeInSeconds = 0;
    private JLabel timerLabel;

    private JButton btnItalia, btnFrancia, btnGermania, btnSpagna, btnRegnoUnito, btnPortogallo;
    private JButton btnPaesiBassi, btnBelgio, btnRussia, btnUcraina, btnIrlanda;
    private JButton btnAlbania, btnAndorra, btnAustria, btnBielorussia, btnBosnia;
    private JButton btnBulgaria, btnVaticano, btnCroazia, btnDanimarca, btnEstonia;
    private JButton btnFinlandia, btnGrecia, btnIslanda, btnKosovo, btnLettonia;
    private JButton btnLiechtenstein, btnLituania, btnLussemburgo, btnMacedonia, btnMalta;
    private JButton btnMoldavia, btnMonaco, btnMontenegro, btnNorvegia, btnPolonia;
    private JButton btnRepubblicaCeca, btnRomania, btnSanMarino, btnSerbia, btnSlovacchia;
    private JButton btnSlovenia, btnSvezia, btnSvizzera, btnUngheria;

    public EuropaFrame(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        setTitle("Europa");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Imposta FULL SCREEN
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setResizable(false);
        setLocationRelativeTo(null);

        immagine = FunzioniFrame.caricaImmagine("src/images/europa.png");
        
        Set<String> tutteLeNazioni = new HashSet<>();
        try {
            tutteLeNazioni = fileRandomNation.getAllNations();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Errore nel caricamento delle nazioni");
        }
        nazioneControllo = new NazioneControllo(tutteLeNazioni);
        
        JPanel pannelloImmagine = creaPannelloImmagine();
        JPanel menuPanel = creaMenuPanel();
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pannelloImmagine, menuPanel);
        splitPane.setDividerSize(0);
        splitPane.setResizeWeight(0.80);
        splitPane.setEnabled(false);
        
        startTimer();

        add(splitPane);
        generaNuovaNazione();
        setVisible(true);
    }

    private JPanel creaPannelloImmagine() {
        JPanel pannelloImmagine = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                FunzioniFrame.disegnaImmagine(g, immagine, getWidth(), getHeight());

                int width = getWidth();
                int height = getHeight();

                btnItalia.setBounds((int) (width * 0.56), (int) (height * 0.70), 15, 15);
                btnFrancia.setBounds((int) (width * 0.37), (int) (height * 0.60), 15, 15);
                btnGermania.setBounds((int) (width * 0.50), (int) (height * 0.50), 15, 15);
                btnSpagna.setBounds((int) (width * 0.25), (int) (height * 0.73), 15, 15);
                btnRegnoUnito.setBounds((int) (width * 0.32), (int) (height * 0.45), 15, 15);
                btnPortogallo.setBounds((int) (width * 0.165), (int) (height * 0.74), 14, 14);
                btnPaesiBassi.setBounds((int) (width * 0.43), (int) (height * 0.47), 13, 13);
                btnBelgio.setBounds((int) (width * 0.418), (int) (height * 0.51), 13, 13);
                btnRussia.setBounds((int) (width * 0.90), (int) (height * 0.20), 20, 20);
                btnUcraina.setBounds((int) (width * 0.80), (int) (height * 0.48), 15, 15);
                btnIrlanda.setBounds((int) (width * 0.24), (int) (height * 0.42), 13, 13);
                btnAlbania.setBounds((int) (width * 0.69), (int) (height * 0.72), 13, 13);
                btnAndorra.setBounds((int) (width * 0.33), (int) (height * 0.69), 13, 13);
                btnAustria.setBounds((int) (width * 0.575), (int) (height * 0.575), 15, 15);
                btnBielorussia.setBounds((int) (width * 0.75), (int) (height * 0.40), 15, 15);
                btnBosnia.setBounds((int) (width * 0.65), (int) (height * 0.66), 14, 14);
                btnBulgaria.setBounds((int) (width * 0.765), (int) (height * 0.665), 15, 15);
                btnVaticano.setBounds((int) (width * 0.53), (int) (height * 0.70), 10, 10);
                btnCroazia.setBounds((int) (width * 0.61), (int) (height * 0.62), 13, 13);
                btnDanimarca.setBounds((int) (width * 0.48), (int) (height * 0.375), 13, 13);
                btnEstonia.setBounds((int) (width * 0.69), (int) (height * 0.29), 13, 13);
                btnFinlandia.setBounds((int) (width * 0.67), (int) (height * 0.20), 18, 18);
                btnGrecia.setBounds((int) (width * 0.72), (int) (height * 0.74), 14, 14);
                btnIslanda.setBounds((int) (width * 0.20), (int) (height * 0.13), 15, 15);
                btnKosovo.setBounds((int) (width * 0.70), (int) (height * 0.685), 10, 10);
                btnLettonia.setBounds((int) (width * 0.70), (int) (height * 0.328), 14, 14);
                btnLiechtenstein.setBounds((int) (width * 0.50), (int) (height * 0.595), 10, 10);
                btnLituania.setBounds((int) (width * 0.69), (int) (height * 0.37), 15, 15);
                btnLussemburgo.setBounds((int) (width * 0.44), (int) (height * 0.54), 10, 10);
                btnMacedonia.setBounds((int) (width * 0.715), (int) (height * 0.70), 13, 13);
                btnMalta.setBounds((int) (width * 0.59), (int) (height * 0.85), 10, 10);
                btnMoldavia.setBounds((int) (width * 0.80), (int) (height * 0.54), 13, 13);
                btnMonaco.setBounds((int) (width * 0.457), (int) (height * 0.66), 12, 12);
                btnMontenegro.setBounds((int) (width * 0.68), (int) (height * 0.695), 10, 10);
                btnNorvegia.setBounds((int) (width * 0.47), (int) (height * 0.26), 15, 15);
                btnPolonia.setBounds((int) (width * 0.65), (int) (height * 0.46), 15, 15);
                btnRepubblicaCeca.setBounds((int) (width * 0.575), (int) (height * 0.521), 15, 15);
                btnRomania.setBounds((int) (width * 0.75), (int) (height * 0.59), 15, 15);
                btnSanMarino.setBounds((int) (width * 0.545), (int) (height * 0.665), 10, 10);
                btnSerbia.setBounds((int) (width * 0.685), (int) (height * 0.645), 15, 15);
                btnSlovacchia.setBounds((int) (width * 0.65), (int) (height * 0.535), 14, 14);
                btnSlovenia.setBounds((int) (width * 0.585), (int) (height * 0.61), 13, 13);
                btnSvezia.setBounds((int) (width * 0.55), (int) (height * 0.20), 18, 18);
                btnSvizzera.setBounds((int) (width * 0.47), (int) (height * 0.595), 13, 13);
                btnUngheria.setBounds((int) (width * 0.65), (int) (height * 0.58), 15, 15);

            }
        };
        pannelloImmagine.setPreferredSize(new Dimension(800, 600));

        btnItalia = new JButton("");
        btnFrancia = new JButton("");
        btnGermania = new JButton("");
        btnSpagna = new JButton("");
        btnRegnoUnito = new JButton("");
        btnPortogallo = new JButton("");
        btnPaesiBassi = new JButton("");
        btnBelgio = new JButton("");
        btnRussia = new JButton("");
        btnUcraina = new JButton("");
        btnIrlanda = new JButton("");
        btnAlbania = new JButton("");
        btnAndorra = new JButton("");
        btnAustria = new JButton("");
        btnBielorussia = new JButton("");
        btnBosnia = new JButton("");
        btnBulgaria = new JButton("");
        btnVaticano = new JButton("");
        btnCroazia = new JButton("");
        btnDanimarca = new JButton("");
        btnEstonia = new JButton("");
        btnFinlandia = new JButton("");
        btnGrecia = new JButton("");
        btnIslanda = new JButton("");
        btnKosovo = new JButton("");
        btnLettonia = new JButton("");
        btnLiechtenstein = new JButton("");
        btnLituania = new JButton("");
        btnLussemburgo = new JButton("");
        btnMacedonia = new JButton("");
        btnMalta = new JButton("");
        btnMoldavia = new JButton("");
        btnMonaco = new JButton("");
        btnMontenegro = new JButton("");
        btnNorvegia = new JButton("");
        btnPolonia = new JButton("");
        btnRepubblicaCeca = new JButton("");
        btnRomania = new JButton("");
        btnSanMarino = new JButton("");
        btnSerbia = new JButton("");
        btnSlovacchia = new JButton("");
        btnSlovenia = new JButton("");
        btnSvezia = new JButton("");
        btnSvizzera = new JButton("");
        btnUngheria = new JButton("");
        
        for (JButton button : new JButton[]{
            btnItalia, btnFrancia, btnGermania, btnSpagna, btnRegnoUnito, btnPortogallo,
            btnPaesiBassi, btnBelgio, btnRussia, btnUcraina, btnIrlanda, btnAlbania, btnAndorra, 
            btnAustria, btnBielorussia, btnBosnia, btnBulgaria, btnVaticano, btnCroazia,
            btnDanimarca, btnEstonia, btnFinlandia, btnGrecia, btnIslanda, btnKosovo, btnLettonia,
            btnLiechtenstein, btnLituania, btnLussemburgo, btnMacedonia, btnMalta, btnMoldavia, 
            btnMonaco, btnMontenegro, btnNorvegia, btnPolonia, btnRepubblicaCeca, btnRomania,
            btnSanMarino, btnSerbia, btnSlovacchia, btnSlovenia, btnSvezia, btnSvizzera, btnUngheria}) {
            
            pannelloImmagine.add(button);
        }
        
        FunzioniFrame.impostaPulsanteNazione(btnItalia, "Italia", randomNation != null ? randomNation.getNameNation() : "");
        FunzioniFrame.impostaPulsanteNazione(btnFrancia, "Francia", randomNation != null ? randomNation.getNameNation() : "");
        FunzioniFrame.impostaPulsanteNazione(btnGermania, "Germania", randomNation != null ? randomNation.getNameNation() : "");
        FunzioniFrame.impostaPulsanteNazione(btnSpagna, "Spagna", randomNation != null ? randomNation.getNameNation() : "");
        FunzioniFrame.impostaPulsanteNazione(btnRegnoUnito, "Regno Unito", randomNation != null ? randomNation.getNameNation() : "");
        FunzioniFrame.impostaPulsanteNazione(btnPortogallo, "Portogallo", randomNation != null ? randomNation.getNameNation() : "");
        FunzioniFrame.impostaPulsanteNazione(btnPaesiBassi, "Paesi Bassi", randomNation != null ? randomNation.getNameNation() : "");
        FunzioniFrame.impostaPulsanteNazione(btnBelgio, "Belgio", randomNation != null ? randomNation.getNameNation() : "");
        FunzioniFrame.impostaPulsanteNazione(btnRussia, "Russia", randomNation != null ? randomNation.getNameNation() : "");
        FunzioniFrame.impostaPulsanteNazione(btnUcraina, "Ucraina", randomNation != null ? randomNation.getNameNation() : "");
        FunzioniFrame.impostaPulsanteNazione(btnIrlanda, "Irlanda", randomNation != null ? randomNation.getNameNation() : "");
        FunzioniFrame.impostaPulsanteNazione(btnAlbania, "Albania", randomNation != null ? randomNation.getNameNation() : "");
        FunzioniFrame.impostaPulsanteNazione(btnAndorra, "Andorra", randomNation != null ? randomNation.getNameNation() : "");
        FunzioniFrame.impostaPulsanteNazione(btnAustria, "Austria", randomNation != null ? randomNation.getNameNation() : "");
        FunzioniFrame.impostaPulsanteNazione(btnBielorussia, "Bielorussia", randomNation != null ? randomNation.getNameNation() : "");
        FunzioniFrame.impostaPulsanteNazione(btnBosnia, "Bosnia ed Erzegovina", randomNation != null ? randomNation.getNameNation() : "");
        FunzioniFrame.impostaPulsanteNazione(btnBulgaria, "Bulgaria", randomNation != null ? randomNation.getNameNation() : "");
        FunzioniFrame.impostaPulsanteNazione(btnVaticano, "Città del Vaticano", randomNation != null ? randomNation.getNameNation() : "");
        FunzioniFrame.impostaPulsanteNazione(btnCroazia, "Croazia", randomNation != null ? randomNation.getNameNation() : "");
        FunzioniFrame.impostaPulsanteNazione(btnDanimarca, "Danimarca", randomNation != null ? randomNation.getNameNation() : "");
        FunzioniFrame.impostaPulsanteNazione(btnEstonia, "Estonia", randomNation != null ? randomNation.getNameNation() : "");
        FunzioniFrame.impostaPulsanteNazione(btnFinlandia, "Finlandia", randomNation != null ? randomNation.getNameNation() : "");
        FunzioniFrame.impostaPulsanteNazione(btnGrecia, "Grecia", randomNation != null ? randomNation.getNameNation() : "");
        FunzioniFrame.impostaPulsanteNazione(btnIslanda, "Islanda", randomNation != null ? randomNation.getNameNation() : "");
        FunzioniFrame.impostaPulsanteNazione(btnKosovo, "Kosovo", randomNation != null ? randomNation.getNameNation() : "");
        FunzioniFrame.impostaPulsanteNazione(btnLettonia, "Lettonia", randomNation != null ? randomNation.getNameNation() : "");
        FunzioniFrame.impostaPulsanteNazione(btnLiechtenstein, "Liechtenstein", randomNation != null ? randomNation.getNameNation() : "");
        FunzioniFrame.impostaPulsanteNazione(btnLituania, "Lituania", randomNation != null ? randomNation.getNameNation() : "");
        FunzioniFrame.impostaPulsanteNazione(btnLussemburgo, "Lussemburgo", randomNation != null ? randomNation.getNameNation() : "");
        FunzioniFrame.impostaPulsanteNazione(btnMacedonia, "Macedonia Del Nord", randomNation != null ? randomNation.getNameNation() : "");
        FunzioniFrame.impostaPulsanteNazione(btnMalta, "Malta", randomNation != null ? randomNation.getNameNation() : "");
        FunzioniFrame.impostaPulsanteNazione(btnMoldavia, "Moldavia", randomNation != null ? randomNation.getNameNation() : "");
        FunzioniFrame.impostaPulsanteNazione(btnMonaco, "Monaco", randomNation != null ? randomNation.getNameNation() : "");
        FunzioniFrame.impostaPulsanteNazione(btnMontenegro, "Montenegro", randomNation != null ? randomNation.getNameNation() : "");
        FunzioniFrame.impostaPulsanteNazione(btnNorvegia, "Norvegia", randomNation != null ? randomNation.getNameNation() : "");
        FunzioniFrame.impostaPulsanteNazione(btnPolonia, "Polonia", randomNation != null ? randomNation.getNameNation() : "");
        FunzioniFrame.impostaPulsanteNazione(btnRepubblicaCeca, "Repubblica Ceca", randomNation != null ? randomNation.getNameNation() : "");
        FunzioniFrame.impostaPulsanteNazione(btnRomania, "Romania", randomNation != null ? randomNation.getNameNation() : "");
        FunzioniFrame.impostaPulsanteNazione(btnSanMarino, "San Marino", randomNation != null ? randomNation.getNameNation() : "");
        FunzioniFrame.impostaPulsanteNazione(btnSerbia, "Serbia", randomNation != null ? randomNation.getNameNation() : "");
        FunzioniFrame.impostaPulsanteNazione(btnSlovacchia, "Slovacchia", randomNation != null ? randomNation.getNameNation() : "");
        FunzioniFrame.impostaPulsanteNazione(btnSlovenia, "Slovenia", randomNation != null ? randomNation.getNameNation() : "");
        FunzioniFrame.impostaPulsanteNazione(btnSvezia, "Svezia", randomNation != null ? randomNation.getNameNation() : "");
        FunzioniFrame.impostaPulsanteNazione(btnSvizzera, "Svizzera", randomNation != null ? randomNation.getNameNation() : "");
        FunzioniFrame.impostaPulsanteNazione(btnUngheria, "Ungheria", randomNation != null ? randomNation.getNameNation() : "");


        return pannelloImmagine;
    }

    private JPanel creaMenuPanel() {
        JPanel menuPanel = new JPanel(new GridBagLayout());
        menuPanel.setPreferredSize(new Dimension(200, getHeight()));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.NORTH;

        bandieraPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Ombreggiatura
                Graphics2D g2d = (Graphics2D) g;
                int shadowOffset = 5;
                int shadowSize = 10;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(0, 0, 0, 50)); // Colore nero con trasparenza per l'ombreggiatura
                g2d.fillRoundRect(shadowOffset, getHeight() - shadowSize, getWidth() - shadowOffset * 2, shadowSize, 10, 10);

                // Disegna l'immagine della bandiera
                if (bandieraIcon != null) {
                    FunzioniFrame.disegnaBandiera(g, bandieraIcon, getWidth(), getHeight());
                }
            }
        };

        bandieraPanel.setPreferredSize(new Dimension(150, 100));
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;
        gbc.fill = GridBagConstraints.BOTH;
        menuPanel.add(bandieraPanel, gbc);

        JButton nextButton = new JButton("CAMBIA BANDIERA");
        nextButton.addActionListener(e -> {
                generaNuovaNazione();
                incrementaErrato();
                // Continuare
        });
        nextButton.setBackground(Color.BLUE);
        nextButton.setForeground(Color.WHITE);
        nextButton.setFont(new Font("Arial", Font.BOLD, 14));
        nextButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 0));
        nextButton.setPreferredSize(new Dimension(200, 30));
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        menuPanel.add(nextButton, gbc);

        nomeNazionePanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                FunzioniFrame.adjustFontSize((Graphics2D) g, nomeNazione, this);
            }
        };
        nomeNazione = new JLabel("NomeNazione", JLabel.CENTER);
        nomeNazione.setForeground(Color.BLACK);
        nomeNazionePanel.add(nomeNazione, BorderLayout.CENTER);
        nomeNazionePanel.setPreferredSize(new Dimension(200, 50));
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;
        gbc.fill = GridBagConstraints.BOTH;
        menuPanel.add(nomeNazionePanel, gbc);

        incorrectLabel = new JLabel("Errate: " + incorrectGuesses, JLabel.CENTER);
        incorrectLabel.setFont(new Font("Arial", Font.BOLD, 28));  // Aumentato il font e reso grassetto
        incorrectLabel.setForeground(Color.RED);  // Colore rosso per maggiore visibilità
        gbc.weightx = 1.0;
        gbc.weighty = 0.005;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menuPanel.add(incorrectLabel, gbc);

        gbc.anchor = GridBagConstraints.SOUTH;
        
        correctLabel = new JLabel("Corrette: " + correctGuesses, JLabel.CENTER);
        correctLabel.setFont(new Font("Arial", Font.BOLD, 28));  // Aumentato il font e reso grassetto
        correctLabel.setForeground(new Color(0, 77, 0));  // Colore rosso per maggiore visibilità
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menuPanel.add(correctLabel, gbc);
        
        timerLabel = new JLabel("00:00", SwingConstants.CENTER);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 30));
        timerLabel.setForeground(Color.BLUE); 
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        menuPanel.add(timerLabel, gbc);
        
        
        JButton backButton = new JButton("ESCI");
        backButton.addActionListener(e -> {
            parentFrame.setVisible(true);
            dispose();
        });
        backButton.setBackground(Color.BLUE);
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 12));
        backButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        backButton.setPreferredSize(new Dimension(100, 30));
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.SOUTH;
        menuPanel.add(backButton, gbc);

        return menuPanel;
    }

    private void randomNat() {
        try {
            randomNation = fileRandomNation.getRandomNation();
            if (randomNation != null) {
                bandieraIcon = new ImageIcon(fileRandomNation.imageFolder + randomNation.getPngFile());
                System.out.println("Nazione generata: " + randomNation.getNameNation()); // Stampa la nazione generata
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private boolean isGeneratingNewNation = false; // Flag per impedire la generazione multipla

    private void generaNuovaNazione() {
        // Se la generazione di una nuova nazione è già in corso, non fare nulla
        if (isGeneratingNewNation) return;

        isGeneratingNewNation = true; // Imposta il flag a true per evitare nuove generazioni

        // Verifica se tutte le nazioni sono state mostrate
        if (nazioneControllo.tutteNazioniMostrate()) {
            // Supponendo che tu abbia una variabile `numeroErrori` che conta gli errori
            stopTimer();
            JOptionPane.showMessageDialog(
                this, 
                "Congratulazioni! Hai indovinato tutte le nazioni Europee con: " + incorrectGuesses + " errori.\n"+
                "Tempo passato : "+timeInSeconds+" Secondi"
            );
            isGeneratingNewNation = false; // Reset flag
            
            return;
        }

        // Seleziona una nuova nazione casuale che non è stata già mostrata
        String newNation;
        do {
            randomNat(); // Suppongo che questa funzione generi una nazione casuale
            newNation = randomNation.getNameNation(); // Ottieni il nome della nazione generata
        } while (nazioneControllo.isNazioneMostrata(newNation)); // Continua finché non trovi una nuova nazione

        // Aggiungi la nuova nazione alla lista di quelle mostrate
        nazioneControllo.aggiungiNazioneMostrata(newNation);

        // Imposta il nome della nazione nel campo
        nomeNazione.setText(
            "<html>" +
                "<h2 style='text-align: center; margin-bottom: 0px'>Seleziona la nazione:</h2>" +
                "<h1><center><br><span style='font-size:30px; font-weight:bold; color:#1a001a;'>" + newNation + "</span></br></center></h1>" +
            "</html>"
        );

        // Aggiorna lo stato dei pulsanti
        for (JButton button : new JButton[]{
                btnItalia, btnFrancia, btnGermania, btnSpagna, btnRegnoUnito, btnPortogallo,
                btnPaesiBassi, btnBelgio, btnRussia, btnUcraina, btnIrlanda, btnAlbania, btnAndorra,
                btnAustria, btnBielorussia, btnBosnia, btnBulgaria, btnVaticano, btnCroazia,
                btnDanimarca, btnEstonia, btnFinlandia, btnGrecia, btnIslanda, btnKosovo, btnLettonia,
                btnLiechtenstein, btnLituania, btnLussemburgo, btnMacedonia, btnMalta, btnMoldavia,
                btnMonaco, btnMontenegro, btnNorvegia, btnPolonia, btnRepubblicaCeca, btnRomania,
                btnSanMarino, btnSerbia, btnSlovacchia, btnSlovenia, btnSvezia, btnSvizzera, btnUngheria
        }) {
            String buttonNationName = button.getText();

            // Imposta solo il comportamento per i pulsanti che sono ancora abilitati
            if (button.isEnabled()) {
                // Resetta il colore e riabilita i pulsanti che non sono stati disabilitati
                button.setBackground(null);
                button.setEnabled(true);    // Riabilita il pulsante
                impostaColorazionePulsante(button, buttonNationName, newNation);
            }
        }

        bandieraPanel.repaint(); // Ridisegna il pannello della bandiera
        isGeneratingNewNation = false; // Reset flag
    }


    // Funzione di normalizzazione dei nomi delle nazioni
    private String normalizzaStringa(String str) {
        // Rimuove spazi extra e converte in minuscolo
        String normalized = str.trim().toLowerCase();

        // Normalizzazione degli accenti (opzionale, aggiungi altri accenti se necessario)
        normalized = normalized.replace("é", "e")
                                 .replace("è", "e")
                                 .replace("à", "a")
                                 .replace("ù", "u")
                                 .replace("ò", "o")
                                 .replace("ç", "c");

        return normalized;
    }


    private void impostaColorazionePulsante(JButton button, String nazioneNome, String randomNationNome) {
        // Rimuovi tutti gli ActionListener esistenti
        for (ActionListener al : button.getActionListeners()) {
            button.removeActionListener(al);
        }

        // Aggiungi un nuovo ActionListener
        button.addActionListener(e -> {
            // Normalizza i nomi (minuscolo e senza spazi extra)
            String normalizedNazioneNome = nazioneNome.trim().toLowerCase();
            String normalizedRandomNationNome = randomNationNome.trim().toLowerCase();

            System.out.println("Pulsante premuto: " + nazioneNome);
            if (normalizedNazioneNome.equals(normalizedRandomNationNome)) {
                // Risposta corretta
                button.setBackground(Color.GREEN);
                button.setEnabled(false);  // Rendi disabilitato il pulsante
                incrementaCorretto();
                generaNuovaNazione();     // Genera una nuova nazione
            } else {
                // Risposta errata
                button.setBackground(Color.RED);
                button.setEnabled(false);  // Disabilita il pulsante errato
                incrementaErrato();        // Incrementa il contatore degli errori

                JOptionPane.showMessageDialog(this, "Errato! questa nazione è: " + nazioneNome);
                

                // Riabilita il pulsante dopo un breve intervallo
                Timer timer = new Timer(500, evt -> {
                    button.setBackground(null);  // Reset colore
                    button.setEnabled(true);     // Riabilita il pulsante errato
                });
                timer.setRepeats(false);
                timer.start();
            }
        });
    }

    private void incrementaErrato() {
        incorrectGuesses++;
        // JOptionPane.showMessageDialog(this, "Incorrect. Try again.");
        System.out.println("Errate: " + correctGuesses);
        FunzioniFrame.aggiornaIncorretto(incorrectLabel, incorrectGuesses);
    }
    
    private void incrementaCorretto() {
        correctGuesses++;
        // JOptionPane.showMessageDialog(this, "Incorrect. Try again.");
        System.out.println("Corrette: " + correctGuesses);
        FunzioniFrame.aggiornaCorretto(correctLabel, correctGuesses);
    }
    
    private volatile boolean running = false;
    
    private void startTimer() {
        if (timerThread == null || !timerThread.isAlive()) {
            running = true; // Avvia il timer
            timerThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (running) {
                        try {
                            // Incrementa il tempo ogni secondo
                            timeInSeconds++;

                            // Calcola minuti e secondi
                            int minutes = timeInSeconds / 60;
                            int seconds = timeInSeconds % 60;

                            // Formatta il tempo come "mm:ss"
                            String timeString = String.format("%02d:%02d", minutes, seconds);

                            // Aggiorna l'etichetta del timer nella GUI
                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    timerLabel.setText(timeString);
                                }
                            });

                            // Pausa di 1 secondo
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            // Se il thread è interrotto, esce dal ciclo
                            if (!running) {
                                Thread.currentThread().interrupt(); // Imposta il flag di interruzione
                                break; // Esce dal ciclo while
                            }
                        }
                    }
                }
            });
            timerThread.start(); // Avvia il thread
        }
    }
    
    private void stopTimer() {
        running = false; // Ferma il timer
        if (timerThread != null && timerThread.isAlive()) {
            timerThread.interrupt(); // Interrompe il thread se è in pausa
            try {
                timerThread.join(); // Aspetta che il thread finisca
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
