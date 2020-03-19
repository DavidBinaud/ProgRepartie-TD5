package Ex3;


import static org.junit.jupiter.api.Assertions.*;

class SalonsTest {

    Salons salons;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        salons = new Salons();
    }

    @org.junit.jupiter.api.Test
    void getSalonsNames() {
        String s = salons.getSalonsNames();
        assertEquals("Noms des salons: attente - chatroom-cours - chatroom-détente",s);
    }

}