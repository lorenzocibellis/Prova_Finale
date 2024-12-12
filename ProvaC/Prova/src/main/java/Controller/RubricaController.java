/**
 * @file RubricaController.java
 * 
 * @brief Classe Controller per la gestione dell'interfaccia dell'applicazione "Rubrica".
 * 
 * Questa classe gestisce le interazioni con l'utente e le azioni sulla Rubrica.
 *
 * @see GestioneRubrica.Rubrica
 */



package Controller;

import GestioneRubrica.Avviso;
import GestioneRubrica.Contatto;
import GestioneRubrica.Rubrica;
import com.mycompany.prova.App;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RubricaController implements Initializable {

    /**
     * Pannello di base sul quale è visualizzato il contatto.
     */
    @FXML
    private StackPane contattoPane;
    
    /**
     * Tabella visualizzazione dei contatti.
     */
    @FXML
    private TableView<Contatto> rubricaList;
    
    /**
     * Colonna dei cognomi della tabella.
     */
    @FXML
    private TableColumn<Contatto, String> cognomeClm;
    
    /**
     * Colonna dei nomi della tabella.
     */
    @FXML
    private TableColumn<Contatto, String> nomeClm;

    /**
     * Bottone di apertura pop-up per aggiunta contatto.
     */
    @FXML
    private javafx.scene.control.Button addButton;
    
    /**
     * Bottone per la rimozione del/dei contatto/i.
     */
    @FXML
    private javafx.scene.control.Button removeButton;
    
    /**
     * Bottone per l'importazione di rubrica da file esterno.
     */
    @FXML
    private javafx.scene.control.Button importButton;
    
    /**
     * Bottone per l'esportazione della rubrica su file esterno.
     */
    @FXML
    private javafx.scene.control.Button exportButton;
    
    /**
     * Bottone per la ricerca di contatti all'interno della rubrica.
     */
    @FXML
    private javafx.scene.control.Button researchButton;
    
    /**
     * Bottone per resettare la ricerca della rubrica.
     */
    @FXML
    private Button resetResearch;
    
    /**
     * Campo di testa per la ricerca dei contatti tramite sottostringa nella rubrica.
     */
    @FXML
    private javafx.scene.control.TextField researchField;
    
    /**
     * Bottone per chiusura dell'interfaccia grafica.
     */
    @FXML
    private javafx.scene.control.Button exitButton;
            
    /**
     * Puntatore alla rubrica gestita.
     */
    private Rubrica rubricaPointer;
    
    /**
     * Puntatore al controller visualizzato sul "pannello del contatto".
     */
    ContattoController contattoController;
    
    
    
    
    /**
    * @brief Inizializza il controller al caricamento della scena.
    * 
    * Questo metodo viene chiamato automaticamente dal framework JavaFX
    * quando la scena associata a questo controller viene caricata.
    * 
    * @param location La posizione del file FXML associato al controller (può essere null se non fornito).
    * @param resources Le risorse internazionalizzate utilizzate per la scena (può essere null se non presenti).
    * 
    */
     @Override
    public void initialize(URL location, ResourceBundle resources) {
    
        //rendo invisibile il pannello del contatto
        contattoPane.setVisible(false);
        
        //creo la rubrica su cui lavorare
        this.rubricaPointer = new Rubrica();
        
         
       //lego le colonne della tabella ai campi nome e cognome dei contatti della rubrica 
        nomeClm.setCellValueFactory(s -> { return new SimpleStringProperty(s.getValue().getNome()); });
        cognomeClm.setCellValueFactory(new PropertyValueFactory("cognome"));  
        rubricaList.setItems(rubricaPointer.getContactList());
        
        //permetto la selezione multipla di contatti all'interno della tabella (CTRL + clickMouse)
        rubricaList.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.MULTIPLE);
        
        resetResearch.visibleProperty().bind(Bindings.createBooleanBinding(
                () -> (!researchField.getText().isEmpty()), researchField.textProperty()));
        
       
        //gestisco L'evento di selezione singola e multipla dei contatti e lo associo alla tabella
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
     
    /**
     * @brief Metodo gestione aggiunta contatto
     * 
     * Questo metodo permette l'apertura di un pop-up per le operazioni di creazione e inserimento di un nuovo contatto nella tabella 
     * 
     * @param event L'evento che ha generato l'azione di aggiunta
     * 
     * @throws IOException Eccezione per la gestione di errori durante l'apertura della finestra di aggiunta del contatto
     */
    @FXML
    private void add(javafx.event.ActionEvent event) throws IOException {
        
        //Operazioni per apertura finestra di creazione
        FXMLLoader f = App.getFXML("Contatto");
        Parent root = f.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        
        //Creazione del controller del contatto
        ContattoController controller = f.getController(); 
        if (controller != null) { //controllo effettiva creazione del controller
            controller.setController(this.rubricaPointer); 
        } else {
            System.out.println("Il controller è nullo");
        }
       
        stage.show(); //visualizzazione pop-up di aggiunta
        
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
    
    ObservableList<Contatto> temp = rubricaList.getSelectionModel().getSelectedItems();
    if(temp.isEmpty())
        return;

    if (Avviso.conferma("Attenzione", "Conferma Rimozione","Sei sicuro di voler eliminare il/i contatto/i?")) {
        
        rubricaPointer.rimuoviContatto(temp);
        contattoPane.getChildren().clear();
    
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
        
            
                Avviso.info("Avviso", null, "La rubrica è stata importata con successo");
            
            
            
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
   
        Avviso.info("Avviso", null,"La rubrica è stata esportata con successo");
        
        
    }else{
        
        System.out.println("Esportazione annullata");
        
    }
    
    }

    /**
     * @brief Ricerca contatti
     * 
     * Questo metodo permette la ricerca dei contatti tramite una stringa di testo
     * 
     * @param event L'evento che ha generato l'operazione di ricerca
     */
    @FXML
    private void research(javafx.event.ActionEvent event) {
    
    if(!researchField.getText().isEmpty()) //controllo che la barra ricerca sia vuota
        
            rubricaList.setItems(rubricaPointer.ricercaContatti(researchField.getText()).getContactList()); //visualizzo tutta la rubrica
    
        else
        
            rubricaList.setItems(rubricaPointer.getContactList()); //visualizzo la sottoRubrica
    
    
    
    }

    /**
     * @brief Chiude l'interfaccia del controller
     *
     * Questo metodo viene invocato quando l'utente preme il bottone per uscire dall'interfaccia.
     * Come conseguenza l'interfaccia viene chiusa.
     *
     * @param event L'evento che ha generato l'azione di chiusura.
     */
    @FXML
    private void goBack(javafx.event.ActionEvent event) {
    
     ((javafx.stage.Stage) exitButton.getScene().getWindow()).close();
        
    
    }

    /**
     * @brief Metodo reset ricerca
     * 
     * Questo Metodo permette il reset della ricerca, mostrando nuovamente la rubrica "originale" 
     * e svuotando la barra ricerca
     * 
     * @param event L'evento che ha generato l'operazione di reset 
     */
    @FXML
    private void resetResearch(ActionEvent event) {
        
        //resetta il testo del campo di ricerca
        researchField.setText("");
        
        //resetta la visualizzazione della lista
        rubricaList.setItems(rubricaPointer.getContactList());
        
        Platform.runLater(() -> { //Cambia il focus all'apertura della schermata
        researchField.requestFocus();
        researchField.positionCaret(0); //posizione il cursore all'inizio del textField
        });
    }
   
}