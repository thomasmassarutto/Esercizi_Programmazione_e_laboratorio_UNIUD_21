/*
 * OGGETTO: TAVOLA ROTONDA
 *
 * Le principali informazioni che dobbiamo ottenere:
 * Nr. cavalieri rimasti
 * Cavaliere con la brocca
 * Stato: siamo alla fine?
 *
 * Regole principali:
 * Versa
 * Passa
 *
 * _-_-_-_-PROTOCOLLO
 * new TavRotonda(int n)    : TavRotonda
 *
 * quantiCavalieri()        : int
 *
 * chiHaLaBrocca()          : int (identificatore)
 *
 * ritualeCompletato()      : boolean
 *
 * REGOLE
 *
 * serviSidro()             :TavRotonda (senza un cavaliere)
 *
 * passaBrocca()            :TavRotonda
 *
 * */

// usa le liste di interi IntSList() per rappresentare una tavola
public class TavRotondaV1 {

    /*VARIABILI DI ISTANZA*/
    // rappresentazione della tavola come lista
    // ad uso interno della classe
    private IntSList cavalieri;

    /*COSTRUTTORI*/

    public TavRotondaV1(int n){

        cavalieri = range(1, n);// cavalieri: è una lista!
    }

    // costruttore privato che permette di creare
    // una tavola rotonda a partire da una lista
    private TavRotondaV1(IntSList lista){

        cavalieri= lista;
    }

    /*METODI*/
    // estraggono info
    public int quantiCavalieri(){

        return cavalieri.length();
    }

    public int chiHaLaBrocca(){

        // per come viene implementato il programma
        // il primo elemento della lista ha la brocca
        return cavalieri.car();
    }

    public boolean ritualeCompletato(){

        return cavalieri.cdr().isNull();// se oltre al tipo con la brocca non ce nessuno

        /*
        if ( cavalieri.length() == 1){ //cavalieri.cdr().isNull();

            return true;
        }else {

            return false;
        }
        */
    }


    //regole per giocare

    // fa uscire il secondo elemento:
    // costruisce una lista che ha
    // tutti i membri tranna il secondo
    public TavRotondaV1 serviSidro(){

        //i primi due elementi vengono tolti,
        // poi viene aggiunto la testa della
        // lista originale: ABCDE -> CDE -> ACDE
        IntSList nuovaTavola= ( cavalieri.cdr().cdr() ).cons(cavalieri.car());

        return new TavRotondaV1(nuovaTavola);
    }

    // muove il primo elemento alla fine della lista
    public TavRotondaV1 passaBrocca(){
        // il primo elemento della lista che
        // diventerà la coda della nuova lista
        IntSList coda= IntSList.NULL_INTSLIST.cons(cavalieri.car());
        IntSList nuovaTavola= (cavalieri.cdr()).append(coda);

        return new TavRotondaV1(nuovaTavola);
    }

    //ingloba serviSidro e passaBrocca: OTTIMIZZAZIONE
    public TavRotondaV1 dopoUscitaCavaliere(){

        IntSList coda= IntSList.NULL_INTSLIST.cons(cavalieri.car());

        //tolgo due e il primo lo metto in coda
        IntSList nuovaTavola= (cavalieri.cdr().cdr() ).append(coda);

        return new TavRotondaV1(nuovaTavola);
    }

    //_-_-_ALTRE FUNZIONI STRUMENTALI_-_-_-_

    // crea una lista di interi dal min al max
    private static IntSList range (int inf, int sup){

        if (inf > sup){
            return (new IntSList());
        }else{
            return range(inf+1, sup).cons(inf);
        }
    }


}// TavRotonda
