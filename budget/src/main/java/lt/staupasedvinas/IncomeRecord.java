package lt.staupasedvinas;

import java.time.LocalDateTime;

public class IncomeRecord extends Record {

    boolean isMoneyInBankAccount;

    public IncomeRecord(double sum, int categoryIndex, LocalDateTime date, boolean isMoneyInBankAccount, String additionalInfo) {
        super(sum, categoryIndex, date);
        this.isMoneyInBankAccount = isMoneyInBankAccount;
        this.additionalInfo = additionalInfo;
    }


    @Override
    public String toString() {
        String string = "Sum = " + df.format(sum) +
                "\tcategory index = " + categoryIndex +
                "\tdate = " + dateTimeFormatter.format(date) +
                "\tis money in bank account = " + isMoneyInBankAccount;
        if (additionalInfo.length() > 0)
            string += "\tadditional info = \"" + additionalInfo + "\".";
        return string;
    }
}
