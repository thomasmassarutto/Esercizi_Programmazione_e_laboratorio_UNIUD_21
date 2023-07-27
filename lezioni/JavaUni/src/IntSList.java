/*
    ASTRAZIONE SUI DATI

    11011101
    Ci vuole coerenza con i tipi, quella stringa li
    è un astrzione di un concetto ad un livello piu alto
    Caratterizzare il tipo di un dato significa dire che operazioni sono concesse
    L'astrazione sui dati è basata su questo in OOP quando si dichiarano
    nuovi tipi che rappresentano idee. Questi sono caratterizzati attraverso le
    proprietà o le operazioni consentite.
    Si introduce un nuovo tipo di dato definendo che operazioi sono
    consentite.
    Sono chiamati metodi (o procedure).
    Introducono entità usate dal programma.
    -20min-
*/

//definire un tipo simile alle liste di scheme
//Integer Schemelike List
public class IntSList {
    //lista vuota immutabile
    public static final IntSList NULL_INTSLIST = new IntSList();


    // tipo lista di interi, questa classe definisce un tipo
    // che operazioni sono concesse:
    // SCHEMA COMUNE DI COMPORTAMENTO DEL TIPO INTSLIST

    //VARIABILI DI ISTANZA
    private final boolean empty;// indica se la lista è vuota
    private final int first;//indica il primo elemento della lista
    private final IntSList rest;//indica la restante parte della lista
    /*
    * COSTRUTTORE:
    * MODO CHE SI HA PER CREARE UN NUOVO OGGETTO
    * */
    public IntSList (){//primo costruttore crea liste vuote
    //inizializzare variabili di istanza
    // il suo compito deve assegnare un valore alle variabili di istanza
        empty= true;
        first= 0;
        rest= null;//oggetto indefinito
    }

    //da qua in poi saranno definite le operazioni del tipo
    //lo scopo è far somigliare il tipo IntSList
    //a quello di scheme
    /*TARGET OPERAZIONI: SIMILI A QUELLE DI SHEME
    *
    * DICHIARAZIONE DI INTENTI: PROTOCOLLO
    * PROTOCOLLO PER INTERAGIRE CON oggetti di tipo IntSList
    *
    * null              new IntSList()  : IntSList
    * (null? S)         s.isNull()      : bool
    * (car S)           s.car()         : int
    * (cdr S)           s.cdr()         : IntSList
    * (cons n S)        s.cons(n)       : IntSList
    *
    * OPERAZIONI SULLE LISTE
    *
    * length
    * list-ref
    * equal
    * append
    * reverse
    * VOGLIO SIMULARE QUESTE OPERAZIONI IN JAVA, POSSO FARE SOLO QUESTE OPERAZIONI
    */

    public IntSList (int n, IntSList s){// secondo costruttore: crea liste non vuote
        //inizializzare variabili di istanza
        // il suo compito deve assegnare un valore alle variabili di istanza
        empty= false;
        first= n;
        rest= s;//oggetto indefinito
    }
/*
* COME FA JAVA A CAPIRE CHE COSTRUTTORE USARE?
*
* IN BASE AGLI ARGOMENTI CHE HA IN INPUT, NON PUOI CREARE DUE COSTRUTTORI CON
* LO STESSO INPUT ALTRIMENTI JAVA NON SA CHI USARE
* IN QUESTO CASO FUNZIONA PERCHE IL PRIMO NON HA INPUT, MENTRE IL SECONDO NE HA 2
* */
    public boolean isNull(){
        return this.empty;
    }

    public int car(){
        return this.first;
    }
    public IntSList cdr(){
        return rest;
    }

    public IntSList cons(int n){        //uso il 2 costruttore per creare una nuova IntSList
        return new IntSList(n, this);// il destinatario della richiesta noto in runtime
                                        // oggetto che richiede la funzione lista.cona
                                        // pippo.cons(n) -> this fa riferimento a pippo
    }

    //length
    public int length (){

        if (this.isNull()){

            return 0;
        }else {

            return this.cdr().length() + 1;
        }

    }

    //list-ref
    public int listRef (int i){
        if (i==0){
        return car(); //funziona anche this.car() oppure first
        }else{
            return cdr().listRef(i-1);
        }
    }

    //equal
    public boolean equals (IntSList s){

        if (isNull() || s.isNull()){

            return (isNull() && s.isNull()) ;
        }else if (car() == s.car() ){

            return cdr().equals(s.cdr());
        }else{

            return false;
        }


    }

    //append
    public IntSList append (IntSList s){

        if ( isNull() ){
            return s;
        }else {

            return (this.cdr().append(s)).cons(this.car()); //appende s alla coda di this e poi aggiunge in testa il car
        }
    }

    //reverse
    public IntSList reverse(){

        //return reverseRec(NULL_INTSLIST);
        return reverseRec(new IntSList());
    }

    private IntSList reverseRec(IntSList r){

        if ( isNull() ){
            return r;
        }else {

            return cdr().reverseRec( r.cons(car()) );
        }
    }
    //-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_
    //mi permette di stampare la lista come stringa di caratteri
    //OVERRIDE PER PROCEDURA STANDARD: viene usato al posto della procedura standard
    //ORA IntSList è visto in output come un tipo stringa, altrimenti
    //con un print si vedrebbe l'indirizzo in memoria
    public String toString (){
        String desc ="(";
        if (!isNull() ){

            desc = desc + car();
            IntSList resto = cdr();

            while (!resto.isNull()){
            desc = desc + ", " + resto.car();
            resto= resto.cdr();
            }
        }

        return desc + ")";
    }

}
