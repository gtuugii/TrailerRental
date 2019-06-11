package mum.edu.swe.trailerrentalclient.model;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Status {

    //0-INACTIVE, 1-ACTIVE
    public Map<Integer, String> userStatus = new HashMap<Integer, String>() {
        {
            put(0, "INACTIVE");
            put(1, "ACTIVE");
        }
    };

    //0-CLOSE, 1-PENDING, 2-OPEN
    public Map<Integer, String> rentStatus = new HashMap<Integer, String>() {
        {
            put(0, "CLOSE");
            put(1, "PENDING");
            put(2, "OPEN");
        }
    };

    // 0-MAINTENANCE 1-ACTIVE, 2-PENDING, 3-RENTED
    public Map<Integer, String> trailerStatus = new HashMap<Integer, String>() {
        {
            put(0, "MAINTENANCE");
            put(1, "ACTIVE");
            put(2, "PENDING");
            put(3, "RENTED");
        }
    };

    // 0-CLOSED 1-OPEN
    public Map<Integer, String> mainStatus = new HashMap<Integer, String>() {
        {
            put(0, "CLOSED");
            put(1, "OPEN");
        }
    };

}
