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
        System.out.println(game.coord(0)[0] +" "+ game.coord(0)[1] );
        int k;

        while(!game.isSorted()){
            k = gui.get();
            int[] vuotoCoord= game.coord(0);
            int[] kCoord= game.coord(k);

            //System.out.println((kCoord[ROW] +1) +" "+ (kCoord[COL] +1) );
            //System.out.println((vuotoCoord[ROW] +1) +" "+ (vuotoCoord[COL] +1) );
            if (game.canBeMoved(k)){
                // aggiornare gioco
                game.moveTile(k);
                //System.out.println(game.toString());
                // aggiornare gui
                gui.setNumber(vuotoCoord[ROW] +1,vuotoCoord[COL] +1,k);
                //gui.clear(kCoord[ROW] +1,kCoord[COL]+1);
                gui.setNumber(kCoord[ROW] +1,kCoord[COL]+1, 0);
                gui.display();
            }
        }
        //System.out.println(Game.isSorted());

    }
}