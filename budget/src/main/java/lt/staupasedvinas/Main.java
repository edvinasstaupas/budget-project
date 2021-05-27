package lt.staupasedvinas;

import lt.staupasedvinas.domain.Budget;
import lt.staupasedvinas.services.managingservice.ManagingServiceImpl;

public class Main {

    public static void main(String[] args) {
        new ManagingServiceImpl(new Budget());
    }
}