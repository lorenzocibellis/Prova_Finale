/**
 * @file Controller.java
 * 
 * @brief Classe astratta base per i controller dell'applicazione.
 *
 *Fornisce funzionalità comuni per la gestione e la navigazione
 *        tra le schermate. Le sottoclassi possono determinare in maniera autonoma 
 *        i diversi metodi in base all'evenienza
 * 
 * @see javafx.scene.Parent
 * @see javafx.fxml.FXML
 * 
 */




package Controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.stage.Stage;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */




public abstract class Controller {

    /**
     * @brief Mostra una nuova vista sulla scena corrente.
     *        
     *Questo metodo consente di visualizzare un nodo {@code Parent} specifico
     *        come nuova interfaccia utente. Può essere usato per navigare tra diverse
     *        schermate dell'applicazione.
     *
     * @param p il nodo {@code Parent} da visualizzare.
     *          Non può essere {@code null}.
     * 
     * @throws Da determinare se il metodo può generare eccezioni, come {@code NullPointerException}.
     */
    @FXML
    private void display(Parent p) {
        // Da implementare
    }

    /**
     * @brief Torna alla schermata precedente.
     *         
     *Questo metodo consente di ripristinare la schermata visualizzata prima
     *        della navigazione corrente. La logica di gestione della cronologia delle
     *        viste deve essere definita nell'implementazione.
     *
     * @throws Da determinare se il metodo può generare eccezioni, come {@code IOException}.
     */
    @FXML
    protected void goBack(Stage stage) {
        // Da implementare
    
    if (stage != null) {
        stage.close();
    }
    
    
    
    }
}
