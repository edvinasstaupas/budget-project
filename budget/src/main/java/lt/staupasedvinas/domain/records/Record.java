package lt.staupasedvinas.domain.records;

import lt.staupasedvinas.services.outputservice.PrintServiceImpl;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Record {

    private final int index;
    private double sum;
    private int categoryIndex;

    public void setSum(double sum) {
        this.sum = sum;
    }

    public void setCategoryIndex(int categoryIndex) {
        this.categoryIndex = categoryIndex;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    private LocalDateTime date;
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

    @Override
    public String toString() {
        return String.format("index = %-4d sum = %-6s category index = %-4d date = %-14s", index,
                PrintServiceImpl.decimalFormat.format(sum), categoryIndex, PrintServiceImpl.dateTimeFormat.format(date));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Record)) return false;
        Record record = (Record) o;
        return getIndex() == record.getIndex();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIndex());
    }
}
