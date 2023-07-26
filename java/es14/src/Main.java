import puzzleboard.PuzzleBoard;
public class Main {
    public static final int ROW=0;
    public static final int COL=1;
    public static void main(String[] args) {

        // n: dimensione della tavoletta, n>2
        int n=3;
        Board game= new Board(n);

        System.out.println(game.toString());
        PuzzleBoard gui= new PuzzleBoard( 3);

        // riempire la gui
        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                gui.setNumber(i+1,j+1, game.board[i][j]);
            }
        }

        // variabile che "sente" il click del mouse
        int k;
        while(!game.isSorted()){
            k = gui.get();
            int[] vuotoCoord= game.coord(0);
            int[] kCoord= game.coord(k);

            if (game.canBeMoved(k)){
                // aggiornare gioco
                game.moveTile(k);

                // aggiornare gui

                // aggiorno posizione tile spostato
                gui.setNumber(vuotoCoord[ROW] +1,vuotoCoord[COL] +1,k);

                // aggiornare posizione vuota
                //gui.clear(kCoord[ROW] +1,kCoord[COL]+1);
                gui.setNumber(kCoord[ROW] +1,kCoord[COL]+1, 0);
                gui.display();
            }
        }

    }
}