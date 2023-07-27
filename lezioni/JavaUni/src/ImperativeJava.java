public class ImperativeJava {
    //calcolo mcd (gcd: greater common divisor)
    public static int gcd (int x, int y) { //x,y> 0

        while (x != y) {
            if (x < y) {
                y = y - x;
            } else {
                x = x - y;
            }
        }
        return x;
    }
        // MCD (lcm: least common multiple)
        public static int lcm (int x, int y){

            int m=x;

            while (m % y > 0){

            m= m+x;
            }
            return m;
        }

        //verifica numero primo
    public static boolean isPrime (int n){//n>1
        //non ha divisori propri
        int d= 3;
        boolean p= (n % 2 == 0) ? n==2 : true; //se n è %2 non è primo
        while (p && (d<n)){ //oppure (p && d*d <= n)
            if (n % d == 0){
                p= false;
            }
            d= d+2;
        }
        return p;
    }

    public static boolean isPrimeV2 (int n){//n>1
        //non ha divisori propri
        int d= 3;
        if (n%2==0){
            return (n==2); //false
        }
        while (d*d <= n){ //oppure ()
            if (n % d == 0){
                return false;
            }
            d= d+2;
        }
        return true;
    }

    public static int ufo (int x){
      //tipo  nome  creatore    dimensione
      //            oggetto
        int[] u=    new         int[x+1];//array

        u[1]= 1;

        for (int k=2; k<=x; k=k+1){

        if (k%2==0) {
            u[k] = 2 * u[k / 2] - 1;
        } else {
            u[k] = 2 * u[k / 2] + 1;
        }}
        return u[x];
    }

    public static int ufoV2 (int x){

        int[] u= new int[logSize(x)];
        u[0]= x;
        int i=0;

        while (x>1){

            x= x/2;
            i ++;
            u[i]=x;
        }

        int y= 1;
        for (int j=i-1; j>=0;j--){
            if (u[j] %2==0){
                y= 2*y-1;
            }else{
                y= 2*y+1;
            }
        }

        return y;
    }

    private static int logSize(int n){

        return (int) (Math.log(n) / Math.log(2)) +1;
    }
    //BTR iterativo
    public static int btrVal (String btr){

        int n= btr.length();
        int v=0;
        /*
        for (int i=0; i<n ;i++){

            v= 3 * v + btdVal (btr.charAt(i));
        }*/
        int i=0;
        while ( i<n){

            v= 3 * v + btdVal (btr.charAt(i));
            i++;
        }
        return v;

    }
     public static int btdVal (char btd){
        switch (btd){
            case '-': {return -1;}
            case '.': {return 0;}
            case '+': {return +1;}
            default: {return 0;}
        }
    }
    /*
    //ALGORITMO INSERTION SORT

    */
    public static void insSort(double[] v){

        //k: scansiona ogni elemento dell'array dall'indice 1 in poi
        //NB: un array di 1 elemento sarà sempre ordinato
        for(int k=1;k< v.length ; k++){

            double x= v[k]; // salvo il numero da spostare in una variabile

            int i=k-1;  // rappresenta gli indici precedenti a k;
                        // variabile contatore nel while

            //scorro all'indietro nell'array dall'indice i (:k-1)
            //fino all'indice 0 e finchè l'elemento che voglio
            //ordinare (x) è minore dell'elemento in posizione i
            //sposto a destra gli elementi magiori di x
            while ( i>=0 && x<v[i] ){

                v[i+1]= v[i];   // faccio scorrere gli elementi
                i--;    // decremento il contatore
            }

            //inserisco il valore da ordinare in [i+1]
            v[i+1]=x;
        }
    }

    //+++++++++++++++++++++++++++++++++
    }

