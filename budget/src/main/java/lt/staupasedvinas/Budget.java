package lt.staupasedvinas;

import lt.staupasedvinas.records.ExpenseRecord;
import lt.staupasedvinas.records.IncomeRecord;
import lt.staupasedvinas.records.Record;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Budget {

    private static int indexCounter = 0;

    private final List<Record> records;
    private final List<IncomeRecord> incomeRecords;
    private final List<ExpenseRecord> expenseRecords;

    public Budget() {
        records = new ArrayList<>();
        incomeRecords = new ArrayList<>();
        expenseRecords = new ArrayList<>();
    }

    public List<IncomeRecord> getIncomeRecords() {
        return incomeRecords;
    }

    public List<ExpenseRecord> getExpenseRecords() {
        return expenseRecords;
    }

    public void addExpenseRecord(double sum, int categoryIndex, int cardNumber, String additionalInfo) {
        records.add(new ExpenseRecord(sum, categoryIndex, LocalDateTime.now(), cardNumber, additionalInfo, indexCounter++));
    }

    public void addIncomeRecord(double sum, int categoryIndex, boolean isMoneyInBankAccount, String additionalInfo) {
        records.add(new IncomeRecord(sum, categoryIndex, LocalDateTime.now(), isMoneyInBankAccount, additionalInfo, indexCounter++));
    }

    public boolean removeExpenseRecord(int index) {
        return expenseRecords.remove(findObjectWithIndex(index, expenseRecords));
    }

    public boolean removeIncomeRecord(int index) {
        return incomeRecords.remove(findObjectWithIndex(index, incomeRecords));
    }

    public double getListBalance(List<? extends Record> list) {
        double balance = 0;
        for (Record record : list) {
            balance += record.getSum();
        }
        return balance;
    }

    public double balance() {
        return getListBalance(this.incomeRecords) - getListBalance(this.expenseRecords);
    }

    private <T extends Record> T findObjectWithIndex(int index, List<T> list) {
        for (T record : list) {
            if (record.getIndex() == index) {
                return record;
            }
        }
        return null;
    }
}
