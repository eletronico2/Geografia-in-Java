package frameContinenti;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;
import javax.swing.*;
import javax.swing.Timer;

public class NazioneControllo {
    private Set<String> nazioniMostrate;
    private Set<String> tutteLeNazioni;

    public NazioneControllo(Set<String> tutteLeNazioni) {
        this.tutteLeNazioni = tutteLeNazioni;
        this.nazioniMostrate = new HashSet<>();
    }

    public boolean isNazioneMostrata(String nazione) {
        return nazioniMostrate.contains(nazione);
    }

    public void aggiungiNazioneMostrata(String nazione) {
        nazioniMostrate.add(nazione);
    }

    public boolean tutteNazioniMostrate() {
        return nazioniMostrate.size() == tutteLeNazioni.size();
    }

    // Metodo per inizializzare il comportamento del pulsante
    public void inizializzaPulsante(JButton button, String nationToCheck, String randomNation) {
        // Aggiungi l'ActionListener solo una volta, altrimenti potresti avere listener duplicati
        button.addActionListener(createActionListener(button, nationToCheck, randomNation));
    }

    // Metodo per creare l'ActionListener
    private ActionListener createActionListener(JButton button, String nationToCheck, String randomNation) {
        return e -> {
            System.out.println("Checking nation: " + nationToCheck + " against " + randomNation);
            if (nationToCheck.equalsIgnoreCase(randomNation)) {
                // Se la nazione è corretta
                button.setBackground(Color.GREEN); // Colore verde per risposta corretta
                button.setEnabled(false); // Disabilita il pulsante dopo una risposta corretta
            } else {
                // Se la nazione è errata
                button.setBackground(Color.RED); // Colore rosso per risposta errata
                button.setEnabled(false); // Disabilita temporaneamente il pulsante

                // Crea un timer per riabilitare il pulsante e resettare il colore dopo 1 secondo
                Timer timer = new Timer(1500, evt -> {
                    button.setBackground(null); // Reset del colore del pulsante
                    button.setEnabled(true); // Riabilita il pulsante
                });
                timer.setRepeats(false); // Il timer si attiva solo una volta
                timer.start();
            }
        };
    }
}
