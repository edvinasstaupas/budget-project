package lt.staupasedvinas.services.managingservice;

import lt.staupasedvinas.domain.Budget;
import lt.staupasedvinas.domain.records.ExpenseRecord;
import lt.staupasedvinas.domain.records.IncomeRecord;
import lt.staupasedvinas.domain.records.Record;
import lt.staupasedvinas.domain.records.RecordFactory;
import lt.staupasedvinas.repository.RecordsFileReader;
import lt.staupasedvinas.repository.RecordsFileWriter;
import lt.staupasedvinas.services.inputservice.InputService;
import lt.staupasedvinas.services.inputservice.InputServiceImpl;
import lt.staupasedvinas.services.outputservice.PrintService;
import lt.staupasedvinas.services.outputservice.PrintServiceImpl;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

import static java.lang.System.exit;
import static lt.staupasedvinas.services.outputservice.PrintServiceImpl.*;

public class ManagingServiceImpl implements InputService, ManagingService, PrintService {

    private final Budget budget;
    private final InputServiceImpl inputService;
    private final PrintServiceImpl printService;

    public ManagingServiceImpl(Budget budget) {
        this.budget = budget;
        inputService = new InputServiceImpl(this.budget);
        printService = new PrintServiceImpl();
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
                    "7 save data to file\n" +
                    "8 get data from file\n" +
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
                case "7":
                    writeToFile();
                    break;
                case "8":
                    readFromFile();
                    break;
                case "0":
                    exit(0);
                default:
                    printlnErr("Bad input.");
                    break;
            }
        }
    }

    @Override
    public void editRecord() {
        printRecords();
        int index = getIndex();
        Record record = budget.getRecord(index);
        println("Sum: " + record.getSum());
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
        println("Date: " + record.getDate());
        println("Would you like to edit this record's date? yes/no");
        switch (scanner.next()) {
            case "yes":
                budget.getRecord(index).setDate(getDate());
                break;
            case "no":
                println("Okay, moving on...");
                break;
            default:
                printlnErr("Bad input");
        }
        println("Category index: " + record.getCategoryIndex());
        println("Would you like to edit this record's category index? yes/no");
        switch (scanner.next()) {
            case "yes":
                budget.getRecord(index).setCategoryIndex(getIndexInput());
                break;
            case "no":
                println("Okay, moving on...");
                break;
            default:
                printlnErr("Bad input");
        }
        if (record instanceof IncomeRecord) {
            println("Is money in bank account: " + ((IncomeRecord) record).getIsMoneyInBankAccount());
            println("Would you like to edit this record's info if money is in bank account? yes/no");
            switch (scanner.next()) {
                case "yes":
                    ((IncomeRecord) budget.getRecord(index)).setMoneyInBankAccount(getMoneyIsIn());
                    break;
                case "no":
                    println("Okay, moving on...");
                    break;
                default:
                    printlnErr("Bad input");
            }
        } else if (record instanceof ExpenseRecord) {
            println("Card number: " + ((ExpenseRecord) record).getCardNumber());
            println("Would you like to edit this record's card number? yes/no");
            switch (scanner.next()) {
                case "yes":
                    ((ExpenseRecord) budget.getRecord(index)).setCardNumber(getCardNumber());
                    break;
                case "no":
                    println("Okay, moving on...");
                    break;
                default:
                    printlnErr("Bad input");
            }
        }
        println("Additional info: " + record.getAdditionalInfo());
        println("Would you like to edit this record's additional info? yes/no");
        switch (scanner.next()) {
            case "yes":
                budget.getRecord(index).setAdditionalInfo(getInfo());
                break;
            case "no":
                println("Okay, moving on...");
                break;
            default:
                printlnErr("Bad input");
        }
        //TODO use this code for other variables
    }

    @Override
    public void deleteRecord() {
        printRecords();
        boolean removed;
        removed = removeRecord(getIndex());
        if (!removed) {
            printlnErr("Something gone wrong, your request was not fulfilled. It is likely that you entered wrong index, check that and try again");
        }
    }

    @Override
    public boolean removeRecord(int index) {
        return budget.removeRecord(index);
    }

    @Override
    public void printBalance() {
        println("Total balance: " + decimalFormat.format(budget.balance()));
        println("");
    }

    @Override
    public void printBudget() {
        printRecords();
        println("------------------------------------------------------" +
                "-------------------------------------------------------------");
        println("Total incomes: " + decimalFormat.format(
                budget.getListBalance(budget.getIncomeRecords())));
        println("Total expenses: " + decimalFormat.format(
                budget.getListBalance(budget.getExpenseRecords())));
        printBalance();
        println("");
    }

    @Override
    public void printRecords() {
        println("Incomes:");
        for (Record record : budget.getIncomeRecords()) {
            println(record.toString());
        }
        println("------------------------------------------------------" +
                "-------------------------------------------------------------\nExpenses:");
        for (Record record : budget.getExpenseRecords()) {
            println(record.toString());
        }
    }

    @Override
    public void addExpense() {
        budget.addRecord(new RecordFactory().newExpenseRecord(getSum(), getIndexInput(), getCardNumber(), getInfo(), budget));
    }

    @Override
    public void writeToFile() {
        new RecordsFileWriter(getFile()).write(budget.getRecords());
    }

    @Override
    public void readFromFile() {
        RecordsFileReader reader = new RecordsFileReader(getFile());
        List<Record> records = reader.read();
        if (!(records == null)) {
            println("Read file data:");
            for (Record record : records) {
                println(record.toString());
            }
            budget.setRecords(records);
            budget.setIndex(reader.getMaxIndex() + 1);
            println("New records were set");
        }
    }

    @Override
    public void addIncome() {
        budget.addRecord(new RecordFactory().newIncomeRecord(getSum(), getIndexInput(), getMoneyIsIn(), getInfo(), budget));
    }

    @Override
    public LocalDateTime getDate() {
        return inputService.getDate();
    }

    @Override
    public int getIndex() {
        return inputService.getIndex();
    }

    @Override
    public File getFile() {
        return inputService.getFile();
    }

    @Override
    public int getCardNumber() {
        return inputService.getCardNumber();
    }

    @Override
    public String getInfo() {
        return inputService.getInfo();
    }

    @Override
    public boolean getMoneyIsIn() {
        return inputService.getMoneyIsIn();
    }

    @Override
    public int getIndexInput() {
        return inputService.getIndexInput();
    }

    @Override
    public double getSum() {
        return inputService.getSum();
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
