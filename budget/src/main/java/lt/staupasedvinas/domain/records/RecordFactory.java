package lt.staupasedvinas.domain.records;

import lt.staupasedvinas.domain.Budget;

import java.time.LocalDateTime;

public class RecordFactory {

    public Record newIncomeRecord(double sum, int categoryIndex, boolean isMoneyInBankAccount, String additionalInfo, Budget budget) {
        return new IncomeRecord(sum, categoryIndex, LocalDateTime.now(), isMoneyInBankAccount, additionalInfo, budget.getIndexCounterWithAddition());
    }

    public Record newExpenseRecord(double sum, int categoryIndex, int cardNumber, String additionalInfo, Budget budget) {
        return new ExpenseRecord(sum, categoryIndex, LocalDateTime.now(), cardNumber, additionalInfo, budget.getIndexCounterWithAddition());
    }

    public Record newIncomeRecordFromCSV(int index, double sum, int categoryIndex, LocalDateTime date, boolean isMoneyInBankAccount, String additionalInfo) {
        return new IncomeRecord(sum, categoryIndex, date, isMoneyInBankAccount, additionalInfo, index);
    }

    public Record newExpenseRecordFromCSV(int index, double sum, int categoryIndex, LocalDateTime date, int cardNumber, String additionalInfo) {
        return new ExpenseRecord(sum, categoryIndex, date, cardNumber, additionalInfo, index);
    }
}
