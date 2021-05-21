package lt.staupasedvinas.services;

import lt.staupasedvinas.Budget;
import lt.staupasedvinas.records.ExpenseRecord;
import lt.staupasedvinas.records.IncomeRecord;
import lt.staupasedvinas.records.Record;
import lt.staupasedvinas.records.RecordFactory;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static java.lang.System.exit;
import static lt.staupasedvinas.services.PrintService.*;

public class ManagingService {

    static DecimalFormat decimalFormat = new DecimalFormat("#.##");
    static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final Budget budget;

    public ManagingService(Budget budget) {
        this.budget = budget;
        init();
    }

    public void init() {
        while (true) {
            String commands = "\n1 add income\n" +
                    "2 add expense\n" +
                    "3 print budget\n" +
                    "4 print balance\n" +
                    "5 delete a record\n" +
                    "6 edit a record\n" +
                    "0 exit the program\n";
            println(commands);
            switch (scanner.next()) {
                case "1":
                    addIncome();
                    break;
                case "2":
                    addExpense();
                    break;
                case "3":
                    printBudget();
                    break;
                case "4":
                    printBalance();
                    break;
                case "5":
                    deleteRecord();
                    break;
                case "6":
                    editRecord();
                    break;
                case "0":
                    exit(0);
                default:
                    printlnErr("Bad input.");
                    break;
            }
        }
    }

    private void editRecord() {
        printRecords();
        int index = getIndex();
        println("Would you like to edit this record's sum? yes/no");
        switch (scanner.next()) {
            case "yes":
                budget.getRecord(index).setSum(getSum());
                break;
            case "no":
                println("Okay, moving on...");
                break;
            default:
                printlnErr("Bad input");
        }
        //TODO use this code for other variables
    }

    private int getIndex() {
        println("Enter what index you want to delete:");
        int index = scanner.nextInt();
        while (index > budget.getIndexCounter())
        {
            printlnErr("Bad input, index is too big. Bigest index can be " +  (budget.getIndexCounter() - 1));
            index = scanner.nextInt();
        }
        return index;
    }

    private void deleteRecord() {
        boolean removed;

        removed = removeRecord(getIndex());

        if (!removed) {
            printlnErr("Something gone wrong, your request was not fulfilled. It is likely that you entered wrong index, check that and try again");
        }
    }

    private boolean removeRecord(int index) {
        return budget.removeRecord(index);
    }

    @Deprecated
    public boolean removeIncomeRecord(int index) {
        return budget.removeIncomeRecord(index);
    }

    @Deprecated
    public boolean removeExpenseRecord(int index) {
        return budget.removeExpenseRecord(index);
    }

    private void printBalance() {
        println("Total balance: " + decimalFormat.format(budget.balance()));
        println("");
    }

    private void printBudget() {
        printRecords();
        println("------------------------------------------------------" +
                "-------------------------------------------------------------");
        println("Total incomes: " + decimalFormat.format(
                budget.getListBalance(budget.getRecords(), BudgetForms.INCOME)));
        println("Total expenses: " + decimalFormat.format(
                budget.getListBalance(budget.getRecords(), BudgetForms.EXPENSE)));
        printBalance();
        println("");
    }

    private void printRecords() {
        println("Incomes:");
        for (Record record : budget.getRecords()) {
            if (record instanceof IncomeRecord) {
                println(record.toString());
            }
        }
        println("------------------------------------------------------" +
                "-------------------------------------------------------------\nExpenses:");
        for (Record record : budget.getRecords()) {
            if (record instanceof ExpenseRecord) {
                println(record.toString());
            }
        }
    }

    private void addExpense() {
        double sum = getSum();
        println("Enter category index:");
        int index = scanner.nextInt();
        println("Enter card number:");
        int cardNumber = scanner.nextInt();
        println("Enter additional info (if you want to leave it blank, press enter):");
        scanner.nextLine(); //needed to skip \n character
        String info = scanner.nextLine();
        println(info);
        budget.addRecord(new RecordFactory().newExpenseRecord(sum, index, cardNumber, info, budget));
        //budget.addExpenseRecord(sum, index, cardNumber, info);
    }

    private void addIncome() {
        double sum = getSum();
        println("Enter category index:");
        int index = scanner.nextInt();
        println("Enter if the money is in the bank account (yes/no):");
        boolean moneyIsIn = false;
        boolean noGo = true;
        while (noGo) {
            String string = scanner.next();
            if (string.toLowerCase(Locale.ROOT).equals("yes")) {
                moneyIsIn = true;
                noGo = false;
            } else if (string.toLowerCase(Locale.ROOT).equals("no")) {
                noGo = false;
            } else {
                println("Bad input.");
            }
        }
        println("Enter additional info (if you want to leave it blank, press enter):");
        scanner.nextLine(); //needed to skip \n character
        String info = scanner.nextLine();
        budget.addRecord(new RecordFactory().newIncomeRecord(sum, index, moneyIsIn, info, budget));
        //budget.addIncomeRecord(sum, index, moneyIsIn, info);
    }

    private double getSum() {
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
}
