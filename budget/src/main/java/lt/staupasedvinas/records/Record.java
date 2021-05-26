package lt.staupasedvinas.records;

import java.time.LocalDateTime;

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

    //TODO toString method both here and in child classes
    @Override
    public String toString() {
        return "Record{" +
                "index=" + index +
                ", sum=" + sum +
                ", categoryIndex=" + categoryIndex +
                ", date=" + date +
                ", additionalInfo='" + additionalInfo + '\'' +
                '}';
    }
}
