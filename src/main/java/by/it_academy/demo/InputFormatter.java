package by.it_academy.demo;

import java.text.DecimalFormat;
import java.util.Scanner;

public class InputFormatter {
    public static double formatInputToDecimal(Scanner scanner) {
        DecimalFormat patternFormat = new DecimalFormat("0.000");
        String result = patternFormat.format(scanner.nextDouble());
        return Double.parseDouble(result);
    }
}
