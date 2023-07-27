/*
 * OGGETTO: TAVOLA ROTONDA
 *
 * Le principali informazioni che dobbiamo ottenere:
 * Nr. Cavalieri rimasti
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

// usa le liste d'interi IntSList() per rappresentare una tavola
public class TavRotondaV2 {

    /*VARIABILI DI ISTANZA*/

    private final int commensali;
    private final int cavConBrocca;
    // rappresentazione della tavola come lista
    // a uso interno della classe
    private final IntSList altriCavv;

    /*COSTRUTTORI*/

    public TavRotondaV2(int n){

        commensali= n;
        cavConBrocca= 1;
        altriCavv = range(2, n);// cavalieri: è una lista!
    }

    // costruttore privato che permette di creare
    // una tavola rotonda a partire da una lista
    // struttura per rappresentazione interna
    private TavRotondaV2(int n, int brocca, IntSList lista){ //n: nr. cavalieri
                                                            //brocca: chi ha la brocca
                                                            //lista: lista di cavalieri presenti al tavolo (NO BROCCA)

        commensali=n;
        cavConBrocca= brocca;
        altriCavv= lista;
    }

    /*METODI*/
    // estraggono info
    public int quantiCavalieri(){

    return commensali;
    }

    public int chiHaLaBrocca(){

        //Il primo cavaliere della lista avrà sempre la brocca.
        return cavConBrocca;
    }

    public boolean ritualeCompletato(){
        // se oltre al tipo con la brocca non ce nessuno
        return altriCavv.isNull();
    }


    //regole per giocare

    // fa uscire il secondo elemento:
    // costruisce una lista che ha
    // tutti i membri tranne il secondo (che è stato eliminato)
    public TavRotondaV2 serviSidro(){

        // ABCDE -> CDE -> ACDE
        if (commensali == 1){// se ce un solo commensale

            return this;// ritorna l'oggetto identico
        }else{

        return new TavRotondaV2(commensali-1, cavConBrocca, altriCavv.cdr() ); // aggiorna il numero dei commensali
                                                                                // toglie il commensale a cui è stato
                                                                                // versato
        }
    }

    // IN PRATICA: muove il primo elemento alla fine della lista
    public TavRotondaV2 passaBrocca(){

        if (commensali==1){

            return this;
        }else {

            IntSList coda= IntSList.NULL_INTSLIST.cons(cavConBrocca);// lista composta da solo il tipo con brocca
            IntSList nuovaLista= altriCavv.cdr().append(coda);  //tolgo il primo elemento degli altri cav e aggiungo
                                                                // alla lista "coda" (il cavaliere che prima aveva
                                                                // la brocca)

            return new TavRotondaV2(commensali, altriCavv.car(), nuovaLista );// int: nr commensali
                                                                            // int: il nuovo cav. con la brocca
                                                                            // la lista aggiornata senza cavaliere
                                                                            // eliminato
        }
    }

    //ingloba serviSidro e passaBrocca.
    public TavRotondaV2 dopoUscitaCavaliere(){

        TavRotondaV2 nuovaTavola= serviSidro();

        return nuovaTavola.passaBrocca();
    }

    //_-_-_ALTRE FUNZIONI STRUMENTALI_-_-_-_

    // crea una lista d'interi dal min al max
    private static IntSList range (int inf, int sup){

        if (inf > sup){
            return (new IntSList());
        }else{
            return range(inf+1, sup).cons(inf);
        }
    }


}// TavRotonda
