package lt.staupasedvinas;

import java.time.LocalDateTime;

public class Budget {

    private final IncomeRecord[] incomeRecord;
    private final ExpenseRecord[] expenseRecord;

    private int incomeCounter;
    private int expenseCounter;

    public Budget() {
        incomeRecord = new IncomeRecord[100];
        expenseRecord = new ExpenseRecord[100];
        incomeCounter = 0;
        expenseCounter = 0;
    }

    public IncomeRecord[] getIncomeRecord() {
        return incomeRecord;
    }

    public ExpenseRecord[] getExpenseRecord() {
        return expenseRecord;
    }

    public int getIncomeCounter() {
        return incomeCounter;
    }

    public int getExpenseCounter() {
        return expenseCounter;
    }

    public void addExpenseRecord(double sum, int categoryIndex, int cardNumber, String additionalInfo) {
        expenseRecord[expenseCounter++] = new ExpenseRecord(sum, categoryIndex, LocalDateTime.now(), cardNumber, additionalInfo);
    }

    public void addIncomeRecord(double sum, int categoryIndex, boolean isMoneyInBankAccount, String additionalInfo) {
        incomeRecord[incomeCounter++] = new IncomeRecord(sum, categoryIndex, LocalDateTime.now(), isMoneyInBankAccount, additionalInfo);
    }
}
