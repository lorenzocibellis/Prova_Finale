/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Controller;

import GestioneRubrica.Contatto;
import GestioneRubrica.Rubrica;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javafx.scene.input.KeyCode;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Acer
 */
public class ContattoControllerTest {
    
    private ContattoController controller;
    private Contatto mockContatto;
    private Rubrica mockRubrica;
    private TableView<Contatto> mockTable;
    private Button mockModifyButton;
    private Button mockConfirmButton;
    private TextField mockNameField;
    private TextField mockSurnameField;
    private TextField mockNumber1Field;
    private TextField mockNumber2Field;
    private TextField mockNumber3Field;
    private TextField mockEmail1Field;
    private TextField mockEmail2Field;
    private TextField mockEmail3Field;
    private StackPane mockContactPane;
    private boolean mocktypeController;

    @BeforeEach
    public void setup() {
        mockNameField.setText("");
        mockSurnameField.setText("");
        mockNumber1Field.setText("");
        mockNumber2Field.setText("");
        mockNumber3Field.setText("");
        mockEmail1Field.setText("");
        mockEmail2Field.setText("");
        mockEmail3Field.setText("");
    }

    @Test
    public void testInitialize() {
        assertEquals("", mockNameField.getText(), "Il campo nome dovrebbe essere vuoto");
        assertEquals("", mockSurnameField.getText(), "Il campo cognome dovrebbe essere vuoto");
        assertEquals("", mockNumber1Field.getText(), "Il campo numero1 dovrebbe essere vuoto");
        assertEquals("", mockNumber2Field.getText(), "Il campo numero2 dovrebbe essere vuoto");
        assertEquals("", mockNumber3Field.getText(), "Il campo numero3 dovrebbe essere vuoto");
        assertEquals("", mockEmail1Field.getText(), "Il campo email1 dovrebbe essere vuoto");
        assertEquals("", mockEmail2Field.getText(), "Il campo email2 dovrebbe essere vuoto");
        assertEquals("", mockEmail3Field.getText(), "Il campo email3 dovrebbe essere vuoto");
    }

    @Test
    public void testSetControllerWithRubrica() {
        // Eseguiamo il setController con il mock della rubrica
        controller.setController(mockRubrica);
    
        // Verifica che il puntatore alla rubrica non sia nullo
        assertNotNull(mockRubrica);
        
        // Verifica che il tipo di controller sia impostato su false (controller per aggiungere un contatto)
        assertFalse(this.mocktypeController);

        // Verifica che il bottone di modifica sia reso invisibile
        assertFalse(mockModifyButton.isVisible(), "Il bottone di modifica dovrebbe essere invisibile");
    }

    @Test
    public void testSetControllerWithContatto() {
        
        
        
        
        this.mockNameField.setText("Lorenzo");
        this.mockSurnameField.setText("Cibellis");
        this.mockNumber1Field.setText("1234567890");
        //Verifica che il contatto non sia nuovo
        assertNotNull(this.mockContatto);
        
        controller.setController(mockContatto, mockRubrica, this.mockTable);
        
        // Verifica che il tipo di controller sia impostato su false (controller per aggiungere un contatto)
        assertTrue(this.mocktypeController);
        
        //Verifica che la table non sia nulla
        assertNotNull(this.mockTable);
        
        // Verifica che i riferimenti agli oggetti siano stati correttamente assegnati
        assertEquals(mockRubrica, mockRubrica);
        assertTrue(mocktypeController);  // typeController dovrebbe essere true
        assertEquals(mockContatto, mockContatto);
        assertEquals(mockTable, mockTable);
    }

    @Test
    public void testModify() {
        // Simula un clic sul bottone di modifica
        controller.modifyButton.fire();
        
        // Verifica che il bottone di conferma diventi visibile e i campi siano abilitati
        verify(mockModifyButton).setVisible(false);
        verify(mockConfirmButton).setVisible(true);
        verify(mockNameField).setEditable(true);
    }

    @Test
    public void testConfirmAddValidContact() {
        when(mockNameField.getText()).thenReturn("John");
        when(mockSurnameField.getText()).thenReturn("Doe");
        when(mockNumber1Field.getText()).thenReturn("1234567890");
        when(mockEmail1Field.getText()).thenReturn("email1@example.com");

        // Imposta il comportamento della rubrica per aggiungere un contatto
        Contatto newContact = new Contatto();
        controller.setController(mockRubrica);
        controller.confirm(null);
        
        // Verifica che il contatto sia stato aggiunto
        verify(mockRubrica).aggiungiContatto(any(Contatto.class));
    }

    @Test
    public void testConfirmModValidContact() {
        // Simula la modifica di un contatto
        Contatto mockContatto = mock(Contatto.class);
        controller.setController(mockContatto, mockRubrica, mockTable);
        
        when(mockNameField.getText()).thenReturn("Jane");
        when(mockSurnameField.getText()).thenReturn("Doe");
        when(mockNumber1Field.getText()).thenReturn("0987654321");
        when(mockEmail1Field.getText()).thenReturn("email1_updated@example.com");
        
        controller.confirm(null);
        
        // Verifica che il contatto sia stato modificato
        verify(mockContatto).setNome("Jane");
        verify(mockContatto).setCognome("Doe");
        verify(mockContatto).setNumero1("0987654321");
        verify(mockContatto).setEmail1("email1_updated@example.com");
    }

    @Test
    public void testGoBack() {
        // Simula un clic sul bottone di uscita
        controller.goBack(null);
        
        // Verifica che la finestra venga chiusa (sembra un'azione di tipo Stage)
        verify(mockContactPane, atLeastOnce()).setOnKeyPressed(any());
    }
    
}
