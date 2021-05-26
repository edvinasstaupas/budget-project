package lt.staupasedvinas;

import lt.staupasedvinas.services.ManagingService;

public class Main {

    public static void main(String[] args) {
        new ManagingService(new Budget());
    }
}