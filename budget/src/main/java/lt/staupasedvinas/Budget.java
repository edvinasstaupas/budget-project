package lt.staupasedvinas;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Budget {

    private static int incomeCounter = 0;
    private static int expenseCounter = 0;

    private final List<IncomeRecord> incomeRecords;
    private final List<ExpenseRecord> expenseRecords;

    public Budget() {
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
        expenseRecords.add(new ExpenseRecord(sum, categoryIndex, LocalDateTime.now(), cardNumber, additionalInfo, incomeCounter++));
    }

    public void addIncomeRecord(double sum, int categoryIndex, boolean isMoneyInBankAccount, String additionalInfo) {
        incomeRecords.add(new IncomeRecord(sum, categoryIndex, LocalDateTime.now(), isMoneyInBankAccount, additionalInfo, expenseCounter++));
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
