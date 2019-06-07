package mum.edu.swe.trailerrentalserver.domain;

public class Status {
    enum TRAILER{
        ACTIVE, PEND, RENT;
    }

    enum RENT{
        OPEN, CLOSE, PEND;
    }

    enum USER{
        ACTIVE, INACTIVE;
    }
}
