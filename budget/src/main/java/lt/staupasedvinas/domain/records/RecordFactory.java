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
}
