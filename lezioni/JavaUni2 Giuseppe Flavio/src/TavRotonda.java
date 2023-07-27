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
public class TavRotonda {

    /*VARIABILI DI ISTANZA*/

    private final int commensali;
    private final int cavConBrocca;
    // rappresentazione della tavola come lista
    // a uso interno della classe
    private final IntSList altriCavv;
    private final IntSList altriRovesc;

    /*COSTRUTTORI*/

    public TavRotonda (int n){

        commensali= n;
        cavConBrocca= 1;
        altriCavv = range(2, n);// cavalieri: è una lista!
        altriRovesc =IntSList.NULL_INTSLIST;
    }

    // costruttore privato che permette di creare
    // una tavola rotonda a partire da una lista
    // struttura per rappresentazione interna
    private TavRotonda (int n, int brocca, IntSList lista1, IntSList lista2){ //n: nr. cavalieri
                                                            //brocca: chi ha la brocca
                                                            //lista: lista di cavalieri presenti al tavolo (NO BROCCA)

        commensali=n;
        cavConBrocca= brocca;
        altriCavv= lista1;
        altriRovesc= lista2;
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
        return commensali == 1;
    }


    //regole per giocare

    // fa uscire il secondo elemento:
    // costruisce una lista che ha
    // tutti i membri tranne il secondo (che è stato eliminato)
    public TavRotonda serviSidro(){

        if (commensali == 1){// se ce un solo commensale

            return this;// ritorna l'oggetto identico
        } else if ( altriCavv.isNull() ) {//quando lista2 vuota

            IntSList listaRovesc= altriRovesc.reverse();
            return new TavRotonda(commensali-1, cavConBrocca, listaRovesc.cdr(), IntSList.NULL_INTSLIST );
        } else{

        return new TavRotonda(commensali-1, cavConBrocca, altriCavv.cdr(), altriRovesc ); // aggiorna il numero dei commensali
                                                                                            // toglie il commensale a cui è stato
                                                                                             // versato
        }
    }

    public TavRotonda passaBrocca(){

        if (commensali==1){

            return this;
        } else if (altriCavv.isNull()) {//quando lista2 vuota

            IntSList listaRovesc= altriRovesc.reverse();
            IntSList lista2= IntSList.NULL_INTSLIST.cons(cavConBrocca);

            return new TavRotonda(commensali, listaRovesc.car(), listaRovesc.cdr(), lista2);
        } else {

            return new TavRotonda(commensali, altriCavv.car(), altriCavv.cdr(), altriRovesc.cons(cavConBrocca)  );// int: nr commensali
                                                                            // int: il nuovo cav. con la brocca
                                                                            // la lista aggiornata senza cavaliere
                                                                            // eliminato
        }
    }

    //ingloba serviSidro e passaBrocca.
    public TavRotonda dopoUscitaCavaliere(){

        TavRotonda nuovaTavola= serviSidro();

        return nuovaTavola.passaBrocca();
    }

    //_-_-_ALTRE FUNZIONI STRUMENTALI_-_-_-_

    // crea una lista d'interi dal min al max
    public static IntSList range (int inf, int sup){

        if (inf > sup){
            return (new IntSList());
        }else{
            return range(inf+1, sup).cons(inf);
        }
    }


}// TavRotonda
