package lt.staupasedvinas.domain;

import lt.staupasedvinas.domain.records.ExpenseRecord;
import lt.staupasedvinas.domain.records.IncomeRecord;
import lt.staupasedvinas.domain.records.Record;
import lt.staupasedvinas.utils.BudgetForms;

import java.util.ArrayList;
import java.util.List;

public class Budget {

    private int indexCounter;

    private List<Record> records;

    public Budget() {
        indexCounter = 0;
        records = new ArrayList<>();
    }

    public List<Record> getRecords() {
        return records;
    }

    public List<Record> getIncomeRecords() {
        return getList(BudgetForms.INCOME);
    }

    public List<Record> getExpenseRecords() {
        return getList(BudgetForms.EXPENSE);
    }

    private List<Record> getList(BudgetForms budgetForm) {
        List<Record> list = new ArrayList<>();
        for (Record record : records) {
            if (budgetForm.equals(BudgetForms.INCOME)) {
                if (record instanceof IncomeRecord) {
                    list.add(record);
                }
            } else if (budgetForm.equals(BudgetForms.EXPENSE)) {
                if (record instanceof ExpenseRecord) {
                    list.add(record);
                }
            }
        }
        return list;
    }

    public void addRecord(Record record) {
        records.add(record);
    }

    public boolean removeRecord(int index) {
        return records.remove(findRecordWithIndex(index));
    }

    public double getListBalance(List<Record> list) {
        double balance = 0;
        for (Record record : list) {
            balance += record.getSum();
        }
        return balance;
    }

    public double balance() {
        return getListBalance(getList(BudgetForms.INCOME)) - getListBalance(getList(BudgetForms.EXPENSE));
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

    public Record getRecord(int index) {
        return findRecordWithIndex(index);
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public void setIndex(int maxIndex) {
        indexCounter = maxIndex;
    }
}
