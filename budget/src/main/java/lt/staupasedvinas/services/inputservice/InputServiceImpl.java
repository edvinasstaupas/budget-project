package lt.staupasedvinas.services.inputservice;

import lt.staupasedvinas.domain.Budget;
import lt.staupasedvinas.services.outputservice.PrintService;
import lt.staupasedvinas.services.outputservice.PrintServiceImpl;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.Locale;

import static lt.staupasedvinas.services.outputservice.PrintServiceImpl.*;

public class InputServiceImpl implements InputService, PrintService {

    private final Budget budget;

    private final PrintServiceImpl printService;

    public InputServiceImpl(Budget budget) {
        this.budget = budget;
        this.printService = new PrintServiceImpl();
    }

    @Override
    public int getCardNumber() {
        println("Enter card number:");
        return scanner.nextInt();
    }

    @Override
    public String getInfo() {
        println("Enter additional info (if you want to leave it blank, press enter):");
        scanner.nextLine(); //needed to skip \n character
        return scanner.nextLine();
    }

    @Override
    public boolean getMoneyIsIn() {
        println("Enter if the money is in the bank account (yes/no):");
        while (true) {
            String string = scanner.next();
            if (string.toLowerCase(Locale.ROOT).equals("yes")) {
                return true;
            } else if (string.toLowerCase(Locale.ROOT).equals("no")) {
                return false;
            } else {
                println("Bad input.");
            }
        }
    }

    @Override
    public int getIndexInput() {
        println("Enter category index:");
        return scanner.nextInt();
    }

    @Override
    public double getSum() {
        println("Enter the sum:");
        double sum = 0;
        boolean badInput = true;
        while (badInput) {
            String sumString = scanner.next();
            sumString = sumString.replaceAll(",", ".");
            try {
                sum = Double.parseDouble(sumString);
            } catch (NumberFormatException e) {
                printlnErr("Bad input. Enter valid sum: ");
            } finally {
                if (sum != 0)
                    badInput = false;
            }
        }
        return sum;
    }

    @Override
    public LocalDateTime getDate() {
        LocalDateTime date = null;
        boolean badDate = true;
        while (badDate) {
            try {
                date = LocalDateTime.of(getInt("year"), getInt("month"), getInt("day"), getInt("hours"), getInt("minutes"), getInt("seconds"));
                badDate = false;
            } catch (DateTimeException dateTimeException) {
                printlnErr("Bad input, try again!");
            }
        }
        return date;
    }

    @Override
    public int getIndex() {
        println("Enter what index you want to delete:");
        int index = scanner.nextInt();
        while (index > budget.getIndexCounter()) {
            printlnErr("Bad input, index is too big. Bigest index can be " + (budget.getIndexCounter() - 1));
            index = scanner.nextInt();
        }
        return index;
    }

    private int getInt(String string) {
        println("Enter " + string);
        return scanner.nextInt();
    }

    @Override
    public void println(String string) {
        printService.println(string);
    }

    @Override
    public void printlnErr(String string) {
        printService.printlnErr(string);
    }
}
