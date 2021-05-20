package lt.staupasedvinas.records;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Record {

    private final int index;
    private final double sum;
    private final int categoryIndex;
    private final LocalDateTime date;
    private String additionalInfo;

    public Record(double sum, int categoryIndex, LocalDateTime date, int index, String additionalInfo) {
        this.index = index;
        this.sum = sum;
        this.categoryIndex = categoryIndex;
        this.date = date;
        this.additionalInfo = additionalInfo;
    }

    public double getSum() {
        return sum;
    }

    public int getCategoryIndex() {
        return categoryIndex;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public int getIndex() {
        return index;
    }
}
