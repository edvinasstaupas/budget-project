package lt.staupasedvinas.services.outputservice;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class PrintServiceImpl implements PrintService {
    public static Scanner scanner = new Scanner(System.in);
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static DecimalFormat decimalFormat = new DecimalFormat("#.##");
    public static DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void println(String string) {
        System.out.println(string);
    }

    @Override
    public void printlnErr(String string) {
        System.out.println(PrintServiceImpl.ANSI_RED + string + PrintServiceImpl.ANSI_RESET);
    }

    @Override
    public void printlnBadInput() {
        printlnErr("Bad input. Try again.");
    }
}
