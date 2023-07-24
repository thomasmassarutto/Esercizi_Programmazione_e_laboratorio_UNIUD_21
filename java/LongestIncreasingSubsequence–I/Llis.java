import java.util.Arrays;

public class Llis{

public static int llis( int[] s ) {  // s[i] > 0 per i in [0,n-1], dove n = s.length
    return llisRec( s, 0, 0 );
}

private static int llisRec( int[] s, int i, int t ) {

    if ( i == s.length ) {          // i = n : coda di s vuota (caso base)
        return 0;
    } else if ( s[i] <= t ) {       // x = s[i] ≤ t : x non può essere scelto
        return llisRec( s, i+1, t );
    } else {                        // x > t : x può essere scelto o meno
        //caso 1: t prende il valore dell'elemento i e si fa la risorsione da i+1
        //caso 2: t torna 0 e si fa la risorsione da i+1
        return Math.max( 1+llisRec(s,i+1,s[i]), llisRec(s,i+1,t) );
    }
}

/////////////////////

public static void printMatrix(int[][] mem, int s_len){
    System.out.println("mem:");
    for (int h =0; h<s_len; h++ ) {
        for(int k = 0; k<s_len; k++){
            System.out.print(mem[h][k]+ " ");
        }
        System.out.println();
    }
}

private final static int UNKNOWN = -1;  

static int[][] mem;

public static int llisRecTD1(int[] s, int i, int t) {

    //printMatrix(mem, s.length);
    if (i == s.length) {
        return 0;
    }

    if (mem[i][t] != UNKNOWN) {
        return mem[i][t];
    }

    int mem_llis = llisRecTD1(s, i + 1, t);
    int recors_llis = 0;
    if (s[i] > t) {
        recors_llis = 1 + llisRecTD1(s, i+1, s[i]);
    }

    mem[i][t] = Math.max(recors_llis, mem_llis);
    //printMatrix(mem, s.length);
    return mem[i][t];
    
}

public static int llisTD1(int[] s) {
    int n = s.length+1;
    mem = new int[n][n];
    for (int i =0; i<s.length; i++ ) {
        for(int j = 0; j<s.length; j++){
            mem[i][j]= UNKNOWN;
        }
    }
    return llisRecTD1(s, 0, 0);
}

////

static int[][] mem2;

public static int llisRecTD2(int[] s, int i, int j) {

    //j = ultimo elemento della sequenza s considerato nella funzione
    int t;
    if (j > 0) {
        t = s[j-1];
    } else {
        t = 0;
    }

    printMatrix(mem2, s.length);
    if (i == s.length) {
        return 0;
    }

    if (mem2[i][j] != UNKNOWN) {
        return mem2[i][j];
    }

    //llis, senza considerare s[i], da s[i+1] partendo da s[j]
    //(j è la soglia, ultimo valore della sottosequenza considerata)
    int mem_llis = llisRecTD2(s, i + 1, j);

    //llis, per s[i], partendo da s[i+1]; inizializzato a 0 perchè s[i] potrebbe essere minore della soglia
    int recors_llis = 0;

    if (s[i] > t) {
        recors_llis = 1 + llisRecTD2(s, i+1, i+1);
    }

    mem2[i][j] = Math.max(recors_llis, mem_llis);
    printMatrix(mem2, s.length);
    return mem2[i][j];
    
}

public static int llisTD2(int[] s) {
    int n = s.length+1;
    mem2 = new int[n][n];
    for (int i =0; i<n; i++ ) {
        for(int j = 0; j<n; j++){
            mem2[i][j]= UNKNOWN;
        }
        mem2[i][n-1] = 0;
    }
    return llisRecTD2(s, 0, 0);
}

public static void main(String[] args) {

    int[] string;
    string = new int[] {6, 1, 7, 2, 8, 3, 9, 4, 10, 5, 6} ;
    
    System.out.println("llisLength: " + llis( string ));
    //System.out.println("llisTD1: " + llisTD1( string ));  //i,t
    System.out.println("llisTD2: " + llisTD2( string ));  //i,j
}
}

/*
    i <= s.length:
    6:
    string = new int[] {6, 1, 7, 2, 8, 3, 9, 4, 10, 5, 6} ;
    5:
    string = new int[] {6, 1, 7, 2, 8, 3, 9, 4, 10, 5};
    5:
    string = new int[] {8, 9, 10, 11, 12, 4, 5, 6, 7, 1, 2, 3} ;
    4:
    string =  new int[] {7, 8, 9, 10, 4, 5, 6, 2, 3, 1} ; 
    5:
    string =  new int[] {10, 11, 12, 6, 7, 8, 9, 1, 2, 3, 4, 5} ;
    4:
    string = new int[] {10, 8, 9, 5, 6, 7, 1, 2, 3, 4} ;
    1:
    string = new int[] {5, 4, 3, 2, 1};

    normal:
    3:
    string =  new int[] {54, 52, 42, 33, 14, 40, 37, 61, 53, 1} ;
    5:
    string = new int[] {9, 46, 54, 71, 60, 47, 1, 32, 25, 61};
    4:
    string = new int[] {27, 90, 7, 29, 49, 8, 53, 1, 28, 6};
    3:
    string = new int[] {47, 38, 39, 25, 44};
    3:
    string = new int[] {2, 7, 5, 7, 4};
 */