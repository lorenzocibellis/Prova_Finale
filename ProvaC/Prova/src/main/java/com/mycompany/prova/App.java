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
     * @throws IOException Eccezione di errore di creazione della scena
     */
    
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("RubricaProva"));
        stage.setScene(scene);
        stage.show();
        scene.getRoot().requestFocus();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

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
