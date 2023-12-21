package z2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;

class Drukarka {
    private PriorityQueue<Integer> kolejka = new PriorityQueue<>((a, b) -> Integer.compare(b, a));

    public void start(String inputFile, String outputFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {

            String line;
            while ((line = br.readLine()) != null) {
                processLine(line);
            }

            while (!kolejka.isEmpty()) {
                int element = kolejka.poll();
                bw.write(Integer.toString(element));
                bw.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processLine(String line) {
        switch (line) {
            case "drukuj":
                if (!kolejka.isEmpty()) {
                    int highestPriority = kolejka.poll();
                    writeToFile(highestPriority);
                } else {
                    writeToFile("brak");
                }
                break;
            case "koniec":
                while (!kolejka.isEmpty()) {
                    int element = kolejka.poll();
                    writeToFile(element);
                }
                break;
            default:
                if (!line.trim().isEmpty() && line.matches("\\{\\d+\\}")) {
                    try {
                        int priority = Integer.parseInt(line.substring(1, line.length() - 1));
                        kolejka.add(priority);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
        }
    }


    private void writeToFile(int value) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/z2/wynik.txt", true));
            bw.write(Integer.toString(value));
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeToFile(String value) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/z2/wynik.txt", true));
            bw.write(value);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
