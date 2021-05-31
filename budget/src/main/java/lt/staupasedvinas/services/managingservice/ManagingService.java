package lt.staupasedvinas.services.managingservice;

public interface ManagingService {
    void editRecord();

    void deleteRecord();

    boolean removeRecord(int index);

    void printBalance();

    void printBudget();

    void printRecords();

    void addExpense();

    void writeToFile();

    void readFromFile();

    void addIncome();

}
