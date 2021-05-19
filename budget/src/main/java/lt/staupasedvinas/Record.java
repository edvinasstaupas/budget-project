package lt.staupasedvinas;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Record {

    static DecimalFormat df = new DecimalFormat("#.##");
    static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

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

   /* @Override
    public String toString() {
        return "Sum = " + df.format(sum) +
                "\tcategory index = " + categoryIndex +
                "\tdate = " + dateTimeFormatter.format(date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Record)) return false;
        Record record = (Record) o;
        return Double.compare(record.getSum(), getSum()) == 0 && categoryIndex == record.categoryIndex && date.equals(record.date) && Objects.equals(additionalInfo, record.additionalInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSum(), categoryIndex, date, additionalInfo);
    }*/
}
