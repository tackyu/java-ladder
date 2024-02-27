package utils;

import java.util.Scanner;

public class Console {

    private static Scanner scanner;

    private Console() {
    }

    public static String readLine() {
        return getInstance().nextLine();
    }

    private static Scanner getInstance() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner;
    }

    public static void close() {
        if (scanner != null) {
            scanner.close();
            scanner = null;
        }
    }
}
