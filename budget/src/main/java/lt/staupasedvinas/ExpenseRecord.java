package lt.staupasedvinas;

import java.time.LocalDateTime;

public class ExpenseRecord extends Record {

    int cardNumber;

    public ExpenseRecord(double sum, int categoryIndex, LocalDateTime date, int cardNumber, String additionalInfo) {
        super(sum, categoryIndex, date);
        this.cardNumber = cardNumber;
        this.additionalInfo = additionalInfo;
    }


    @Override
    public String toString() {
        String string = "Sum = " + df.format(sum) +
                "\tcategory index = " + categoryIndex +
                "\tdate = " + dateTimeFormatter.format(date) +
                "\tcard number = " + cardNumber;
        if (additionalInfo.length() > 0)
            string += "\tadditional info = \"" + additionalInfo + "\".";
        return string;
    }
}
