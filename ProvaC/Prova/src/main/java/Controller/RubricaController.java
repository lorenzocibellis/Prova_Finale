/**
 * @file RubricaController.java
 * 
 * @brief Classe Controller per la gestione dell'interfaccia dell'applicazione "Rubrica".
 * 
 * Questa classe gestisce le interazioni con l'utente e le azioni sulla Rubrica.
 *
 * @see GestioneRubrica.Rubrica
 * @see Controller.Controller
 */



package Controller;

import GestioneRubrica.Contatto;
import GestioneRubrica.Rubrica;
import com.mycompany.prova.App;
import java.awt.Button;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RubricaController implements Initializable {

    @FXML
    private TableView<Contatto> rubricaList;
    @FXML
    private TableColumn<Contatto, String> cognomeClm;
    @FXML
    private TableColumn<Contatto, String> nomeClm;

    
   
    
    private Rubrica rubricaPointer;
    
    @FXML
    private javafx.scene.control.Button addButton;
    @FXML
    private javafx.scene.control.Button removeButton;
    @FXML
    private javafx.scene.control.Button importButton;
    @FXML
    private javafx.scene.control.Button exportButton;
    @FXML
    private javafx.scene.control.Button researchButton;
    @FXML
    private javafx.scene.control.TextField researchField;
    @FXML
    private javafx.scene.control.Button exitButton;
            
    
    ContattoController contattoController;
    @FXML
    private StackPane contattoPane;
    
    
    
     @Override
    public void initialize(URL location, ResourceBundle resources) {
    
        contattoPane.setVisible(false);
        this.rubricaPointer = new Rubrica();
        
         
       
        nomeClm.setCellValueFactory(s -> { return new SimpleStringProperty(s.getValue().getNome()); });   // freccetta, a sx c'è il parametro del metodo,e a dx il corpo del metodo con un eventuale restituzuione
        cognomeClm.setCellValueFactory(new PropertyValueFactory("cognome"));  // dato questo nome va a vedere se esiste get seguito da surname, se esiste si fa restituire il valore e lo avvolge in una simple property

        
        rubricaList.setItems(rubricaPointer.getContactList());   // collega i dati della rubrica alla tableview
        rubricaList.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.MULTIPLE);
        
        
        
       
        
        EventHandler<MouseEvent> ClickHandler = event ->{
            if(event.isControlDown())
                contattoPane.getChildren().clear();
            else
                try {
                        
                    openContact(null); //apre lo studente

                    } catch (IOException ex) {
                    Logger.getLogger(ContattoController.class.getName()).log(Level.SEVERE, null, ex);
                }
        };
        
        rubricaList.setOnMouseClicked(ClickHandler);
        
        
        
        
    }
    
    
    @FXML
    private void add(javafx.event.ActionEvent event) throws IOException {
        
        FXMLLoader f = App.getFXML("Contatto");
        Parent root = f.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        
        
        ContattoController controller = f.getController(); 
    if (controller != null) {
        controller.setController(this.rubricaPointer); 
    } else {
        System.out.println("Il controller è nullo");
    }
       
    stage.show();
        
    }

    
    private void openContact(javafx.event.ActionEvent event) throws IOException {
    
        Contatto temp = (Contatto) rubricaList.getSelectionModel().getSelectedItem();
                
        if(temp == null)
            return;
        
        contattoPane.setVisible(true);
        FXMLLoader loader = App.getFXML("Contatto");
    StackPane contactPane = loader.load();

    ContattoController controller = loader.getController();
        controller.setController(temp, rubricaPointer, rubricaList);
    

    contattoPane.getChildren().clear();
    contattoPane.getChildren().add(contactPane);

   
        
    }

    @FXML
    private void delete(javafx.event.ActionEvent event) {
    
        
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Attenzione");
    alert.setHeaderText(null); //
    alert.setContentText("Sei sicuro di voler eliminare il/i contatto/i?");

  
    alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);    
    
    Optional<ButtonType> result = alert.showAndWait();
    
    if (result.isPresent()) {
    
        if (result.get() == ButtonType.YES) {
       
       rubricaPointer.rimuoviContatto(rubricaList.getSelectionModel().getSelectedItems());
            contattoPane.getChildren().clear();
    
        }
        
    }
        
    
    }

    
    
    @FXML
    private void importRubrica(javafx.event.ActionEvent event) throws IOException {
    
    FileChooser fileChooser = new FileChooser();
        
        
    FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("CSV Files (*.csv)", "*.csv");
    fileChooser.getExtensionFilters().add(filter);


    File file = fileChooser.showOpenDialog(null);

        if (file != null) { 
            
            try {
       
        Rubrica nuovaRubrica = rubricaPointer.importaRubrica(file.getAbsolutePath());
        
        
            rubricaPointer.rimuoviContatto(rubricaPointer.getContactList()); // rimuovi tutti i vecchi contatti in rubrica
            rubricaPointer = nuovaRubrica;
            
            
            rubricaList.setItems(rubricaPointer.getContactList());
        
            
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                     alert.setTitle("Avviso");
                    alert.setHeaderText(null); // Se non vuoi nessun header, puoi impostarlo a null
                    alert.setContentText("La rubrica è stata importata con successo!");
                    alert.showAndWait();
            
            
            
    } catch (IOException e) {
        
        System.err.println("Errore durante l'importazione del file: " + e.getMessage());
     
    }
        
        
        
        } else {
    
                System.out.println("Selezione del file annullata.");
            }
    
     
    }

    @FXML
    private void exportRubrica(javafx.event.ActionEvent event) throws IOException {
    
    
   FileChooser fileChooser = new FileChooser();

   
    FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("CSV Files (*.csv)", "*.csv");
    fileChooser.getExtensionFilters().add(filter);
    
    File file = fileChooser.showSaveDialog(null); 
    
       
    if(file != null){
    
    
        rubricaPointer.esportaRubrica(file.getAbsolutePath());
   
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                     alert.setTitle("Avviso");
                    alert.setHeaderText(null); // Se non vuoi nessun header, puoi impostarlo a null
                    alert.setContentText("La rubrica è stata esportata con successo!");
                    alert.showAndWait();
        
        
    }else{
        
        System.out.println("Esportazione annullata");
        
    }
    
    }

    @FXML
    private void research(javafx.event.ActionEvent event) {
    
    if(!researchField.getText().isEmpty())
            rubricaList.setItems(rubricaPointer.ricercaContatti(researchField.getText()).getContactList());
        else
            rubricaList.setItems(rubricaPointer.getContactList());
    
    
    
    }

    @FXML
    private void goBack(javafx.event.ActionEvent event) {
    
     ((javafx.stage.Stage) exitButton.getScene().getWindow()).close();
        
    
    }
    
    /**
     * @brief Esporta i contatti della Rubrica in un file.
     * 
     * @param e evento che attiva l'esportazione
     */


    
    
    
    

   
}