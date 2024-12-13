/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package GestioneRubrica;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Acer
 */
public class ContattoTest {
  private Contatto contatto;

    @BeforeEach
    void setUp() {
        // Prima di ogni test, creiamo un nuovo contatto
        contatto = new Contatto();
    }

    @Test
    void testSetAndGetNome() {
        // Testiamo i metodi set e get per il nome
        contatto.setNome("Marco");
        assertEquals("Marco", contatto.getNome(), "Il nome non è stato impostato correttamente.");
    }

    @Test
    void testSetAndGetCognome() {
        // Testiamo i metodi set e get per il cognome
        contatto.setCognome("Rossi");
        assertEquals("Rossi", contatto.getCognome(), "Il cognome non è stato impostato correttamente.");
    }

    @Test
    void testSetAndGetNumeri() {
        // Testiamo i numeri di telefono
        contatto.setNumero1("1234567890");
        contatto.setNumero2("0987654321");
        contatto.setNumero3("1122334455");

        String[] numeri = contatto.getNumeri();
        assertEquals("1234567890", numeri[0], "Il primo numero non è stato impostato correttamente.");
        assertEquals("0987654321", numeri[1], "Il secondo numero non è stato impostato correttamente.");
        assertEquals("1122334455", numeri[2], "Il terzo numero non è stato impostato correttamente.");
    }

    @Test
    void testSetAndGetEmails() {
        // Testiamo gli indirizzi email
        contatto.setEmail1("marco.rossi@example.com");
        contatto.setEmail2("m.rossi@example.com");
        contatto.setEmail3("rossi.m@example.com");

        String[] emails = contatto.getEmails();
        assertEquals("marco.rossi@example.com", emails[0], "Il primo email non è stato impostato correttamente.");
        assertEquals("m.rossi@example.com", emails[1], "Il secondo email non è stato impostato correttamente.");
        assertEquals("rossi.m@example.com", emails[2], "Il terzo email non è stato impostato correttamente.");
    }

    @Test
    void testCompareToEqual() {
        // Testiamo se due contatti con gli stessi nome e cognome sono uguali
        Contatto contatto2 = new Contatto();
        contatto2.setNome("Marco");
        contatto2.setCognome("Rossi");

        contatto.setNome("Marco");
        contatto.setCognome("Rossi");

        assertEquals(0, contatto.compareTo(contatto2), "I contatti dovrebbero essere uguali.");
    }

    @Test
    void testCompareToDifferentCognome() {
        // Testiamo se il confronto tiene conto del cognome
        Contatto contatto2 = new Contatto();
        contatto2.setNome("Marco");
        contatto2.setCognome("Bianchi");

        contatto.setNome("Marco");
        contatto.setCognome("Rossi");

        assertTrue(contatto.compareTo(contatto2) > 0, "Il cognome Rossi dovrebbe essere maggiore di Bianchi.");
    }

    @Test
    void testCompareToDifferentNome() {
        // Testiamo se il confronto tiene conto del nome
        Contatto contatto2 = new Contatto();
        contatto2.setNome("Alessandro");
        contatto2.setCognome("Rossi");

        contatto.setNome("Marco");
        contatto.setCognome("Rossi");

        assertTrue(contatto.compareTo(contatto2) < 0, "Il nome Marco dovrebbe essere minore di Alessandro.");
    }

    @Test
    void testEquals() {
        // Testiamo se due contatti uguali sono considerati uguali
        Contatto contatto2 = new Contatto();
        contatto2.setNome("Marco");
        contatto2.setCognome("Rossi");

        contatto.setNome("Marco");
        contatto.setCognome("Rossi");

        assertTrue(contatto.equals(contatto2), "I contatti dovrebbero essere uguali.");
    }

    @Test
    void testNotEquals() {
        // Testiamo se due contatti con nomi o cognomi diversi non sono uguali
        Contatto contatto2 = new Contatto();
        contatto2.setNome("Luca");
        contatto2.setCognome("Rossi");

        contatto.setNome("Marco");
        contatto.setCognome("Rossi");

        assertFalse(contatto.equals(contatto2), "I contatti dovrebbero essere diversi.");
    }
    
}
