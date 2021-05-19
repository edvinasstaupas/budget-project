package lt.staupasedvinas;

import java.time.LocalDateTime;

public class ExpenseRecord extends Record {

    int cardNumber;

    public ExpenseRecord(double sum, int categoryIndex, LocalDateTime date, int cardNumber, String additionalInfo, int index) {
        super(sum, categoryIndex, date, index, additionalInfo);
        this.cardNumber = cardNumber;
    }

    public int getCardNumber() {
        return cardNumber;
    }

   /* @Override
    public String toString() {
        String string = super.toString() + "\tcard number = " + cardNumber;
        if (additionalInfo.length() > 0)
            string += "\tadditional info = \"" + additionalInfo + "\".";
        return string;
    }*/
}
