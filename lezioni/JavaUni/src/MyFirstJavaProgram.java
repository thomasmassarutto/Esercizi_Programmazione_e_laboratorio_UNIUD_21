import static java.lang.Math.pow;

public class MyFirstJavaProgram {
    //procedura che calcola l'area di un cilindro
    static double areaCilindro(double r, double h) {
        return 2 * Math.PI * r * (r + h);
    }

    //procedura che restituisce il plurale maschile
    public static String pluraleSm (String s){
        return s.substring (0, s.length()-1) + "i";
    }
    public static String pluraleSf (String s){
        return s.substring (0, s.length()-1) + "e";
    }

    public static boolean femminile (String s){
        // controllo ultimo carattere
        return s.charAt(s.length() - 1) == 'a';//se ultimo carattere è a ritorna True
        // controllo con stringa
        //return s.substring(s.length() - 1).equals("a");
    }

    //costruisce il plurale
    public static String plurale (String s){
        // modo skillato
        // return femminile(s) ? pluraleSf(s) : pluraleSm(s)

        //modo easy
        if (femminile(s)){
            return pluraleSf(s);
        }else{
        return pluraleSm(s);
        }
    }

    //sequenza di fibonacci
    public static int fibonacci (int n){

        if(n<2){
            return 1;
        }else{
            return fibonacci(n-2) + fibonacci(n-1);
        }
    }

    //calcolare lato lungo fogli Ax
    private static final double S0 = (100 * Math.pow(2, +0.25 ) );
    private  static final double S1 = (100 * Math.pow(2, -0.25 ) );
    public static double latoLungo (int k){//numero che indica il formato

    if (k < 2){
        return k==0 ? S0 : S1;
    }else{
        //(/ (s (- k 2)) 2)
        return latoLungo(k - 2) / 2;
    }

    }

    // complemento a 1: inverte 0 con 1 in stringa binaria

    public static String onesComplement (String bin ){

        if (bin.equals("")){
        return "";
        } else{
        return  bitComplement (bin.substring(0,1))
                + onesComplement(bin.substring(1));
        }
    }
    private static String bitComplement (String bit){
        return bit.equals("0") ? "1" : "0";
    }

    // rappresentazione binaria bilanciata

    //
    private static int btdVal (char btd){
        /* primo metodo per gestire condizioni multiple
        if       (btd == '-'){
            return -1;
        }else if (btd == '.'){
            return 0;
        }else if (btd == '+'){
            return +1;
        } else {return 0;}
         */

        // SWITCH
        switch (btd){
            case ('-'):{return -1;}
            case ('.'):{return 0;}
            case ('+'):{return +1;}
            default: {return 0;}
        }
    }

    public static int btrVal (String btr){
        // posizione ultima cifra
        int k = btr.length() -1;
        // prefisso
        String prefix = btr.substring (0, k);
        //carattere ultima posizione
        char lsd= btr.charAt(k);

        if (k== 0){
            return btdVal(lsd);
        }else{
            return 3 * btrVal(prefix) + btdVal(lsd);
        }
    }
}
