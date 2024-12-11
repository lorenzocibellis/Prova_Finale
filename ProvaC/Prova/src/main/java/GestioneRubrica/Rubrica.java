/**
 * @file Rubrica.java
 * 
 * @brief Classe per la gestione di una rubrica di contatti.
 *
 * Questa classe consente di gestire un insieme di contatti, permettendo operazioni
 *        come aggiungere, rimuovere, cercare, importare ed esportare contatti da e verso file esterni.
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
     * la libreria javafx
     */
    private ObservableList<Contatto> contactList;
    
    /**
     * Costruttore della classe Rubrica, che sarà inizialmente vuota
     * 
     */
    public Rubrica(){
    
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
    * @return {@code true} se il contatto è stato aggiunto con successo;
    *         {@code false} altrimenti (ad esempio, se il contatto è già presente 
    *         o se l'aggiunta non è consentita per altri motivi).
    * 
    * @pre c non può essere null.
    * 
    * @post Il contatto è aggiunto alla rubrica.
    * 
    * 
    * 
    */
    public void aggiungiContatto(Contatto c){
      
         this.contactList.add(c);
        
         Collections.sort(contactList);
          
        
    }
    
    /**
     *  @brief Rimuove un contatto da una lista osservabile di contatti.
     *
     *Questo metodo consente di rimuovere un oggetto di tipo {@link Contatto.java} 
     *         da una lista osservabile ({@code ObservableList}). La rimozione può avvenire 
     *         in base a criteri definiti nell'implementazione del metodo (ad esempio, 
     *         corrispondenza con un contatto esistente nella lista).
     *
     * @param list la lista osservabile ({@code ObservableList<Contatto>}) 
     *             da cui rimuovere il contatto.
     * @return {@code true} se il contatto è stato rimosso correttamente;
     *         {@code false} se il contatto non è presente in {@code list} o per altri motivi
     *
     * @pre list non deve essere null.
     * @pre I contatti di list devono appartenere alla rubrica da cui li si vuole rimuovere
     * 
     * @post I contatti appartenente a list sono rimossi dalla rubrica
     * 
     */
    public boolean rimuoviContatto(ObservableList<Contatto> list){  
    
    
        return contactList.removeAll(list);
   
    }
    
    
    
    
    
    public ObservableList<Contatto> getContactList() {
        return contactList;
    }

    
    
    
    
    
    /**
     * @brief Ricerca nella lista osservabile i contatti avente corrispondenza con la stringa inserita dall'utente
     * 
     * @param s la stringa inserita nel textfield corrispondente dall'utente
     * 
     * @return la sotto-rubrica cui fanno parte i contatti avente corrispondenza alla stringa {@code s}
     * 
     * 
     */
    public Rubrica ricercaContatti(String s){
        Rubrica temp = new Rubrica();
        for(Contatto c : contactList){
            if(c.getNome().toLowerCase().startsWith(s) || c.getCognome().toLowerCase().startsWith(s))
                temp.aggiungiContatto(c);
        }
        return temp;
    }
    
    /**
     * @return 
     * @throws java.io.FileNotFoundException
     * @brief Importa da file esterno una rubrica intera
     * 
     *Viene richiamato questo metodo nel momento in cui l'utente preme il bottone corrispondente
     *        Si gestiscono anche i casi in cui il file scelto dall'utente non ha il formato adatto
     * 
     * @param nomefile Il nome del file da cui importare la rubrica
     * 
     * @pre nomefile deve essere una stringa non vuota
     * 
     * @post La rubrica contenuta nel file avente come nome nomefile viene caricata come rubrica nell'applicazione
     * 
     * 
     */
    public Rubrica importaRubrica(String nomefile) throws FileNotFoundException, IOException{
    
         Rubrica r = new Rubrica();

    try (BufferedReader br = new BufferedReader(new FileReader(nomefile))) {
        String line;
        br.readLine(); 

        while ((line = br.readLine()) != null) { 
            
            
            String[] fields = line.split(";", -1); // Usa -1 per mantenere i campi vuoti
            
            Contatto c = new Contatto();
            
            c.setCognome(fields[0]);
            c.setNome(fields[1]);
            c.setNumero1(fields[2].isEmpty() ? "" : fields[2]);
            c.setNumero2(fields[3].isEmpty() ? "" : fields[3]);
            c.setNumero3(fields[4].isEmpty() ? "" : fields[4]);
            c.setEmail1(fields[5].isEmpty() ? "" : fields[5]);
            c.setEmail2(fields[6].isEmpty() ? "" : fields[6]);
            c.setEmail3(fields[7].isEmpty() ? "" : fields[7]);

            r.aggiungiContatto(c); 
        
        
        }
    } catch (IOException e) {
        
        System.err.println("Errore durante la lettura del file: ");
        
    }

    return r;
        
        
    }
    
    /**
     * @throws java.io.IOException
     * @brief Esporta la rubrica intera
     * 
     * Si richiama questo metodo quando l'utente preme sul bottone corrispondente
     *        e si determina non solo la locazione del file ma anche la sua estensione
     * 
     * @param nomefile Il nome del file su cui esportare la rubrica
     * 
     * @pre nomefile deve essere una stringa non vuota
     * 
     * @post Viene creato sul computer un file contenente la rubrica 
     * 
     * 
     */
    public void esportaRubrica(String nomefile) throws IOException{
    
        
     try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(nomefile)))) {   
            
            pw.println("COGNOME;NOME;NUMERO 1;NUMERO 2;NUMERO 3;EMAIL 1;EMAIL 2; EMAIL3");   
            
            
            for(Contatto c : contactList){
                
               
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
            
            
        }catch (IOException e) {
        
        System.err.println("Errore durante la scrittura del file: ");
        
    }
        
    
    
        
    }

    
    
    
    
    
   
    
    
    
    
    
    
    
    
    
    
}
