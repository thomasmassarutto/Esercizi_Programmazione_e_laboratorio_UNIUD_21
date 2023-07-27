

public class Memoization {


/*
* R(n): numero di ricorsioni che fib(n) fa dato un numero n
* R(n) >= (3/2)^(n-1) (sottostima). Crescita esponenziale
*
* PER INDUZIONE:
*
* CASI BASE n<2
* R(0)= 1 (1: nr chiamate)
*   R(0) >= (3/2)^(-1) =2/3 VERO
*
* R(1)= 1
*   R(1) >= (3/2)^(0) =1 VERO
*
* INDUZIONE
* n >= 2
* R(n)= 1 + R(n-2) + R(n-1) > R(n-2) + R(n-1) [per sempl. togli +1]
*    >= (3/2)^(n-1 -2) + (3/2)^(n-1 -1)
*    >= (3/2)^(n-3) + (3/2)^(n-2)
*       raccolgo a fattore comune
*    >= (3/2)^(n-2) * ( 2/3 + 1 ) > (3/2)^(n-2) * ( 3/2 )
*                                 = R(n)
*    >= (3/2)^(n-1)
*
* il tempo è comunque stato sottostimato
*
* ES R(100)
*       R(100) >= (3/2)^(100-1)
*       R(100) >= (3/2)^(99)
*       R(100) >= 2.7*10^{17}
*
* Tempo minimo: con 1 ricorsione ogni millisecondo
* vengono impiegati non meno di 8 anni. In linea
* teorica. Nella realtà starebbe quasi 15 mila anni.
* lol
*
* Il tempo di calcolo è quindi molto importante
*
*
*                   fib(5)
*                  /      \
*           fib(3)          fib(4)
*          /      \        /      \
*       fib(1)   fib(2)  fib(2)  fib(3)
*           ...               ...
*
* Dall'albero si nota che molti calcoli li torno a fare
* più volte: poco ottimizzato. Bisogna trovare il modo
* di "ricordarsi" i risultati intermedi tramite uno stato
* */


//!!! NB: FIBONACCI NON E' QUELLO CANONICO: fib(0)=1 !!!

    
    //procedura ricorsiva che ritorna il numero di fibonacci
    public static long fib(int n){

        if (n<2){// per 0, 1 il valore è 1

            return 1;
        }else {// somma dei fibonacci dei due valori di fibonacci immediatamente precedenti

            return fib(n-2) + fib(n-1);
        }
    }// public static int fib

    //procedura con memorizzazione dello stato
    public static long fibMem (int n){

    // array in cui verranno piazzati i valori intermedi
    // implementa il concetto di stato
    long[] mem= new long[n + 1];

    // inizializzo l'array con tutte le celle a 0
    for (int i =0 ; i<=n; i++){

        mem[i]= UNKNOWN;
    }

    // chiedo utilizzp fibRec per restituire il risultato
    return fibRec(n, mem);
    }

    // procedura di supporto ottimizzata, registra la variabile di stato per evitare di ripetere calcoli già eseguiti
    private static long fibRec(int n, long[] mem){// M: rappresenta lo stato della computazione, la storia

        if( mem[n] == UNKNOWN ) {//se un fibonacci non è gia stato calcolato (UNKNOW: 0)

            if (n < 2) {
                //registra 1 in mem[0] o mem[1]
                mem[n]= 1;
            } else {
                //registra fibRec(n - 2, mem) + fibRec(n - 1, mem) in mem per n
                // all procedura ricorsiva vengono passati i valori standard di fibonacci (n-2 e n-1)
                // e l array contenente lo stato dei calcoli precedenti
                mem[n]= fibRec(n - 2, mem) + fibRec(n - 1, mem);
            }
        }

        // prima di restituire il valore, questo viene affidato
        // ad una variabile di stato
        return mem[n];//valore registrato mem[n]
    }// public static int fibRec

    private final static int UNKNOWN= -1;// indicazione che il valore non è stato calcolato,
                                        // VALE -1 PER CONVENZIONE INTERNA

/*****************************************************************************************************/
    //manhattan
    // calcolo del numero dei percorsi di manhattan

    //maniera standard

    public static long manh(int i, int j){

        // se stessa riga o colonna: 1 solo modo: vai dritto
        if (i==0 || j==0){// partenza e destinazione nella stessa strada

            return 1;
        }else{// somma manh in cui scendo di un passo + numero di percorsi verso dx

            return manh(i-1, j) + manh(i, j-1);
        }
    }

    // PROGAMMAZIONE DINAMICA TOP DOWN
    // maniera ottimizzata con memorizzazione di passi intermedi

    public static long manhMem (int i, int j){

        //array bidimensionale: 1 per ogni dimensione
        long[][] mem= new long[i+1][j+1];

        // scorro entrambe le dimensioni dell array per assegnare
        // 0 (UNKNOWN per convenzione interna) alla cella
        for (int u =0 ; u<=i; u++){
            for (int v =0 ; v<=j; v++){

            mem[u][v]= UNKNOWN;
            }
        }
        return manhRec(i, j, mem);
    }

    private static long manhRec(int i, int j, long[][] mem){// per l'array viene passato il riferimaneto, non la struttura

        // se alla posizione mem[i][j] il valore è 0, faccio i conti
        if (mem[i][j] == UNKNOWN){

            if (i == 0 || j == 0) {// partenza e destinazione nella stessa strada

                mem[i][j]= 1;
            } else {// calcolo ricorsivo

                mem[i][j]= manhRec(i - 1, j, mem) + manhRec(i, j - 1, mem);
            }

        }

        // viene restituito il valore della posizione in memoria i, j di mem
        // mem[i][j]
        return mem[i][j];
    }

    // PROGAMMAZIONE DINAMICA BOTTOM UP
    // PARTO DAI VALORI CASI BASE E I RISULTATI SUCCESSIVI
    // ARRIVANO AUMENTANDO LE RIGHE E LE COLONNE (...)
    // soluzione iterativa
    // usa la matrice
    public static long manhDp (int i, int j){

        // matrice
        long[][] mem= new long[i+1][j+1];

        // procedo con
        // indice delle righe (u)
        for (int u =0 ; u<=i; u++){
            //indice di colonna (v)
            for (int v =0 ; v<=j; v++){
                // se la posizione è nella prima riga o nella prima colonna
                if (u == 0 || v == 0) {
                    // inizializzo le celle della prima riga e colonna a 1
                    mem[u][v]= 1;
                } else {
                    // calcolo ricorsivo della cella [u][v] tramite i valori vicini
                    mem[u][v]= mem[u -1] [v] + mem[u] [v -1];
                }
            }
        }
        // vengono calcolate tutte le caselle della matrice,
        // nonostante questo è abbastanzaottimizzata
        //
        return mem[i][j];
    }


/*************************************/
// LUNGHEZZA DELLA SOTTOSEQUENZA SOTTOSEQUENZA
// COMUNE PIU LUNGA
    public static int llcs (String u, String v){
        // lunghezza stringhe
        int m= u.length();
        int n= v.length();

        if ((m == 0) || (n == 0)){
            // se una delle due è lunga zero non ci sono
            // sottosequenze da analizzare
            return 0;
        }else if (u.charAt(0) == v.charAt(0) ) {
            // se i primi caratteri sono uguali allora
            // incremento di 1 il mio conto
            // ricorsivamente chiamo llcs sulle stesse
            // stringhe tranne la prima lettera
            return 1 + llcs(u.substring(1), v.substring(1) );
        }else{  // se i caratteri iniziali sono diversi
        // trovo il max fra i risultati di llcs
        // calcolato su sottostringhe di u e v senza
        // carattere iniziale
        return Math.max(llcs(u.substring(1), v),
                        llcs(u, v.substring(1))
                        );
        }

    }

// memoriations llcs
    public static int llcsRecMem (String u, String v){

        // lunghezza stringhe
        int m = u.length();
        int n = v.length();

        //array bidimensionale: 1 per ogni dimensione
        // inizializzato a UNKNOWN
        int[][] mem= new int[m+1][n+1];

        // scorro entrambe le dimensioni dell array per assegnare
        // 0 (UNKNOWN per convenzione interna) alla cella
        for (int i =0 ; i<=m; i++){
            for (int j =0 ; j<=n; j++){

                mem[i][j]= UNKNOWN;//in.zione di el. a UNKNOWN
            }
        }
        // passo i dati alla procedeura privata
        // ritorna la sol al problema
        return llcsRec(u, v, mem);
    }

    private static int llcsRec (String u, String v, int[][] mem  ) {
        // lunghezza stringhe
        int m = u.length();
        int n = v.length();

        if (mem[m][n] == UNKNOWN) {

            if ((m == 0) || (n == 0)) {
                // se una delle due stringhe è lunga zero non ci sono
                // sottosequenze da analizzare
                return 0;
            } else if (u.charAt(0) == v.charAt(0)) {
                // se i primi caratteri sono uguali allora
                // incremento di 1 il mio conto
                // ricorsivamente chiamo llcs sulle stesse
                // stringhe tranne la prima lettera
                return 1 + llcsRec(u.substring(1), v.substring(1), mem);
            } else {
                // se i caratteri iniziali sono diversi
                // trovo il max fra i risultati di llcs
                // calcolato su sottostringhe di u e v senza
                // carattere iniziale
                mem[m][n]= Math.max(llcsRec(u.substring(1), v, mem),
                                    llcsRec(u, v.substring(1), mem)
                                    );
            }
        }

        return mem[m][n];
    }

    /*************************************/
// ESEMPIO DELLA SOTTOSEQUENZA SOTTOSEQUENZA
// COMUNE PIU LUNGA
    public static String lcs (String u, String v){
        // lunghezza stringhe
        int m= u.length();
        int n= v.length();

        if ((m == 0) || (n == 0)){
            // se una delle due è lunga zero non ci sono
            // sottosequenze da analizzare
            return "";
        }else if (u.charAt(0) == v.charAt(0) ) {
            // se i primi caratteri sono uguali allora
            // giustapposizione
            return u.substring(0,1) + lcs(u.substring(1), v.substring(1) );
        }else{  // se i caratteri iniziali sono diversi
            // trovo il max fra i risultati di llcs
            // calcolato su sottostringhe di u e v senza
            // carattere iniziale
            return better(lcs(u.substring(1), v),
                            lcs(u, v.substring(1))
                        );
        }
    }

    private static String better(String u,String v ){
        // lunghezza stringhe
        int m= u.length();
        int n= v.length();

        if (m>n){
            return u;
        }else if (m<n){

            return v;
        } if (Math.random() < 0.5){

            return u;
        }else{

            return v;
        }

    }

    // memoizations lcs
    public static String lcsRecMem (String u, String v){

        // lunghezza stringhe
        int m = u.length();
        int n = v.length();

        //array bidimensionale: 1 per ogni dimensione
        String[][] mem= new String[m+1][n+1];

        for (int i =0 ; i<=m; i++){
            for (int j =0 ; j<=n; j++){

                mem[i][j]= null;
            }
        }
        return lcsRec(u, v, mem);
    }

    private static String lcsRec (String u, String v, String[][] mem  ) {
        // lunghezza stringhe
        int m = u.length();
        int n = v.length();

        if (mem[m][n] == null) {

            if ((m == 0) || (n == 0)) {
                // se una delle due è lunga zero non ci sono
                // sottosequenze da analizzare
                mem[m][n]= "";
            } else if (u.charAt(0) == v.charAt(0)) {

                // giustapposizione
                mem[m][n]= u.substring(0,1) + lcsRec(u.substring(1), v.substring(1), mem);
            } else {  // se i caratteri iniziali sono diversi

                mem[m][n]= better(lcsRec(u.substring(1), v, mem),
                                    lcsRec(u, v.substring(1), mem)
                                    );
            }
        }

        return mem[m][n];
    }

    //BOTTOM UP
    // non c'è ricorsione
    // conta le soluzioni
    public static int llcsDp (String u, String v){

        // lunghezza stringhe
        int m = u.length();
        int n = v.length();

        //array bidimensionale: 1 per ogni dimensione
        int[][] mem= new int[m+1][n+1];

        // scorro entrambe le dimensioni della matrice per assegnare
        // il valore alla cella
        for (int i =0 ; i<=m; i++){
            for (int j =0 ; j<=n; j++){

                if ((i == 0) || (j == 0)) {//casi base

                    mem[i][j]= 0;
                } else if (u.charAt(m-i) == v.charAt(n-j)) {// quando I char è ==

                    mem[i][j]= 1 + mem[i-1][j-1];
                } else {// scelgo il max

                    mem[i][j]= Math.max(mem[i-1][j], mem[i][j-1]);
                }
            }
        }

        // valore della cella m,n
        return mem[m][n];
    }


    // BOTTOM UP
    // non c'è ricorsione
    // trova le soluzioni
    // utilizza la matrice
    public static String lcsDp (String u, String v){

        // lunghezza stringhe
        int m = u.length();
        int n = v.length();

        //array bidimensionale: 1 per ogni dimensione
        int[][] mem= new int[m+1][n+1];

        // scorro entrambe le dimensioni della matrice per assegnare
        // il valore alla cella
        for (int i =0 ; i<=m; i++){
            for (int j =0 ; j<=n; j++){

                if ((i == 0) || (j == 0)) {//casi base

                    mem[i][j]= 0;
                } else if (u.charAt(m-i) == v.charAt(n-j)) {// quando I char è ==

                    mem[i][j]= 1 + mem[i-1][j-1];
                } else {// scelgo il max

                    mem[i][j]= Math.max(mem[i-1][j], mem[i][j-1]);
                }
            }
        }

        // punto di partenza "alto a dx"
        int i= m;
        int j= n;

        String lcs= "";

        //finche il punto della matrice contiene un valore maggiore di zero
        // continuo il processo di ricerca per trovare gli altri caratteri
        while (mem[i][j]>0){

            if (u.charAt(m-i) == v.charAt(n-j)){

                lcs= lcs + u.charAt(m-i);// accodo char a lcs
                // sposto diago in basso a dx
                i= i-1;
                j= j-1;
            }else if(mem[i-1][j] > mem[i][j-1] ){// se valore sotto > rispetto a valore a dx

                i= i-1;// mi sposto in basso
            }else if(mem[i-1][j] < mem[i][j-1] ){

                j= j-1;// mi sposto a dx
            }else if (Math.random() < 0.5){// se sono uguali

                i=i-1;
            }else {

                j=j-1;
            }
        }

        return lcs;
    }


}// public class Memoization
