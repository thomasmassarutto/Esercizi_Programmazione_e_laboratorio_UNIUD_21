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

        if (q == n){
            // se il numero delle regine è uguale al numero
            // delle righe (o colonne) della scacchiera
            // c'è una soluzione da ritornare
            return 1;
        }else{// quando q< n

            // variabile contatore
            int count= 0;
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

                    count = count + numberOfCompletions( board.addQueen(i,j) );
                }
            }//for

            //finito il ciclo ritorno la variabile count
            return count;
        }
    }

//II PARTE: numero delle soluzioni possibili

    //numero di soluzioni data una scacchiera nxn
    public static SList<Board> listOfSolutions(int n){

        return listOfCompletions (new Board(n));
    }

    // completamenti validi
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


}
