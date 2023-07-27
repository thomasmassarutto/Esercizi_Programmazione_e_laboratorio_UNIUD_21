/*  GIUSEPPE FLAVIO
* Chi ha la brocca in mano versa il sidro alla sua sx
* Chi riceve il sidro esce dal gioco.
* La brocca viene passata al successivo.
* L'ultimo che rimane con la brocca
*
*
*/

public class GiuseppeFlavio {
    public static void main(String[] args) {

        int cav= ultimoCavaliere(12);

        System.out.println(cav);
    }

    public static int ultimoCavaliere (int n){

        TavRotonda tav= new TavRotonda(n);

        while (! tav.ritualeCompletato() ){

            //le due azioni che si compiono vanno
            //a modificare la tavola

            /*PROCESSO 1
            tav= tav.serviSidro();
            tav= tav.passaBrocca();
            */

            //PROCESSO 2: OTTIMIZZATO
            tav= tav.dopoUscitaCavaliere();
        }

        return tav.chiHaLaBrocca();
    }

    public static int testGFlavio (int n) {

        TavRotonda tav=new TavRotonda(1);
        for (int k = 1; k <= n; k++) {

            tav = new TavRotonda(k);

            while (tav.quantiCavalieri()>1) {

                tav = tav.serviSidro();
                tav = tav.passaBrocca();
            }
        }

            return tav.chiHaLaBrocca();
        }


}
