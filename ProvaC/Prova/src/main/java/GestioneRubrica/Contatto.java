/**
 * @file Contatto.java
 * 
 * @brief Rappresenta un contatto all'interno di una rubrica.
 *        
 *Questa classe contiene le informazioni necessarie come nome, cognome,
 *        numeri di telefono e indirizzi email (gli ultimi due campi di informazioni possono avere un massimo di 3 elementi ognuno).
 *        Consente di gestire e modificare i dati del contatto.
 *        Implementa l'interfaccia {@code Comparable} per consentire il confronto e l'ordinamento.
 *        Si sfruttano i metodi setter e getter per far modificare o prelevare un valore, 
 *        ma senza fornire direttamente un accesso ai singoli campi del contatto
 * 
 */


package GestioneRubrica;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


public class Contatto implements Comparable<Contatto> {
   
    /**
     * Nome del contatto.
     */
    private String nome;

    /**
     * Cognome del contatto.
     */
    private String cognome;

    /**
     * Array di numeri di telefono del contatto, sono opzionali (fino a 3).
     */
    private String[] numeri;

    /**
     * Array di indirizzi email del contatto, sono opzionali (fino a 3).
     */
    private String[] emails;

    
    /**
     * Costruttore predefinito della classe Contatto,
     * il quale assegna un valore predefinito agli attibuti 
     * del contatto nel momento in cui viene creato l'oggetto.
     * 
     */
    
    public Contatto() {
       
        
        this.nome = null;
        this.cognome = null;
        numeri = new String[3];
        emails = new String[3];
    
        numeri[0] = null;
        numeri[1] = null;
        numeri[2] = null;
    
    
        emails[0] = null;
        emails[1] = null;
        emails[2] = null;
    
    
    
    
    }

    /**
     * @brief Imposta il nome del contatto. 
     * 
     * @param nome Il nome del contatto.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @brief Imposta il cognome del contatto.
     *
     * @param cognome Il cognome del contatto.
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * @brief Imposta il primo numero di telefono del contatto.
     *
     * @param numero Il numero di telefono da impostare.
     */
    public void setNumero1(String numero) {
        numeri[0] = numero;
    }

    /**
     * @brief Imposta il secondo numero di telefono del contatto.
     *
     * @param numero Il numero di telefono da impostare.
     */
    public void setNumero2(String numero) {
        numeri[1] = numero;
    }

    /**
     * @brief Imposta il terzo numero di telefono del contatto.
     *
     * @param numero Il numero di telefono da impostare.
     */
    public void setNumero3(String numero) {
        numeri[2] = numero;
    }

    /**
     * @brief Imposta il primo indirizzo email del contatto.
     *
     * @param email L'indirizzo email da impostare.
     */
    public void setEmail1(String email) {
        emails[0] = email;
    }

    /**
     * @brief Imposta il secondo indirizzo email del contatto.
     *
     * @param email L'indirizzo email da impostare.
     */
    public void setEmail2(String email) {
        emails[1] = email;
    }

    /**
     * @brief Imposta il terzo indirizzo email del contatto.
     *
     * @param email L'indirizzo email da impostare.
     */
    public void setEmail3(String email) {
        emails[2] = email;
    }

    /**
     * @brief Restituisce il nome del contatto.
     *
     * @return il nome del contatto come {@code String}.
     */
    public String getNome() {
        return nome;
    }

    /**
     * @brief Restituisce il cognome del contatto.
     *
     * @return Il cognome del contatto come {@code String}.
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * @brief Restituisce l'array di numeri di telefono del contatto.
     *
     * @return Un array di stringhe contenente i numeri di telefono del contatto.
     */
    public String[] getNumeri() {
        return numeri;
    }

    /**
     * @brief Restituisce l'array di indirizzi email del contatto.
     *
     * @return un array di stringhe contenente gli indirizzi email del contatto.
     */
    public String[] getEmails() {
        return emails;
    }

    /**
     * @brief Modifica i dettagli del contatto.
     * 
     * Questo metodo permette di aggiornare le informazioni esistenti di un contatto.
     */
    public void modificaContatto() {
        // Da implementare: definire come aggiornare i dettagli del contatto.
    
    
    
    
    
    
    
    
    }

    
    /**
     * @brief Confronta questo contatto con un altro contatto basandosi sul cognome e sul nome.
     *
     * L'ordinamento viene effettuato in ordine alfabetico, considerando prima il cognome
     * e poi il nome.
     *
     * @param c l'oggetto di tipo {@link Contatto.java}, con cui confrontare
     * 
     * @return un valore negativo, zero o positivo se questo contatto è rispettivamente
     *         minore, uguale o maggiore rispetto al contatto specificato.
     * 
     * @throws da determinare quali eccezioni usare, ad esempio
     *         NullPointerException se il contatto fornito è {@code null}.
     */
    @Override
    public int compareTo(Contatto c) {
      
      if(c == null){ 
            throw new NullPointerException("Il contatto fornito è null");
        }
        
        // Confronta i cognomi
        int confronto = this.cognome.compareTo(c.getCognome());
        //se il cognome dell'istanza attuale precede il cognome del contatto passato, allora ritorna un valore negativo
        //se i cognomi sono uguali allora ritorna 0
        //se il cognome dell'istanza attuale seguee il cognome del contatto passato, allora ritorna un valore positivo
        if(confronto != 0){
            return confronto; //confronta i cognomi e determina se this.cognome precede il cognome del contatto passato
        }
        
        return this.nome.compareTo(c.getNome()); //richiamato se i cognomi sono uguali ed è necessario verificare i nomi

    
    }



}
