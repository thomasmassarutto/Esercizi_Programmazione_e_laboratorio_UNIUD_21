import puzzleboard.PuzzleBoard;

import java.util.*;
public class Board {

    // variabili mnemoniche
    public static final int VUOTO=0;// rappresentazione dello spazio vuoto
    public static final int ROW=0;
    public static final int COL=1;

    // variabili istanza
    private final int dimension;
    public int[][] board;

    // costruttore pubblico
    public Board(int n){
        this.dimension= n;
        this.board= new int[n][n];

        //creazione stack temporaneo
        Stack<Integer> nbrs= new Stack<>();

        //popolazione dello stack
        for (int i = 0; i <= (this.dimension * this.dimension) - 1; i++) {  // Intervallo:  [1, n^2 - 1] e li inserisce nello stack
            nbrs.push(i);
        }

        // randomizzo valori
        Collections.shuffle(nbrs);

        // riempio l'array
        for (int i=0; i<this.dimension; i++){
            for (int j=0; j<this.dimension; j++){
                this.board[i][j]= nbrs.pop();
            }
        }
    }

    // metodi

    // Un metodo per verificare se i tasselli sono ordinati;
    public boolean isSorted(){
        // variabile di confronto
        int k=1;
        for (int i = 0; i < this.dimension; i++) {
            for (int j = 0; j < this.dimension; j++) {
                if (this.board[i][j] == k){
                    k++;
                }else if((i== (this.dimension - 1) && j== (this.dimension - 1)) ){
                    return (this.board[i][j] == VUOTO) ? true : false;
                }else{
                    return false;
                }

            }
        }
        return true;
    }

    // Un metodo per verificare se un dato tassello può essere spostato;
    public boolean canBeMoved(int tile) {
        int[] coordVuoto= coord(VUOTO);
        int[] coordTile= coord(tile);

        // commenti riferiti dal punto di vista del VUOTO:
        // quando il tile si trova SOPRA alla casella vuota (riga +1; stessa colonna )
        if(coordVuoto[ROW] +1 == coordTile[ROW] && coordVuoto[COL] == coordTile[COL]){
            return true;
        }
        // quando il tile si trova SOTTO alla casella vuota (riga -1; stessa colonna)
        else if (coordVuoto[ROW] -1 == coordTile[ROW] && coordVuoto[COL] == coordTile[COL]) {
            return true;
        }
        // quando il tile si trova SINISTRA alla casella vuota (stessa riga; colonna -1)
        else if (coordVuoto[ROW]  == coordTile[ROW] && coordVuoto[COL] -1 == coordTile[COL]) {
            return true;
        }
        // quando il tile si trova DESTRA alla casella vuota (stessa riga; colonna +1)
        else if(coordVuoto[ROW]  == coordTile[ROW] && coordVuoto[COL] +1 == coordTile[COL]){
            return true;
        }
        // opzione di default
        else {
            return false;
        }

    }

    // Un metodo per mostrare in forma testuale (stringa) la configurazione
    public String toString(){
        String theTable="";
        for (int i = 0; i < this.dimension; i++) {
            for (int j = 0; j < this.dimension; j++) {
                if (this.board[i][j] == 0){
                    theTable = theTable + "|" + "X" + "|";
                }else {
                    theTable = theTable + "|" + this.board[i][j] + "|";
                }
            }
            theTable= theTable + "\n";
        }

        return theTable;
    }


    // Un metodo per spostare un dato tassello.
    public void moveTile(int tile){
        int[] coordVuoto= coord(VUOTO);
        int[] coordTile= coord(tile);

        if (canBeMoved(tile)){
            this.board[coordVuoto[ROW]][coordVuoto[COL]]= tile;
            this.board[coordTile[ROW]][coordTile[COL]]= VUOTO;
        }
    }

    // extra

    // ritorna le coordinate del valore cercato
    public int[] coord(int x){

        int[] xCoord= {-99,-99};// inizializzazione con valori che non hanno proprio senso

        // ricerca riga per riga, colonna per colonna
        for (int i= 0; i< this.dimension; i++){
            for (int j= 0; j< this.dimension; j++){
                if (this.board[i][j]== x){
                   xCoord[ROW]=i;
                   xCoord[COL]=j;
                   return xCoord;
                }
            }
        }
        return xCoord;
    }

}
