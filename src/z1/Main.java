package z1;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Kalkulator kalkulator = new Kalkulator();
        kalkulator.oblicz("src/z1/dane.txt", "src/z1/wynik.txt");
    }
}
