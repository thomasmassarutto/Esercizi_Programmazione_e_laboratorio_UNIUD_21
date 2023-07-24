import java.util.function.BiPredicate;

/*
var di istanza:
        -la dimensione della scacchiera (int);
        -il numero di regine collocate nella scacchiera (int);
        -4 liste di indici (IntSList oppure SList<Integer>),
           per rappresentare rispettivamente le codifiche numeriche
            delle righe,
            delle colonne,
            delle diagonali ascendenti verso destra e
            delle diagonali discendenti verso
            NB: per convenzione le diagonali salgono verso dx e scendono verso sx
           destra che sono minacciate da una regina collocata sulla scacchiera;
        -la codifica testuale della configurazione secondo le convenzioni in uso da parte degli scacchisti (String).
protocollo pubblico:

    new Board(n)            costruttore della scacchiera n x n vuota;
    size()                  int: dimensione n della scacchiera b;
    queensOn()              int: numero di regine collocate nella scacchiera b;
    underAttack(i,j)        bool: la posizione <i,j> e' minacciata?
    addQueen(i,j)           Board:  scacchiera che si ottiene dalla configurazione b
                                    aggiungendo una nuova regina in posizione <i,j>
                                    (si assume che la posizione non sia minacciata);
    arrangement() :         String: descrizione "esterna" della configurazione
                                    (convenzioni scacchistiche).

*/
public class Board {
    /*VARIABILI DI ISTANZA*/

    // dimensione scacchiera
    private final int size;

    //regine allocate
    private final int queens;

    // codifica delle righe minacciate
    private final SList<Integer> unRows;

    // codifica delle colonne minacciate
    private final SList<Integer> unCols;

    // codifica delle diagonali / minacciate
    private final SList<Integer> unDiagUp;

    // codifica delle diagonali \ minacciate
    private final SList<Integer> unDiagDo;

    // codifica testuale della scacchiera
    private final String config;

    // Indici alle colonne e alle righe
    // in fase di print formattano le posizioni
    // come da prassi scacchistica
    private static final String COLS = " abcdefghijklmno";
    private static final String ROWS = " 123456789ABCDEF";


    /*COSTRUTTORI*/

    public Board(int n){//rappresenta la scacchiera

        size= n;
        queens= 0;
        unRows= new SList<Integer>();
        unCols= new SList<Integer>();
        unDiagUp= new SList<Integer>();
        unDiagDo= new SList<Integer>();
        config= "";
    }

    private Board(Board b, int r, int c){//crea una nuova scacchiera con regina in posizione (r, c)

        size= b.size();
        queens= b.queensOn() +1;
        unRows= b.unRows.cons(r);
        unCols= b.unCols.cons(c);
        unDiagUp= b.unDiagUp.cons(r - c);
        unDiagDo= b.unDiagDo.cons(r + c);
        config= b.arrangement() + b.newQuenPos(c, r);
    }

    public int size(){

        return this.size;
    }
    public int queensOn(){

        return this.queens;
    }

    //Idea generale: scandaglia le liste delle posizioni occupate per vedere se sono disponibili
    public boolean underAttack(int r,int c){

        // posizione dove voglio mettere la regina
        int queenRow= r;
        int queenCol= c;
        int queenDiagUp= r-c;
        int queenDiagDo= r+c;

        //liste di posizioni sotto attacco
        SList<Integer> unRows= this.unRows;
        SList<Integer> unCols= this.unCols;
        SList<Integer> unDiagUp= this.unDiagUp;
        SList<Integer> unDiagDo= this.unDiagDo;

        // ciclo for che controlla iterativamente le colonne, righe / e \ occupate
        // il ciclo si ripete per ogni regina presente, per quello va da 1 a queens
        for (int i= 1; i<= this.queens ;i++){
            // controllo il primo elemento della lista
            if (queenRow == unRows.car() ||
                queenCol == unCols.car() ||
                queenDiagUp == unDiagUp.car() ||
                queenDiagDo == unDiagDo.car()
                ){

                // posizione sotto attacco
                return true;
            }else{// se il primo elemento non trova corrispondenza, lo tolgo e procedo col ciclo for

                unRows= unRows.cdr();
                unCols= unCols.cdr();
                unDiagUp= unDiagUp.cdr();
                unDiagDo= unDiagDo.cdr();
            }
        }// for

        // se il for non conclude nulla, vuol dire che la posizione è libera: NON sotto attacco
        return false;
    }

    public Board addQueen(int r,int c){

        return new Board(this, r, c);
    }


    /*METODI PRIVATI*/
    private String arrangement(){// rapp. testuale della tavola

        return this.config;
    }

    //identifica la regina
    private String newQuenPos(int r, int c){

        return " " + COLS.charAt(c) + ROWS.charAt(r) + " ";
    }

    /*METODI OVERRIDE*/

    // rappresentazione testuale della scacchiera
    // evidenziate le posizioni sotto attacco
    public String toString(){

        return "<" + size + " " + this.queens + " " + this.unRows.toString() + " " + this.unCols.toString() + " " + this.unDiagUp.toString() + " " + this.unDiagDo.toString() + " " + this.arrangement() + ">";
    }

}
