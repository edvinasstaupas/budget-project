package lt.staupasedvinas.services;

import lt.staupasedvinas.Budget;
import lt.staupasedvinas.records.ExpenseRecord;
import lt.staupasedvinas.records.IncomeRecord;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import static java.lang.System.exit;
import static lt.staupasedvinas.services.printService.*;

public class ManagingService {

    static DecimalFormat df = new DecimalFormat("#.##");
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
                case "0":
                    exit(0);
                    break;
                default:
                    println("Bad input.");
                    break;
            }
        }
    }

    private void deleteRecord() {
        boolean go = false;
        boolean removed;
        println("Do you want to delete income or expense record. Write i or r:");
        String string = "";
        while (!go) {
            string = scanner.next();
            if (string.equals("i")) {
                go = true;
            } else if (string.equals("r")) {
                go = true;
            } else {
                printlnErr("Bad input! Enter valid choice of record.");
            }
        }

        println("Enter what index you want to delete:");
        int index = scanner.nextInt();

        if (string.equals("i")) {
            removed = removeIncomeRecord(index);
        } else {
            removed = removeExpenseRecord(index);
        }
        if (!removed) {
            printlnErr("Something gone wrong, your request was not fulfilled. It is likely that you entered wrong index, check that and try again");
        }
    }

    public boolean removeIncomeRecord(int index) {
        return budget.removeIncomeRecord(index);
    }

    public boolean removeExpenseRecord(int index) {
        return budget.removeExpenseRecord(index);
    }

    public void printBalance() {
        println("Total balance: " + df.format(budget.balance()));
        println("");
    }

    public void printBudget() {
        println("Incomes:");
        List<IncomeRecord> incomeRecords = budget.getIncomeRecords();
        for (IncomeRecord incomeRecord : incomeRecords) {
            println(String.format("Index = %-5dsum = %-5s category index = %-5d date = %s is money in bank account = %-5b additional info = %s",

                    incomeRecord.getIndex(), df.format(incomeRecord.getSum()),
                    incomeRecord.getCategoryIndex(), dateTimeFormatter.format(incomeRecord.getDate()),
                    incomeRecord.getIsMoneyInBankAccount(), incomeRecord.getAdditionalInfo()));
        }
        println("-------------------------------------------------------------------------------------------------------------------\nExpenses:");
        List<ExpenseRecord> expenseRecords = budget.getExpenseRecords();
        for (ExpenseRecord expenseRecord : expenseRecords) {
            println(String.format("Index = %-5d sum = %-5s category index = %-5d date = %s card number = %-8d additional info = %s",
                    expenseRecord.getIndex(), df.format(expenseRecord.getSum()),
                    expenseRecord.getCategoryIndex(), dateTimeFormatter.format(expenseRecord.getDate()),
                    expenseRecord.getCardNumber(), expenseRecord.getAdditionalInfo()));
        }
        println("-------------------------------------------------------------------------------------------------------------------");
        println("Total incomes: " + df.format(budget.getListBalance(budget.getIncomeRecords())));
        println("Total expenses: " + df.format(budget.getListBalance(budget.getExpenseRecords())));
        printBalance();
        println("");
    }

    public void addExpense() {
        double sum = getSum();
        println("Enter category index:");
        int index = scanner.nextInt();
        println("Enter card number:");
        int cardNumber = scanner.nextInt();
        println("Enter additional info (if you want to leave it blank, press enter):");
        scanner.nextLine(); //needed to skip \n character
        String info = scanner.nextLine();
        println(info);
        budget.addExpenseRecord(sum, index, cardNumber, info);
    }

    public void addIncome() {
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
        budget.addIncomeRecord(sum, index, moneyIsIn, info);
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
