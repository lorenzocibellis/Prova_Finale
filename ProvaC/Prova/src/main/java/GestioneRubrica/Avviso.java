/**
 * @file Avviso.java
 * 
 * @brief Classe per la gestione di pop-up informativi.
 *
 *Questa classe si occupa della gestione dell'interfaccia utente per implementare:
 * -avvisi riguardo azioni che andrebbero contro la logica del programma
 * -avvisi riguardo il successo/insuccesso di determinate azioni
 * -avvisi riguardo la conferma di determinate azioni che potrebbero non essere reversibili
 * 
 * @see GestioneRubrica.Rubrica
 * @see GestioneRubrica.Contatto
 */
package GestioneRubrica;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Avviso {
    
    /**
     * @brief Gestione pop-up di errore
     * 
     * Il metodo gestisce pop-up di avviso riguardo un'errore che si è verificato
     * all'interno del sistema, o per segnalare input non corretti forniti dall'utente
     * 
     * @param titolo Il titolo del pop-up di errore
     * @param avviso La stringa che deve comparire come corpo del pop-up
     */
    public static void errore(String titolo, String avviso){
        Alert alert = new Alert(Alert.AlertType.ERROR); //creazione oggetto di tipo Alert
        alert.setTitle(titolo); //Set del titolo
        alert.setHeaderText(null); // Set del titolo del corpo del pop-up
        alert.setContentText(avviso); //set del corpo del pop-up
        alert.showAndWait(); //visualizzazione del pop-up e attesa della sua chiusura
    }
    
    /**
     * @brief Gestione pop-up informativi
     * 
     * Il metodo gestisce pop-up informativi, utili per informare l'utente di certe funzionalità e/o eventi
     * durante l'utilizzo del programma
     * 
     * @param titolo Il titolo del pop-up informativo
     * @param avviso La stringa che deve comparire come corpo del pop-up
     */
    public static void info(String titolo, String avviso){
        Alert alert = new Alert(Alert.AlertType.INFORMATION); //creazione oggetto di tipo Alert
        alert.setTitle(titolo); //Set del titolo
        alert.setHeaderText(null); // Set del titolo del corpo del pop-up
        alert.setContentText(avviso); //set del corpo del pop-up
        alert.showAndWait(); //visualizzazione del pop-up e attesa della sua chiusura
    }
    
    /**
     * @brief Gestione pop-up di conferma
     * 
     * Il metodo gestisce pop-up di conferma, ovvero pop-up utili per confermare o annullare 
     * azioni intraprese dall'utente che potrebbero non essere reversibili
     * 
     * @param titolo Il titolo del pop-up di conferma
     * @param avviso La stringa che deve comparire come corpo del pop-up
     */
    public static boolean conferma(String titolo, String avviso){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION); //creazione oggetto di tipo Alert
    alert.setTitle(titolo); //Set del titolo
    alert.setHeaderText(null); // Set del titolo del corpo del pop-up
    alert.setContentText(avviso); //set del corpo del pop-up

  
    alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO); //set dei bottoni disponibili nel pop-up   
    
    Optional<ButtonType> result = alert.showAndWait(); //visualizzazione del pop-up e attesa della sua chiusura con conseguente ritorno del valore di conferma
    if(result.isPresent()) //controllo se la scelta è stata effettuata
        return result.get() == ButtonType.YES; //controllo del tipo di scelta effettuata
    return false;
    }
    
}
