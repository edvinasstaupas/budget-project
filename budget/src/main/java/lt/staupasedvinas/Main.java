package lt.staupasedvinas;

import java.util.Locale;
import java.util.Scanner;

import static java.lang.System.exit;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    static Budget budget = new Budget();

    public static void main(String[] args) {

        String commands = "1 add income\n" +
                "2 add expense\n" +
                "3 print budget\n" +
                "0 exit the program\n";

        while (true) {
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
                case "0":
                    exit(0);
                    break;
                default:
                    println("Bad input.");
                    break;
            }
        }
    }

    /**
     * Method to add IncomeRecord to Budget object.
     */

    static void addIncome() {
        double sum = getSum();
        println("Enter category index:");
        int index = scanner.nextInt();
        println("Enter if the money is in the bank account (yes/no):");
        boolean moneyIsIn = false;
        boolean noGo = true;
        while(noGo) {
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

    /**
     * Method to add ExpenseRecord to Budget object.
     */

    static void addExpense() {
        double sum = getSum();
        println("Enter category index:");
        int index = scanner.nextInt();
        println("Enter card number:");
        int cardNumber = scanner.nextInt();
        println("Enter additional info (if you want to leave it blank, press enter):");
        scanner.nextLine(); //needed to skip \n character
        String info = scanner.nextLine();
        budget.addExpenseRecord(sum, index, cardNumber, info);
    }

    /**
     * Method to print Budget object's information.
     */

    static void printBudget() {
        double sumIncome = 0;
        println("Incomes:");
        IncomeRecord[] incomeRecord = budget.getIncomeRecord();
        for (int i = 0; i < budget.getIncomeCounter(); i++) {
            println(incomeRecord[i].toString());
            sumIncome += incomeRecord[i].getSum();
        }
        println("-------------------------------------------------------------------------------------------------------------------\nExpenses:");
        double sumExpense = 0;
        ExpenseRecord[] expenseRecord = budget.getExpenseRecord();
        for (int i = 0; i < budget.getExpenseCounter(); i++) {
            println(expenseRecord[i].toString());
            sumExpense += expenseRecord[i].getSum();
        }
        println("-------------------------------------------------------------------------------------------------------------------");
        println("Total incomes: " + Record.df.format(sumIncome));
        println("Total expenses: " + Record.df.format(sumExpense));
        println("Total balance: " + Record.df.format((sumIncome - sumExpense)));
    }

    /**
     * Method that gets sum
     * @return sum as double
     */

    static double getSum() {
        println("Enter the sum:");
        double sum = 0;
        boolean badInput = true;
        while (badInput) {
            String sumString = scanner.next();
            sumString = sumString.replaceAll(",", ".");
            try {
                sum = Double.parseDouble(sumString);
            } catch (NumberFormatException e) {
                println("Bad input. Enter valid sum: ");
            }
            finally {
                if (sum != 0)
                    badInput = false;
            }
        }
        return sum;
    }

    static void println (String string) {
        System.out.println(string);
    }
}


















