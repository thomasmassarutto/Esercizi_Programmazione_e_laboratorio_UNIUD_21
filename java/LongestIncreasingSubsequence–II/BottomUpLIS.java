import java.util.Arrays;

public class BottomUpLIS {

  public static void printMatrix(int[][] mem, int s_len){
    System.out.println("mem:");
    for (int h =0; h<s_len; h++ ) {
        for(int k = 0; k<s_len; k++){
            System.out.print(mem[h][k]+ " ");
        }
        System.out.println();
    }
  }

  // Length of Longest Increasing Subsequence (LLIS):
  // Programmazione dinamica bottom-up
  public static int llisDP(int[] s) {

    int n = s.length;
    
    int[] t = new int[s.length + 1];
    for (int i = 0; i < s.length; i++) {
      t[i] = s[i];
    }
    t[s.length] = 0;

    int[][] mem = new int[n+1][n+1];
  
    // Matrice: valori delle ricorsioni di llisRec relativi a diversi valori degli argomenti
  
    for (int j = 0; j <= n; j++) {
  
      //colonna finale a 0
      mem[j][n] = 0;
    }

    //printMatrix(mem, n+1);
  
    for (int i = n - 1; i >= 0; i--) { //scorre le righe
      for (int j = 0; j <= n; j++) { //scorre le colonne
  
        //strutture di controllo per registrare i valori corrispondenti ai casi ricorsivi
        if (s[i] <= t[j]) {
          mem[j][i] = mem[j][i+1];
        } else {
          mem[j][i] = Math.max(1 + mem[i][i+1], mem[j][i+1]);
        }
        //printMatrix(mem, n+1);
      }
    }

    //printMatrix(mem, n+1);

    //  elemento della matrice il cui valore corrisponde a llis(s) :
    return mem[n][0];
  }

  

  // Longest Increasing Subsequence (LIS):Programmazione dinamica bottom-up
  public static int[] lisDP( int[] s ) {
  
    int n = s.length;
    
    int[][] mem = new int[ n+1 ][ n+1 ];

    int[] t = new int[s.length + 1];
    for (int i = 0; i < s.length; i++) {
      t[i] = s[i];
    }
    t[s.length] = 0;
    
    // 1. Matrice: valori delle ricorsioni di llisRec calcolati esattamente come per llisDP
    for (int j = 0; j <= n; j++) {
  
      //colonna finale a 0
      mem[j][n] = 0;
    }

    //printMatrix(mem, n+1);
  
     for (int i = n - 1; i >= 0; i--) { //scorre le righe
      for (int j = 0; j <= n; j++) { //scorre le colonne
  
        //strutture di controllo per registrare i valori corrispondenti ai casi ricorsivi
        if (s[i] <= t[j]) {
          mem[j][i] = mem[j][i+1];
        } else {
          mem[j][i] = Math.max(1 + mem[i][i+1], mem[j][i+1]);
        }
        //printMatrix(mem, n+1);
      }
    }

    //printMatrix(mem, n+1);
  
    // 2. Cammino attraverso la matrice per ricostruire un esempio di Longest Increasing Subsequence
    // elemento della matrice il cui valore corrisponde a llis(s) :

    int m = mem[n][0];
        
    int[] r = new int[ m ];  // per rappresentare una possibile LIS
    
    // indici utili per seguire un cammino attraverso la matrice e assegnare gli elementi della sottosequenza r
    
    int i = 0;
    int j = n;
    int h = 0; //per salvare gli elementi in r (incremento ad ogni interazione)

    while ( mem[j][i] > 0 ) {
    
      // strutture di controllo per scegliere e seguire un percorso appropriato attraverso 
      // la matrice in modo da ricostruire in r una possibile LIS relativa alla sequenza s
      if (s[i] >= t[j] && 1 + mem[i][i+1] > mem[j][i+1] ){

          //salvo il valore di s[i] in r
          r[h] = s[i];
          h++;
          //mi sposto
          j=i;
          i=i+1;
      }else{

          //mi sposto e basta
          i=i+1;
      }
    }
    return r;  // = LIS relativa alla sequenza s
  }

  public static void main(String[] args) {

    int[] s =  new int[]  {8, 9, 10, 11, 12, 4, 5, 6, 7, 1, 2, 3}  ;

    System.out.println("llis: " + llisDP(s));
    System.out.println("LIS: " + Arrays.toString(lisDP(s)));

  }
}  // class BottomUpLIS

