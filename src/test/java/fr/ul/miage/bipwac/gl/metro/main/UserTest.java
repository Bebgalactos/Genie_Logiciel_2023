package fr.ul.miage.bipwac.gl.metro.main;

import fr.ul.miage.bipwac.gl.metro.graphe.User;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


import static org.junit.Assert.*;


public class UserTest {

    @ParameterizedTest
    @CsvSource({
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1"
    })
    public void rangeLatitude(String ignore) {
        User u = new User();
        assertTrue(u.getUserLatitude() >= 48.815573 && u.getUserLatitude() <= 48.902145);
    }

    @ParameterizedTest
    @CsvSource({
            "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1"
    })
    public void rangeLongitude(String ignore) {
        User u = new User();
        assertTrue(u.getUserLongitude() >= 2.225828 && u.getUserLongitude() <= 2.469920);
    }


}
