package lt.staupasedvinas.services.inputservice;

import lt.staupasedvinas.domain.Budget;
import lt.staupasedvinas.services.outputservice.PrintService;
import lt.staupasedvinas.services.outputservice.PrintServiceImpl;
import lt.staupasedvinas.utils.BadInputException;

import java.io.File;
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
        return returnInt();
    }

    private int returnInt() {
        int number = 0;
        while (true) {
            try {
                return Integer.parseInt(getString());
            } catch (NumberFormatException e) {
                printlnBadInput();
            }
        }
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
            String string = getString();
            if (string.toLowerCase(Locale.ROOT).equals("yes")) {
                return true;
            } else if (string.toLowerCase(Locale.ROOT).equals("no")) {
                return false;
            } else {
                printlnBadInput();
            }
        }
    }

    @Override
    public int getIndexInput() {
        println("Enter category index:");
        return returnInt();
    }

    @Override
    public double getSum() {
        println("Enter the sum:");
        while (true) {
            String sumString = getString();
            sumString = sumString.replaceAll(",", ".");
            try {
                return Double.parseDouble(sumString);
            } catch (NumberFormatException e) {
                printlnBadInput();
            }
        }
    }

    @Override
    public LocalDateTime getDate() {
        LocalDateTime date = null;
        boolean badDate = true;
        while (badDate) {
            try {
                date = LocalDateTime.of(getIntForDate("year"), getIntForDate("month"), getIntForDate("day"),
                        getIntForDate("hours"), getIntForDate("minutes"), getIntForDate("seconds"));
                badDate = false;
            } catch (DateTimeException dateTimeException) {
                printlnBadInput();
            }
        }
        return date;
    }

    @Override
    public int getIndex() {
        println("Enter what index you want to delete:");
        int index = returnInt();
        while (index > budget.getIndexCounter()) {
            printlnErr("Bad input, index is too big. Bigest index can be " + (budget.getIndexCounter() - 1));
            index = returnInt();
        }
        return index;
    }

    private String getFileName() throws BadInputException {
        println("Enter file name: ");
        String name = getString();
        if (!name.endsWith(".csv"))
            throw new BadInputException();
        return name;
    }

    @Override
    public File getFile() {
        while (true) {
            try {
                String name = getFileName();
                return new File("src/main/resources/" + name);
                //return new File(name);
            } catch (BadInputException badInputException) {
                printlnBadInput();
            }
        }
    }

    private String getString() {
        return scanner.next();
    }

    private int getIntForDate(String string) {
        println("Enter " + string);
        return returnInt();
    }

    @Override
    public void println(String string) {
        printService.println(string);
    }

    @Override
    public void printlnErr(String string) {
        printService.printlnErr(string);
    }

    @Override
    public void printlnBadInput() {
        printService.printlnBadInput();
    }
}
