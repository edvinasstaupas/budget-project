package lt.staupasedvinas.services.inputservice;

import lt.staupasedvinas.utils.BadInputException;

import java.io.File;
import java.time.LocalDateTime;

public interface InputService {
    int getCardNumber();

    String getInfo();

    boolean getMoneyIsIn();

    int getIndexInput();

    double getSum();

    LocalDateTime getDate();

    int getIndex();

    File getFile();
}
