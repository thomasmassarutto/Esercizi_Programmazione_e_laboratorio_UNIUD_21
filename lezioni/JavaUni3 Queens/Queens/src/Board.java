/*
* Board b
* new Board( n )
*
* METODI
*
* grandezza della scacchiera
* b.size(): int
*
* quante regine sono disposte sulla scacchiera
* b.queensOn(): int
*
* posso disporre una regina in una data casella?
* b.underAttack(i,j): boolean
*
* aggiunge una regina
* b.addqueen(i,j): Board
*
* descrizione testuale della disposizione finale
* b.arrangement(): String
*
*/
import java.util.function.*;// importo tutte le librerie possibili lol
public class Board {//scacchiera con regine sopra

    /*VARIABILI DI ISTANZA*/

    //Indici alle colonne e alle righe
    private static final String COLS = " abcdefghijklmno"; // dimensione scacchiera: <= 15x15
    private static final String ROWS = " 123456789ABCDEF";

    private final int size;
    private final int queens;
    //attack: nome
    //BiPredicate<Integer,Integer>: tipo coppia di interi e restituisce un valore booleano
    //es: se gli do come valori 2, 5 restituisce vero se la casella è minacciata
    private final BiPredicate<Integer,Integer> attack;// Z x Z -> bool
    //configurazione delle posizioni delle regine
    private final String config;

    /*COSTRUTTORE*/
    public Board(int n){//rappresenta la scacchiera

        size= n;
        queens= 0;
        attack= (x,y) -> false;// non ci sono regine, non ci sono posizioni minacciate
        config= "";
    }

    //aggiunge regina a posizione i, j
    private Board(Board b, int i, int j){//Board: scacchiera precedente. i,j: posizione nuova regina

        size= b.size();
        queens= b.queensOn() +1;
        attack= (x,y)->( (x == i) ||        // sta nella stessa riga
                         (y == j) ||        // sta nella stessa colonna
                         (x-y == i-j) ||    // sta nella stessa diagonale /
                         (x+y == i+j) ||    // sta nella stessa diagonale \
                         b.underAttack(x,y) // era gia minacciata dalle precedenti regine
                        );
        config= b.arrangement() + locCode(i,j);
    }

    public int size(){//ritorna la grandezza della Board

        return this.size;
    }
    public int queensOn(){//ritorna il numero di regine in Board

        return this.queens;
    }
    public boolean underAttack(int i, int j){// controlla che una posizione sia minacciata

        return attack.test(i,j);// invio ad attack le coordinate i,j
    }

    public Board addQueen(int i, int j){//aggiunge regina

        return new Board(this, i, j);
    }

    public String arrangement(){// rapp. testuale

        return this.config;
    }

    /*ALTRI METODI UTILI*/

    //override per stampare
    public String toString(){

        return "Q [" + arrangement() + "]";
    }

    // crea una stringa con le coordinate della
    // regina aggiunta (i=colonna,j=riga)
    private static String locCode (int i, int j){

        return " " + COLS.charAt(j) + ROWS.charAt(i) + " ";
    }
}
