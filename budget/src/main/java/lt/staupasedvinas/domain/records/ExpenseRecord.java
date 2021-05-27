package lt.staupasedvinas.domain.records;

import java.time.LocalDateTime;

public class ExpenseRecord extends Record {

    private int cardNumber;

    public ExpenseRecord(double sum, int categoryIndex, LocalDateTime date, int cardNumber, String additionalInfo, int index) {
        super(sum, categoryIndex, date, index, additionalInfo);
        this.cardNumber = cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" card number = %-18d additional info = %s", cardNumber, getAdditionalInfo());
    }
}
