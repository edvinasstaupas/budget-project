package lt.staupasedvinas.services;

import java.util.Scanner;

public class printService {
    public static Scanner scanner = new Scanner(System.in);
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void println(String string) {
        System.out.println(string);
    }

    public static void printlnErr(String string) {
        System.out.println(ANSI_RED + string + ANSI_RESET);
    }
}
