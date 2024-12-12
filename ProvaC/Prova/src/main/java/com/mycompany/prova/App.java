/**
 * @file MainRubrica
 * 
 * @brief Classe principale per l'applicazione in JavaFX.
 *
 *Questa classe rappresenta l'ingresso dell'applicazione JavaFX e carica
 *        l'interfaccia utente da un file FXML.
 */




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.prova;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application {

    private static Scene scene;

    /**
     * @brief Metodo standard per l'avvio del programma
     * 
     * Questo metodo  permette l'inizializzazione dell'interfaccia grafica di JavaFX
     * 
     * @param stage La finestra sulla quale mostrare l'interfaccia grafica
     * 
     * @throws IOException Eccezione del metodo loadFXML
     */
    
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("RubricaProva"));
        stage.setScene(scene);
        stage.show();
        scene.getRoot().requestFocus();
    }

    /**
     * @brief Setting della scena
     * 
     * Permette di settare il nodo Parent nella scena principale
     * 
     * @param fxml Il nome del file da cui ottenere in nodo principale
     * 
     * @throws IOException Eccezione del metodo loadFXML
     */
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * @brief Caricamento nodo principale
     * 
     * Il metodo permette di ottenere il nodo Parent di un file fxml
     * 
     * @param fxml Il nome del file fxml da cui ottenere il nodo
     * 
     * @return Il nodo del file fxml specificato
     * 
     * @throws IOException Eccezione del caricamento del nodo Parent
     */
    static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    /**
     * @brief Metodo per l'ottenimento del riferimento del file fxml
     * 
     * Questo metodo permette l'ottenimento del riferimento del file fxml specificato nella stringa
     * 
     * @param fxml Nome del file fxml da ottenere
     * 
     * @return L'oggetto FXMLLoader associato al file fxml 
     */
    public static FXMLLoader getFXML(String fxml){
        return new FXMLLoader(App.class.getResource(fxml+".fxml"));
    }

    /**
     * @brief Metodo standard di java
     * 
     * Questo metodo permette l'avvio del programma
     * 
     * @param args eventuali argomenti passati da riga-comando 
     */
    public static void main(String[] args) {
        launch();
    }

    
}
