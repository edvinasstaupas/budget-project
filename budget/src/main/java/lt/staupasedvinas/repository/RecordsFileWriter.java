package lt.staupasedvinas.repository;

import lt.staupasedvinas.domain.records.Record;
import lt.staupasedvinas.services.outputservice.PrintServiceImpl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class RecordsFileWriter {

    private final File file;

    public RecordsFileWriter(File file) {
        this.file = file;
    }

    public void write(List<Record> records) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write("index,id,sum,categoryindex,date,ismoneyin/cardnumber,info\n");
            for (Record record : records) {
                bufferedWriter.write(record.toCSVFormat());
            }
        } catch (IOException e) {
            new PrintServiceImpl().printlnErr("Something went wrong, contact administrator. Error message: " + e.getMessage());
        }
    }
}
