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

    private int n; //commensali
    private int b; // cavConBrocca; pos in array
    private int[] cavv; //indici: posizioni in tavola, contenuto: cavaliere

    /*COSTRUTTORE*/

    public TavRotonda (int n){

        this.n= n;
        b= 0;
        cavv  = new int[ n + n-1 ];// array quasi il doppio dello spazio
        for (int c=0; c<n; c++){

            cavv[c]= c+1;
        }
    }

    /*METODI*/
    // estraggono info
    public int quantiCavalieri(){

    return n;
    }

    public int chiHaLaBrocca(){

        return cavv[b];
    }

    public boolean ritualeCompletato(){
        //
        return n == 1;
    }


    //regole per giocare

    // fa uscire il secondo elemento:
    // costruisce una lista che ha
    // tutti i membri tranne il secondo (che è stato eliminato)
    public void serviSidro(){// METODO IMPERATIVO

        if (n > 1){
            // sovrascrittura [0,1,2,3] ->[0,0,2,3]
            cavv[b+1]= cavv [b];
            //"accorcio array", b=0 -> b=1
            b= b+1;
            // aggiorno numero cavalieri
            n= n-1;

        }
    }

    public void passaBrocca(){

        if (n>1){

            cavv[b+n]= cavv [b];
            // sposto chi ha la brocca in coda
            b= b+1;
        }
    }

    //ingloba serviSidro e passaBrocca.
    public void dopoUscitaCavaliere(){

        serviSidro();
        passaBrocca();
    }

}// Class TavRotonda
/* COMEFUNZIONA
*
*  1 2 3 4 5 6 x x x ...      | b=0, n=6
*
* servi
*  x
*  1 1 3 4 5 6 x x x ...      | b=1, n=5
*
* passa
*  x x
*  1 1 3 4 5 6 1 x x ...      | b=2, n=5
*
* servi
*  x x x
*  1 1 3 3 5 6 1 x x ...      | b=3, n=4
*
* passa
*  x x x x
*  1 1 3 3 5 6 1 3 x ...      | b=4, n=4
*
* La parte utile slitta sull'array
* */