package lt.staupasedvinas.domain.records;

import java.time.LocalDateTime;

public class IncomeRecord extends Record {

    boolean isMoneyInBankAccount;

    public IncomeRecord(double sum, int categoryIndex, LocalDateTime date, boolean isMoneyInBankAccount, String additionalInfo, int index) {
        super(sum, categoryIndex, date, index, additionalInfo);
        this.isMoneyInBankAccount = isMoneyInBankAccount;
    }

    public void setMoneyInBankAccount(boolean moneyInBankAccount) {
        isMoneyInBankAccount = moneyInBankAccount;
    }

    public boolean getIsMoneyInBankAccount() {
        return isMoneyInBankAccount;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" is money in bank account = %-5s additional info = %s", isMoneyInBankAccount, getAdditionalInfo());
    }


}
