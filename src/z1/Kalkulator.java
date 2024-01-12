package z1;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Kalkulator {
    public void oblicz(String inFile, String outFile) throws FileNotFoundException {
        try {
            Scanner scanner = new Scanner(new java.io.File(inFile));
            PrintWriter writer = new PrintWriter(outFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains("+")) {
                    String[] numbers = line.split("\\+");
                    int a = Integer.parseInt(numbers[0].trim());
                    int b = Integer.parseInt(numbers[1].trim());
                    writer.println(a + b);
                } else if (line.contains("-")) {
                    String[] numbers = line.split("-");
                    int a = Integer.parseInt(numbers[0].trim());
                    int b = Integer.parseInt(numbers[1].trim());
                    writer.println(a - b);
                } else if (line.contains("*")) {
                    String[] numbers = line.split("\\*");
                    int a = Integer.parseInt(numbers[0].trim());
                    int b = Integer.parseInt(numbers[1].trim());
                    writer.println(a * b);
                } else if (line.contains("/")) {
                    String[] numbers = line.split("/");
                    int a = Integer.parseInt(numbers[0].trim());
                    int b = Integer.parseInt(numbers[1].trim());
                    try {
                        writer.println(a / b);
                    } catch (java.lang.ArithmeticException e) {
                        writer.println("Dzielenie przez zero");
                    }
                } else {
                    writer.println("Nieznane dzialanie");
                }
            }
            scanner.close();
            writer.close();
        } catch (java.io.FileNotFoundException e) {
            try (PrintWriter writer = new PrintWriter(outFile)) {
                writer.println("Nie znaleziono pliku");
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        }
    }
}
