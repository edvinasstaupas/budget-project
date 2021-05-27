package lt.staupasedvinas.services.inputservice;

import java.time.LocalDateTime;

public interface InputService {
    int getCardNumber();

    String getInfo();

    boolean getMoneyIsIn();

    int getIndexInput();

    double getSum();

    LocalDateTime getDate();

    int getIndex();
}
