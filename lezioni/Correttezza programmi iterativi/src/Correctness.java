public class Correctness {


    /*
    * Calcola i quadrato con solo somme
    *
    * */
    public static int sqr(int n){// Precondizione: n>=0

        int x=0;
        int y=0;
        int z=1;

        while (x<n){// Invariante: x<=n && z= 2x+1 (dispari) && y= x*x(quadrato di x)
                    // Terminazione: n-x
            x= x+1;
            y= y+z;
            z= z+2;
        }
        /*
        * Invariante dopo 1 ciclo while (dando per scontato che entra in while):
        * Ipotesi: x+1<=n; OVVIO, incremento di uno...
        *
        * Ipotesi   x = x
        *           z = 2x+1
        *
        * while     z'= z+2
        *           x'= x+1
        * Dimostro che:
        *     z'= 2x' +1
        * z + 2 = 2(x+1) + 1
        *
        * z + 2=  2(x+1) + 1
        * z + 2= 2x + 2 + 1 (tolgo +2)
        * z= 2x +1
        *
        * Tesi      x = x
        *           y = x*x
        *           z = 2x +1
        * while     x'= x +1
        *           y'= y+z
        * Dimostro che:
        *         y' = x'*x'
        * (riscrivo)
        *        y+z = (x + 1) * (x + 1)
        * (svolgo operazioni)
        *        y+z = x*x +2x +1
        * (uso tesi: y = x*x, z = 2x +1 )
        * x*x + 2x+1 = xx +2x +1
        *
        * */

        return y;// Postcondizione y= n*n
    }
    /*
    * Calcola minimo comune multiplo
    * */
    public static int lcm(int m, int n){// pre: m,n>0

        int x= m;
        int y= n;

        while (x!=y){// Inv: 0<x,y && x,y<= mcm(m,n) &&
                     // x mod m = 0  (x multiplo m)  &&
                     // y mod n = 0  (y multiplo n)
                     // Term: 2mn - x - y

            if (x<y){

                x=x+m;
            }else{
                y=y+n;
            }
        }
        return x;// Post x= mcm(m,n)
    }
}
