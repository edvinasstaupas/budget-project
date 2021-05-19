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
}
