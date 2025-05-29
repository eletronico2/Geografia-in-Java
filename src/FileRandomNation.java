package frameContinenti;

import java.util.*;
import java.io.*;

class FileRandomNation {
    public String imageFolder = "src/images/flags/";
    
    public Nation getRandomNation() throws IOException {
        File file = new File("src/file/europenation.txt");
        if (!file.exists()) {
            throw new IOException("Il file 'europenation.txt' non esiste.");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            // Verifica che il file non sia vuoto
            if (lines.isEmpty()) {
                throw new IOException("Il file è vuoto.");
            }

            // Genera un indice casuale
            Random random = new Random();
            int randomIndex = random.nextInt(lines.size());

            // Ottiene la riga casuale
            String randomLine = lines.get(randomIndex);

            // Divide la riga in nome della nazione e nome del file
            String[] parts = randomLine.split(";");
            if (parts.length != 2) {
                throw new IOException("Formato del file non valido.");
            }

            // Crea e restituisce un oggetto Nation
            return new Nation(parts[0], parts[1]);
        }
    }

    public Set<String> getAllNations() throws IOException {
        File file = new File("src/file/europenation.txt");
        if (!file.exists()) {
            throw new IOException("Il file 'europenation.txt' non esiste.");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            Set<String> nations = new HashSet<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 2) {
                    nations.add(parts[0]);
                } else {
                    System.err.println("Riga malformata: " + line);
                }
            }
            return nations;
        }
    }

    public class Nation {
        private String pngFile;
        private String nationName;

        public Nation(String nationName, String pngFile) {
            this.pngFile = pngFile;
            this.nationName = nationName;
        }

        public String getPngFile() {
            return pngFile;
        }

        public String getNameNation() {
            return nationName;
        }
    }

    public String getImageFolder() {
        return imageFolder;
    }
}
