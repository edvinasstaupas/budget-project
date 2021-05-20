package lt.staupasedvinas.records;

import java.time.LocalDateTime;

public class IncomeRecord extends Record {

    boolean isMoneyInBankAccount;

    public IncomeRecord(double sum, int categoryIndex, LocalDateTime date, boolean isMoneyInBankAccount, String additionalInfo, int index) {
        super(sum, categoryIndex, date, index, additionalInfo);
        this.isMoneyInBankAccount = isMoneyInBankAccount;
    }

    public boolean getIsMoneyInBankAccount() {
        return isMoneyInBankAccount;
    }
}
