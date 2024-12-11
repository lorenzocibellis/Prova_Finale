/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestioneRubrica;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author matti
 */
public class Avviso {
    
    public static void errore(String titolo, String avviso){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titolo);
        alert.setHeaderText(null); // Se non vuoi nessun header, puoi impostarlo a null
        alert.setContentText(avviso);
        alert.showAndWait();
    }
    
    public static void info(String titolo, String avviso){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titolo);
        alert.setHeaderText(null); // Se non vuoi nessun header, puoi impostarlo a null
        alert.setContentText(avviso);
        alert.showAndWait();
    }
    
    public static boolean conferma(String titolo, String avviso){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Attenzione");
    alert.setHeaderText(null); //
    alert.setContentText("Sei sicuro di voler eliminare il/i contatto/i?");

  
    alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);    
    
    Optional<ButtonType> result = alert.showAndWait();
    if(result.isPresent())
        return result.get() == ButtonType.YES;
    
    return false;
    }
    
}
