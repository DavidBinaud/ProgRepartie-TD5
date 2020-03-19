package Ex3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SalonTest {


    Salon salon;

    @BeforeEach
    void setUp() {
        salon = new Salon("attente");
    }

    @Test
    void getConnectesNamesEmpty() {
        String str = salon.getConnectesNames();
        assertEquals(0,salon.getConnectes().size());
        assertEquals("Le salon attente est vide",str);
    }
}