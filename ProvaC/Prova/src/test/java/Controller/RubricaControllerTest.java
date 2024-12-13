/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Controller;

import GestioneRubrica.Rubrica;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
public class RubricaControllerTest {
    private static Rubrica temp ; 
    public RubricaControllerTest() {
    }
    
    @BeforeAll
    public static void setUpClass() throws IOException {
        System.out.println("Inizializzazione della rubrica per i test.");
        
        // Inizializza una nuova rubrica
        temp = new Rubrica();
        
        // Importa i dati da un file di test (assicurati che TestFile.csv esista e sia valido)
        temp.importaRubrica("TestFile.csv");
        
        // Verifica che la rubrica sia stata inizializzata correttamente
        assertNotNull(temp.getContactList(), "La rubrica non è stata inizializzata correttamente.");
    }
    
    @AfterAll
    public static void tearDownClass() {
         System.out.println("Pulizia finale dopo i test.");
        temp = null; // Rilascia le risorse della rubrica impiegata
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of initialize method, of class RubricaController.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        URL location = null;
        ResourceBundle resources = null;
        RubricaController instance = new RubricaController();
        instance.initialize(location, resources);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
