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

        int cav= ultimoCavaliere(25);

        System.out.println(cav);
    }

    public static int ultimoCavaliere (int n){

        TavRotonda tav= new TavRotonda(n);

        while (tav.quantiCavalieri() > 1 ){

            /*PROCESSO IMPERATIVO*/
            tav.serviSidro();
            tav.passaBrocca();
            /*PROCESSO IMPERATIVO "OTTIMIZZATO"
             tav.dopoUscitaCavaliere();
            */

        }

        return tav.chiHaLaBrocca();
    }
}
