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
import javafx.stage.Modality;
import javafx.stage.Stage;


public class App extends Application {

        private static Scene scene;
        
   
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("RubricaProva"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    public static FXMLLoader getFXML(String fxml){
        return new FXMLLoader(App.class.getResource(fxml+".fxml"));
    }

    public static void main(String[] args) {
        launch();
    }

    
}
