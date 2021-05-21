package lt.staupasedvinas;

import lt.staupasedvinas.records.ExpenseRecord;
import lt.staupasedvinas.records.IncomeRecord;
import lt.staupasedvinas.records.Record;
import lt.staupasedvinas.services.BudgetForms;
import lt.staupasedvinas.services.PrintService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Budget {

    private int indexCounter;

    private final List<Record> records;
    @Deprecated
    private final List<IncomeRecord> incomeRecords;
    @Deprecated
    private final List<ExpenseRecord> expenseRecords;

    public Budget() {
        indexCounter = 0;
        records = new ArrayList<>();
        incomeRecords = new ArrayList<>();
        expenseRecords = new ArrayList<>();
    }

    public List<Record> getRecords() {
        return records;
    }

    @Deprecated
    public List<IncomeRecord> getIncomeRecords() {
        return incomeRecords;
    }

    @Deprecated
    public List<ExpenseRecord> getExpenseRecords() {
        return expenseRecords;
    }

    public void addRecord(Record record) {
        records.add(record);
    }

    @Deprecated
    public void addExpenseRecord(double sum, int categoryIndex, int cardNumber, String additionalInfo) {
        records.add(new ExpenseRecord(sum, categoryIndex, LocalDateTime.now(), cardNumber, additionalInfo, indexCounter++));
    }

    @Deprecated
    public void addIncomeRecord(double sum, int categoryIndex, boolean isMoneyInBankAccount, String additionalInfo) {
        records.add(new IncomeRecord(sum, categoryIndex, LocalDateTime.now(), isMoneyInBankAccount, additionalInfo, indexCounter++));
    }

    public boolean removeRecord(int index) {
        return records.remove(findRecordWithIndex(index));
    }

    @Deprecated
    public boolean removeExpenseRecord(int index) {
        return expenseRecords.remove(findObjectWithIndex(index, expenseRecords));
    }

    @Deprecated
    public boolean removeIncomeRecord(int index) {
        return incomeRecords.remove(findObjectWithIndex(index, incomeRecords));
    }


    public double getListBalance(List<Record> list, BudgetForms budgetForm) {
        double balance = 0;
        for (Record record : list) {
            if (budgetForm.equals(BudgetForms.INCOME)) {
                if (record instanceof IncomeRecord)
                    balance += record.getSum();
            } else if (budgetForm.equals(BudgetForms.EXPENSE)) {
                if (record instanceof ExpenseRecord)
                    balance += record.getSum();
            } else if (budgetForm.equals(BudgetForms.ALL))
                balance += record.getSum();
            else {
                PrintService.printlnErr("Something went bad! Contact support");
            }
        }
        return balance;
    }

    public double balance() {
        return getListBalance(records, BudgetForms.INCOME) - getListBalance(records, BudgetForms.EXPENSE);
    }

    @Deprecated
    private <T extends Record> T findObjectWithIndex(int index, List<T> list) {
        for (T record : list) {
            if (record.getIndex() == index) {
                return record;
            }
        }
        return null;
    }

    private Record findRecordWithIndex(int index) {
        for (Record record : records) {
            if (record.getIndex() == index) {
                return record;
            }
        }
        return null;
    }

    public int getIndexCounterWithAddition() {
        return indexCounter++;
    }

    public int getIndexCounter() {
        return indexCounter;
    }

    public boolean editRecord(int index) {
        findRecordWithIndex(index);
        return false;
    }

    public Record getRecord(int index) {
        return findRecordWithIndex(index);
    }
}
