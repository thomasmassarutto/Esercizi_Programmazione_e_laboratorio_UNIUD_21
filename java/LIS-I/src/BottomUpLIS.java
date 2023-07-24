
public class BottomUpLIS {


    // Length of Longest Increasing Subsequence (LLIS):
    // Programmazione dinamica bottom-up

    public static int llisDP( int[] s ) {

        int n = s.length;

        int[][] mem = new int[ n+1 ][ n+1 ];

        // Matrice: valori delle ricorsioni di llisRec
        // relativi a diversi valori degli argomenti

        int[] t = new int[s.length + 1];
        for (int i = 0; i < s.length; i++) {
            t[i] = s[i];
        }
        t[s.length] = 0;

        for ( int j=0; j<=n; j=j+1 ) {

            // --------------------------------------------------
            //  Inserisci qui i comandi per registrare i valori
            //  corrispondenti ai casi base della ricorsione
            // --------------------------------------------------

            mem[j][n]=0;
        }

        for ( int i=n-1; i>=0; i=i-1 ) {
            for ( int j=0; j<=n; j=j+1 ) {

                // ------------------------------------------------
                //  Inserisci qui le strutture di controllo
                //  appropriate e i comandi per registrare
                //  i valori corrispondenti ai casi ricorsivi
                // ------------------------------------------------

                if (s[i] <= t[j]) {
                    mem[j][i] = mem[j][i+1];
                } else {
                    mem[j][i] = Math.max(1 + mem[i][i+1], mem[j][i+1]);
                }
            }}

        // ----------------------------------------------------
        //  Inserisci di seguito l'elemento della matrice
        //  il cui valore corrisponde a llis(s) :

        return  mem[n][0];/* elemento appropriato della matrice */;

        // ----------------------------------------------------
    }


    // Longest Increasing Subsequence (LIS):
    // Programmazione dinamica bottom-up

    public static int[] lisDP( int[] s ) {

        int n = s.length;

        int[][] mem = new int[ n+1 ][ n+1 ];

        // 1. Matrice: valori delle ricorsioni di llisRec
        //    calcolati esattamente come per llisDP

        // ------------------------------------------------
        //  Replica qui il codice del corpo di llisDP
        //  che registra nella matrice i valori
        //  corrispondenti alle ricorsioni di llisRec
        // ------------------------------------------------

        // 2. Cammino attraverso la matrice per ricostruire
        //    un esempio di Longest Increasing Subsequence

        // ----------------------------------------------------
        //  Inserisci di seguito l'elemento della matrice
        //  il cui valore corrisponde a llis(s) :

        int m =  /* elemento appropriato della matrice */;

        // ----------------------------------------------------

        int[] r = new int[ m ];  // per rappresentare una possibile LIS

        // ----------------------------------------------------
        //  Introduci e inizializza qui gli indici utili
        //  per seguire un cammino attraverso la matrice e
        //  per assegnare gli elementi della sottosequenza r
        // ----------------------------------------------------

        while ( mem[i][j] > 0 ) {

            int t = ( j == n ) ? 0 : s[j];

            // --------------------------------------------------
            //  Inserisci qui strutture di controllo e comandi
            //  per scegliere e seguire un percorso appropriato
            //  attraverso la matrice in modo da ricostruire in
            //  r una possibile LIS relativa alla sequenza s
            // --------------------------------------------------
        }

        return r;                // = LIS relativa alla sequenza s
    }


}  // class BottomUpLIS
