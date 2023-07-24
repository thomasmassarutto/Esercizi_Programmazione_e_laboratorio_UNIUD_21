/*
CODIFICA DI OUTPUT:
-la dimensione della scacchiera (int);
-il numero di regine collocate nella scacchiera (int);
-lista di liste:    Slist< SList<Integer> >,
                    coppie di coordinate in cui sono collocate le regine disposte sulla scacchiera
                    delle righe,
                    delle colonne,
-la codifica testuale della configurazione secondo le convenzioni in uso da parte degli scacchisti (String).

MODELLO DI OUTPUT:
<dimensioni, regine, ((r1,c1) ... (rn,cn)), text>


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
    public final int size;

    //regine allocate
    private final int queens;

    //lista di liste che rappresentano le posizioni delle regine
    private final SList<SList<Integer>> occupiedCoord;

    // codifica testuale della scacchiera
    public final String config;

    // Indici alle colonne e alle righe
    // in fase di print formattano le posizioni
    // come da prassi scacchistica
    private static final String COLS = " abcdefghijklmno";
    private static final String ROWS = " 123456789ABCDEF";


    /*COSTRUTTORI*/

    public Board(int n){//rappresenta la scacchiera

        size= n;
        queens= 0;
        occupiedCoord = new SList<SList<Integer>>();// lista di liste di interi ( (int, int), (int, int) )
        config= "";
    }

    private Board(Board b, int r, int c){//crea una nuova scacchiera con regina in posizione (r, c)

        SList<Integer> newQueenCoord = new SList<Integer>().cons(c).cons(r);
        size= b.size();
        queens= b.queensOn() +1;
        occupiedCoord= b.occupiedCoord.cons(newQueenCoord);
        config=  b.newQuenPos(r, c) + b.arrangement();


    }

    public int size(){

        return this.size;
    }
    public int queensOn(){

        return this.queens;
    }

    //Idea generale: scandaglia le liste delle posizioni occupate per vedere se sono disponibili
    public boolean underAttack(int r,int c){

        if (this.queens == 0){return false;}// se non ci sono regine, ritorno subito falso
        // posizione dove voglio mettere la regina
        int queenRow= r;
        int queenCol= c;
        int queenDiagUp= r-c;
        int queenDiagDo= r+c;
        int queens= this.queens;

        SList<SList<Integer>> list= this.occupiedCoord;// lista temporanea: verrà smantellata nel for

        // rappresentazioni delle posizioni delle regine
        int occupiedRow= list.car().car();
        int occupiedCol= list.car().cdr().car();
        int occupiedDiagUp= occupiedRow - occupiedCol;
        int occupiedDiagDo= occupiedRow + occupiedCol;


        // ciclo for che controlla iterativamente le colonne, righe / e \ occupate
        // il ciclo si ripete per ogni regina presente, per quello va da 1 a queens
        for (int i= 1; i<=this.queens; i++){
            // controllo il primo elemento della lista
            if (    queenRow == occupiedRow ||
                    queenCol == occupiedCol ||
                    queenDiagUp == occupiedDiagUp ||
                    queenDiagDo == occupiedDiagDo
            ){

                // posizione sotto attacco
                return true;
            }else{// se il primo elemento non trova corrispondenza, lo tolgo e procedo col ciclo for

                list= list.cdr();

                if(list.isNull()){return false;}// se la lista è nulla non ci sono altre posizioni sotto attacco

                occupiedRow= list.car().car();
                occupiedCol= list.car().cdr().car();
                occupiedDiagUp= occupiedRow - occupiedCol;
                occupiedDiagDo= occupiedRow + occupiedCol;
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

        return "<" + size + ", " + this.queens + ", " + this.occupiedCoord.toString() + ", \" " + this.arrangement() + " \" >";
    }

}
