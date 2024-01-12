package z2;

import java.io.*;
import java.util.PriorityQueue;

public class Drukarka {

    private PriorityQueue<Integer> kolejka = new PriorityQueue<>((a, b) -> Integer.compare(b, a));

    public void start(String inputFile, String outputFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {

            String line;
            while ((line = br.readLine()) != null) {
                try {
                    processLine(line);
                } catch (IllegalArgumentException e) {
                    System.err.println("Blad");
                }
            }

            while (!kolejka.isEmpty()) {
                int number = kolejka.poll();
                bw.write(Integer.toString(number));
                bw.newLine();
            }

        } catch (FileNotFoundException e) {
            try (PrintWriter writer = new PrintWriter(outputFile)) {
                writer.println("Nie znaleziono pliku");
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processLine(String line) {
        try {
            int number = Integer.parseInt(line);
            kolejka.add(number);
        } catch (NumberFormatException e) {
            if (line.equals("drukuj")) {
                if (!kolejka.isEmpty()) {
                    int highestPriorityNumber = kolejka.poll();
                    writeResultToFile(highestPriorityNumber);
                } else {
                    writeResultToFile("brak");
                }
            } else if (line.equals("koniec")) {
                while (!kolejka.isEmpty()) {
                    int num = kolejka.poll();
                    writeResultToFile(num);
                }
            } else {
                throw new IllegalArgumentException("blad");
            }
        }
    }

    private void writeResultToFile(int result) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/z2/wynik.txt", true))) {
            bw.write(Integer.toString(result));
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeResultToFile(String result) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/z2/wynik.txt", true))) {
            bw.write(result);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
