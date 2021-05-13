package lt.staupasedvinas;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Record {

    static DecimalFormat df = new DecimalFormat("#.##");
    static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    double sum;
    int categoryIndex;
    LocalDateTime date;
    String additionalInfo;

    public Record(double sum, int categoryIndex, LocalDateTime date) {
        this.sum = sum;
        this.categoryIndex = categoryIndex;
        this.date = date;
    }

    public double getSum() {
        return sum;
    }
}
