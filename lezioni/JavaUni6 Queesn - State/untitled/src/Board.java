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
 * b.addqueen(i,j): void
 *
 * toglie regina
 * b.removequeen(i,j): void
 *
 * descrizione testuale della disposizione finale
 * b.arrangement(): String
 *
 */
//import java.util.function.*;// importo tutte le librerie possibili lol
public class Board {//scacchiera con regine sopra

    /*VARIABILI DI ISTANZA*/

    //Indici alle colonne e alle righe
    private static final String COLS = " abcdefghijklmno"; // dimensione scacchiera: <= 15x15
    private static final String ROWS = " 123456789ABCDEF";

    private final int size;
    private int queens;
    /* array
        indice: codice di riga
        valore: numero di regine collocate in quella riga
        es:
        rowAttacks: {0,1,0}
        indica che nella riga 2 ce una regina
        colAttacks: {1,0,0}
        indica che nella colonna 1 ce una regina
        in pratica:
        [ ][ ][ ]
        [x][ ][ ]
        [ ][ ][ ]
    */
    private int[]  rowAttacks; //rappresenta per ciascuna riga quanti attacchi ci sono
    private int[]  colAttacks; //
    private int[]  dg1Attacks; //
    private int[]  dg2Attacks; // somma costante
    //configurazione delle posizioni delle regine
    private String config;

    /*COSTRUTTORE*/
    public Board(int n){//rappresenta la scacchiera

        size= n;
        queens= 0;

        // array inizializzati automaticamente a 0
        rowAttacks= new int[n];
        colAttacks= new int[n];
        dg1Attacks= new int[2 * n -1];// spostare  di n-2 (?)
        dg2Attacks= new int[2 * n -1];// spostare di -2 (?)

        // inizializziamo lo stesso a zero lol
        for (int k = 0; k<n; k++){
            rowAttacks[k]=0;
            colAttacks[k]= 0;
        }

        for (int k = n; k<n-1; k++){
            dg1Attacks[k]=0;
            dg2Attacks[k]= 0;
        }


        config= "";
    }

    public int size(){//ritorna la grandezza della Board

        return this.size;
    }
    public int queensOn(){//ritorna il numero di regine in Board

        return this.queens;
    }
    public boolean underAttack(int i, int j){// controlla che una posizione sia minacciata
        //i: riga
        //j: colonna
        // i,j minacciata se ci sono regine in riga, colonna o diagonali
        int n= size;
        return ((rowAttacks[i-1] > 0 ) ||
                 (colAttacks[j-1] >0) ||
                 (dg1Attacks[i - j + n - 1] > 0) ||
                 (dg2Attacks[i + j - 2] > 0)
                );
    }

    public void addQueen(int i, int j){//aggiunge regina

        int n= size;

        queens= queens + 1 ;

        rowAttacks[i-1]             = rowAttacks[i-1] +1 ;
        colAttacks[j-1]             = colAttacks[j-1] + 1;
        dg1Attacks[i - j + n - 1]   = dg1Attacks[i - j + n -1] +1;
        dg2Attacks[i + j - 2]       = dg2Attacks[i + j - 2] +1 ;

        config= config + locCode(i,j);
    }

    public void removeQueen(int i, int j){//aggiunge regina

        int n= size;

        //bisognerebbe verificare che prima ci sia una regina ...
        queens= queens - 1 ;

        rowAttacks[i-1]             = rowAttacks[i-1] - 1 ;
        colAttacks[j-1]             = colAttacks[j-1] - 1;
        dg1Attacks[i - j + n - 1]   = dg1Attacks[i - j + n -1] - 1;
        dg2Attacks[i + j - 2]       = dg2Attacks[i + j - 2] - 1;

        // posizione del pattern
        int k= config.indexOf(locCode(i,j));
        // k+4: in string ail pattern è lungo 4
        // per togliere il pattern bisogna asltare 4 posizioni
        config= config.substring(0, k) + config.substring(k + 4);
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
