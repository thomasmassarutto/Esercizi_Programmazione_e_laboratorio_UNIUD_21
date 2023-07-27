import java.sql.Array;

public class myAlgs {
    /*
    *   Y
    *   O X
    * */
    public static long myAlgManh(int x, int y){

        // x: spostamento orizzontale

        int  sum= x+y;
        long res=binomi(sum, x);

        return res;
    }
    public static long binomi(int n, int k) {
        if ((n == k) || (k == 0))
            return 1;
        else
            return binomi(n - 1, k) + binomi(n - 1, k - 1);
    }
    private final static long UNKNOWN= -1;
    public static long myManhattan(int x, int y){
        long[][] registro= new long[x+1][y+1];
        // inizializzare array a UNKNOWN
        for (int i=0 ;i<= x;i++){
            for (int j=0;j<= y; j++){
                registro [i][j]= UNKNOWN;
            }
        }
        return myManhattanRec(x, y, registro);
    }

    private static long myManhattanRec(int x, int y, long[][] registro){
        if (registro[x][y] ==  UNKNOWN){
            if (x == 0|| y == 0){
                registro[x][y]= 1;
            }else {
                registro[x][y]= myManhattanRec(x-1, y, registro) + myManhattanRec(x, y-1, registro);
            }
        }
        return registro[x][y];
    }
}
