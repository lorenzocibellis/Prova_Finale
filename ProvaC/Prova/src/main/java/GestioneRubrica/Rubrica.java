/**
 * @file Rubrica.java
 * 
 * @brief Classe per la gestione di una rubrica di contatti.
 *
 * Questa classe consente di gestire un insieme di contatti, permettendo operazioni di:
 * aggiunta, rimozione, ricerca, importazione ed esportazione della rubrica da e verso file esterni.
 */
 


package GestioneRubrica;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


public class Rubrica {
    
    /**
     * Lista osservabile dei contatti al fine di poter sfruttare al meglio 
     * la libreria javafx.
     */
    private ObservableList<Contatto> contactList;
    
    /**
     * Costruttore della classe Rubrica, che sarà inizialmente vuota.
     * 
     */
    public Rubrica(){
    
        //creazione lista di contatti
        this.contactList = FXCollections.observableArrayList();
        
    }
    
    
    /**
    *  @brief Aggiunge un contatto alla lista dei contatti.
    *
    *Questo metodo consente di aggiungere un oggetto di tipo {@link Contatto.java} a una 
    *         collezione o struttura dati (ad esempio, una lista). Se il contatto 
    *         è già presente o se ci sono condizioni particolari per l'aggiunta (come 
    *         valori null o duplicati), il metodo può gestirle restituendo un valore booleano.
    *
    * @param c il contatto da aggiungere, rappresentato come un oggetto {@link Contatto.java}.
    * 
    * @pre c non può essere null.
    * 
    * @post Il contatto è aggiunto alla rubrica.
    * 
    * 
    * 
    */
    public void aggiungiContatto(Contatto c){
      
        //aggiunta contatto alla lista di contatti
         this.contactList.add(c);
        
         //sorting della lista
         Collections.sort(contactList);
          
        
    }
    
    /**
     *  @brief Rimuove un contatto da una lista osservabile di contatti.
     *
     * Questo metodo consente di rimuovere un oggetto di tipo {@link Contatto.java} 
     * da una lista osservabile ({@code ObservableList}). La rimozione può avvenire 
     * in base a criteri definiti nell'implementazione del metodo (ad esempio, 
     * corrispondenza con un contatto esistente nella lista).
     *
     * @param list la lista osservabile ({@code ObservableList<Contatto>}) 
     *             da cui rimuovere il contatto.
     * 
     * @return {@code true} se il contatto è stato rimosso correttamente;
     *         {@code false} se il contatto non è presente in {@code list} o per altri motivi
     *
     * @pre list non deve essere null.
     * @pre I contatti di list devono appartenere alla rubrica da cui li si vuole rimuovere.
     * 
     * @post I contatti appartenente a list sono rimossi dalla rubrica.
     * 
     */
    public boolean rimuoviContatto(ObservableList<Contatto> list){  
   
        //rimozione dalla lista i contatti passati come argomento
        return contactList.removeAll(list);
   
    }
    
    
    
    
    /**
     * @brief Restituzione riferimento della lista di contatti
     * 
     * Questo metodo permette l'ottenimento del riferimento alla lista dei contatti per effettuare operazioni
     * direttamente su di essa.
     * 
     * @return Riferimento di contactList. 
     */
    public ObservableList<Contatto> getContactList() {
        
        return contactList;
        
    }

    
    
    
    
    
    /**
     * @brief Ricerca nella lista osservabile i contatti avente corrispondenza nel nominativo con la stringa inserita dall'utente
     * 
     * @param s la stringa inserita nel textfield corrispondente dall'utente
     * 
     * @return la sotto-rubrica cui fanno parte i contatti avente corrispondenza alla stringa {@code s}
     * 
     * 
     */
    public Rubrica ricercaContatti(String s){
        //creazione rubrica temporanea
        Rubrica temp = new Rubrica();
        for(Contatto c : contactList){ //ciclo di controllo su tutti i contatti della lista
            if(c.getNome().toLowerCase().startsWith(s) || c.getCognome().toLowerCase().startsWith(s)) //controllo presenza sottostringa
                temp.aggiungiContatto(c); //aggiunta contatto avente la sottostringa nel nominativo alla rubrica temporanea
        }
        //restituzione riferimento alla rubrica temporanea
        return temp;
    }
    
    /**
     * 
     * @brief Importa da file esterno una rubrica intera
     * 
     * Viene richiamato questo metodo nel momento in cui l'utente preme il bottone corrispondente
     * Si gestiscono anche i casi in cui il file scelto dall'utente non ha il formato adatto
     * 
     * @param nomefile Il nome del file da cui importare la rubrica
     * 
     * @pre nomefile deve essere una stringa non vuota
     * 
     * @post La rubrica contenuta nel file avente come nome nomefile viene caricata come rubrica nell'applicazione
     * 
     * @return Riferimento alla rubrica importata
     * @throws java.io.FileNotFoundException Eccezione per la gestione di file non trovato
     */
    public Rubrica importaRubrica(String nomefile) throws FileNotFoundException, IOException{
    
        Rubrica temp = new Rubrica(); //creazione oggetto rubrica

        //Try di apertura file e inizializzazione oggetto di tipo BufferedReader
        try (BufferedReader br = new BufferedReader(new FileReader(nomefile))) {
            //inizializzazione stringa
            String line;
            //inizializza BufferedReader per leggere linee
            br.readLine(); 

            //ciclo di lettura linee
            while ((line = br.readLine()) != null) { 

                //creazione contatto temporaneo
                Contatto c = new Contatto();
                
                String[] fields = line.split(";", -1); // Usa -1 per mantenere i campi vuoti

                //settaggio dati contatto
                c.setCognome(fields[0]);
                c.setNome(fields[1]);
                c.setNumero1(fields[2].isEmpty() ? "" : fields[2]);
                c.setNumero2(fields[3].isEmpty() ? "" : fields[3]);
                c.setNumero3(fields[4].isEmpty() ? "" : fields[4]);
                c.setEmail1(fields[5].isEmpty() ? "" : fields[5]);
                c.setEmail2(fields[6].isEmpty() ? "" : fields[6]);
                c.setEmail3(fields[7].isEmpty() ? "" : fields[7]);

                //aggiunta contatto alla rubrica importata
                temp.aggiungiContatto(c); 


            }
        } catch (IOException e) /*Cattura eccezione*/{

            System.err.println("Errore durante la lettura del file: ");
        }

        return temp;

    }
    
    /**
     * 
     * @brief Esporta la rubrica intera
     * 
     * Si richiama questo metodo quando l'utente preme sul bottone corrispondente
     * e si determina non solo la locazione del file ma anche la sua estensione
     * 
     * @param nomefile Il nome del file su cui esportare la rubrica
     * 
     * @pre nomefile deve essere una stringa non vuota
     * 
     * @post Viene creato sul computer un file contenente la rubrica 
     * 
     * @throws java.io.IOException Eccezione per errori durante la creazione del file
     */
    public void esportaRubrica(String nomefile) throws IOException{
    
     //Try per creazione del file e inizializzazione oggetto di tipo PrintWriter
     try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(nomefile)))) {   
            
            //Stampa su file della seguente linea
            pw.println("COGNOME;NOME;NUMERO 1;NUMERO 2;NUMERO 3;EMAIL 1;EMAIL 2; EMAIL3");   
            
            //ripetizione ciclo per ogni contatto presente nella lista
            for(Contatto c : contactList){
                
                //scrittura su linea del file dei dati del contatto
                pw.print(c.getCognome());
                pw.append(';');
                
                pw.print(c.getNome());
                pw.append(';');          
                
                
                String[] numeri = c.getNumeri();
                
                pw.print(numeri[0]);
                pw.append(';'); 
                pw.print(numeri[1]);
                pw.append(';'); 
                pw.print(numeri[2]);
                pw.append(';'); 
                
                String[] emails = c.getEmails();
                
                pw.print(emails[0]);
                pw.append(';'); 
                pw.print(emails[1]);
                pw.append(';'); 
                
                pw.println(emails[2]);
                
            }
            
            
        }catch (IOException e) /*Cattura eccezione*/{
        
        System.err.println("Errore durante la scrittura del file: ");
        
        }
        
    }

}
