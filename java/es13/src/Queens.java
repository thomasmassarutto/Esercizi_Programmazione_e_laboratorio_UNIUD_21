
/*
 * Rompicapo delle "n regine"
 *
 * Ultimo aggiornamento: 12/04/2018
 *
 *
 * Dato astratto "configurazione della scacchiera":  Board
 *
 * Operazioni:
 *
 *   new Board( int n )           :  costruttore (scacchiera vuota)
 *
 *   size()                       :  int
 *
 *   queensOn()                   :  int
 *
 *   underAttack( int i, int j )  :  boolean
 *
 *   addQueen( int i, int j )     :  Board
 *
 *   arrangement()                :  String
 *
 *
 * Board b;
 *
 *   new Board(n)           costruttore della scacchiera n x n vuota;
 *   b.size()               dimensione n della scacchiera b;
 *   b.queensOn()           numero di regine collocate nella scacchiera b;
 *   b.underAttack(i,j)     la posizione <i,j> e' minacciata?
 *   b.addQueen(i,j)        scacchiera che si ottiene dalla configurazione b
 *                          aggiungendo una nuova regina in posizione <i,j>
 *                          (si assume che la posizione non sia minacciata);
 *   b.arrangement() :      descrizione "esterna" della configurazione
 *                          (convenzioni scacchistiche).
 */

import queens.*;
public class Queens {

    /*FUNZIONI PARTE I********************************/
    /*
     * I. Numero di soluzioni:
     *
     *
     * Il numero di modi diversi in cui si possono disporre n regine
     *
     *   numberOfSolutions( n )
     *
     * in una scacchiera n x n e' dato dal numero di modi diversi in
     * cui si puo' completare la disposizione delle regine a partire
     * da una scacchiera n x n inizialmente vuota
     *
     *   numberOfCompletions( new Board(n) )
     */

    public static int numberOfSolutions( int n ) {

        return numberOfCompletions( new Board(n) );
    }


    /*
     * Il numero di modi in cui si puo' completare la disposizione
     * a partire da una scacchiera b parzialmente configurata
     *
     *   numberOfCompletions( b )   : int
     *
     * dove k regine (0 <= k < n) sono collocate nelle prime k righe
     * di b, si puo' determinare a partire dalle configurazioni
     * che si ottengono aggiungendo una regina nella riga k+1 in tutti
     * i modi possibili (nelle posizioni che non sono gia' minacciate)
     *
     *   for ( int j=1; j<=n; j=j+1 ) {
     *     if ( !b.underAttack(i,j) ) { ... b.addQueen(i,j) ... }
     *   }
     *
     * calcolando ricorsivamente per ciascuna di queste il numero
     * di modi in cui si puo' completare la disposizione
     *
     *   numberOfCompletions( b.addQueen(i,j) )
     *
     * e sommando i valori che ne risultano
     *
     *   count = count + numberOfCompletions( ... )
     *
     * Se invece la scacchiera rappresenta una soluzione (q == n)
     * c'e' un solo modo (banale) di completare la disposizione:
     * lasciare le cose come stanno!
     */

    private static int numberOfCompletions( Board b ) {
        //System.out.println(  b.toString()); // DEBUG: decommenta per vedere rapp. interna

        int n = b.size();
        int q = b.queensOn();

        if ( q == n ) {

            return 1;

        } else {

            int i = q + 1;
            int count = 0;

            for ( int j=1; j<=n; j=j+1 ) {
                if ( !b.underAttack(i,j) ) {

                    count = count + numberOfCompletions( b.addQueen(i,j) );
                }}

            return count;
        }
    }

    /*FUNZIONI PARTE II********************************/

    // funzione fatta in classe: ritorna la lista delle soluzioni corrette
    // utilizzando listOfCompletions( Board )
    public static SList<Board> listfSolutions( int n ) {

        SList<Board> chessboardList= listOfCompletions( new Board(n) );
        return chessboardList;
    }

    // compila la lista delle soluzioni (Slist<Board>) aggiungendo le disposizioni
    // di board con queens == size.
    // ls(Board', Board'', ....)
    private static SList<Board> listOfCompletions (Board board){

        int n= board.size();
        int q= board.queensOn();

        if (q == n){
            // se il numero delle regine è uguale al numero
            // delle righe (o colonne) della scacchiera
            // c'è una soluzione da ritornare
            return new SList<Board>().cons( board );
        }else{// quando q< n

            // variabile contatore
            SList<Board> list= new SList<Board>();
            // i= indice di riga
            int i= q +1;// prima riga vuota. conta dall'alto al basso

            // controlliamo le colonne: j rappresenta l'indice di colonna
            for (int j= 1; j<=n; j++){

                // se la casella (i,j) non è minacciata aggiungo una
                // regina aggiornando la variabile count:
                // count = count + numberOfCompletions( board.addQueen(i,j) )
                // numberOfCompletions( board.addQueen(i,j) return 1 se q==n
                //
                if ( !board.underAttack(i, j) ){

                    list = list.append( listOfCompletions(board.addQueen(i,j)) );
                }
            }//for

            //finito il ciclo ritorno la variabile count
            return list;
        }
    }

    public static void gui(SList<Board> boardList){

        // utilizza il primo elemento per determinare la size della board
        // NB: tutte le board devo avere la stessa size!
        int chessboardDimension= boardList.car().size();

        // creazione chessboard
        ChessboardView gui = new ChessboardView( chessboardDimension );

        while (!boardList.isNull()){
            // fintantochè ci sono elementi il lista fa partire gui
            gui.setQueens(boardList.car().config );
            boardList=boardList.cdr();
        }

    }


    public static void main( String args[] ) {

        int n= 5;// dimensione scacchiera

        /*PARTE I*/
        int nrOfSol= numberOfSolutions(n);
        System.out.println( nrOfSol );

        /*PARTE II*/
        gui( listfSolutions(n) );

        // nb: parte I e II funzionano indipendentemente

    }

}  // class Queens
