class Main{
    public static void main(String[] args) {

        System.out.println(Queens.listOfSolutions(6));
    }
}

/*
* versione finale di Queens
*   la prima parte della classe si occupa di contare le soluzioni
*   la seconda le lista
*
* */
public class Queens {

//I PARTE: numero delle soluzioni possibili
    public static int numberOfSolutions(int n){

        return numberOfCompletions (new Board(n));
    }

    // completamenti validi
    private static int  numberOfCompletions (Board board){

        int n= board.size();
        int q= board.queensOn();

        if (q == n){// numero regine = dimensine scacchiera

            return 1;
        }else{// quando q < n

            // si fa un conteggio
            int count= 0;
            // i= indice di riga
            int i= q + 1;

            // controlla le colonne: j rappresenta l'indice di colonna
            for (int j= 1; j<=n; j++){

                if ( !board.underAttack(i, j) ){

                    board.addQueen(i,j);
                    count = count + numberOfCompletions( board );
                    // ogni regina poi viene anche tolta
                    board.removeQueen(i,j);
                }
            }//for

            //finito il ciclo ritorno la variabile count
            return count;
        }
    }

//II PARTE: numero delle soluzioni possibili

    //numero di soluzioni data una scacchiera nxn
    public static SList<String> listOfSolutions(int n){

        return listOfCompletions (new Board(n));
    }

    // completamenti validi
    private static SList<String> listOfCompletions (Board board){

        int n= board.size();
        int q= board.queensOn();

        if (q == n){

            return new SList<String>().cons( board.toString() );
        }else{// quando q< n

            // variabile contatore
            SList<String> list= new SList<String>();
            // i= indice di riga
            int i= q + 1;// prima riga vuota. conta dall'alto al basso

            // controlliamo le colonne: j rappresenta l'indice di colonna
            for (int j= 1; j<=n; j++){

                if ( !board.underAttack(i, j) ){
                    board.addQueen(i,j);
                    list = list.append( listOfCompletions( board ) );
                    board.removeQueen(i,j);
                }
            }//for

            return list;
        }
    }

}
