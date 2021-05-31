package lt.staupasedvinas.repository;

import lt.staupasedvinas.domain.records.Record;
import lt.staupasedvinas.domain.records.RecordFactory;
import lt.staupasedvinas.services.outputservice.PrintServiceImpl;
import lt.staupasedvinas.utils.BadDataException;

import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RecordsFileReader {

    private final File file;

    public int getMaxIndex() {
        return maxIndex;
    }

    private int maxIndex;

    public RecordsFileReader(File file) {
        this.file = file;
    }

    public List<Record> read() {
        List<Record> list = new ArrayList<>();
        int max = -1;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            RecordFactory recordFactory = new RecordFactory();
            String line;
            String[] lines;
            bufferedReader.readLine(); //for the first line
            while ((line = bufferedReader.readLine()) != null) {
                lines = line.split(",");
                max = getMax(max, Integer.parseInt(lines[1]));
                if (lines[0].equals("I")) {
                    //P,123,200.6,1,2018-08-03T10:10:10,1234,a
                    //0, 1,   2,  3,         4,          5,  6
                    //I,123,200.6,1,2018-08-03T10:10:10,true,a
                    list.add(recordFactory.newIncomeRecordFromCSV(Integer.parseInt(lines[1]), Double.parseDouble(lines[2]),
                            Integer.parseInt(lines[3]), getDateFromString(lines[4]), getBooleanFromString(lines[5]), lines[6]));
                } else if (lines[0].equals("E")) {
                    list.add(recordFactory.newExpenseRecordFromCSV(Integer.parseInt(lines[1]), Double.parseDouble(lines[2]),
                            Integer.parseInt(lines[3]), getDateFromString(lines[4]), Integer.parseInt(lines[1]), lines[6]));
                } else {
                    throw new BadDataException("Record index unrecognised.");
                }
            }
        } catch (IOException | BadDataException | NumberFormatException | DateTimeException e) {
            new PrintServiceImpl().printlnErr("Something went wrong, contact administrator. Error message: " + e.getMessage());
            return null;
        }
        maxIndex = max;
        return list;
    }

    private int getMax(int index, int current) {
        return Math.max(current, index);
    }

    private boolean getBooleanFromString(String line) throws BadDataException {
        if (line.equals("true"))
            return true;
        else if (line.equals("false")) {
            return false;
        } else {
            throw new BadDataException("Bad boolean input");
        }
    }

    private LocalDateTime getDateFromString(String string) {
        //DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String[] dateAndTime = string.split(" ");
        String[] date = dateAndTime[0].split("-");
        String[] time = dateAndTime[1].split(":");
        return LocalDateTime.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]), Integer.parseInt(time[0]), Integer.parseInt(time[1]), Integer.parseInt(time[2]));
    }
}
