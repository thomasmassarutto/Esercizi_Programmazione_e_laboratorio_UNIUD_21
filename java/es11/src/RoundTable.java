/* PROTOCOLLO PUBBLICO
new RoundTable(n) : RoundTable costruttore della disposizione iniziale con (int) n ≥ 2 cavalieri
numberOfKnights() : int numero di cavalieri a tavola
servingKnights() : IntSList coppia (lista di due elementi) di cavalieri che servono il terzo se c’è
serveNeighbour() : RoundTable disposizione risultante dopo aver servito il prossimo cavaliere, che esce
passJug() : RoundTable disposizione risultante dopo aver passato la brocca
*/

public class RoundTable {
    /*VARIABILI DI ISTANZA*/
    private final int diners;// nr commensali
    private final IntSList knWjug;// primi due cavalieri: in due versano il vino agli altri
    private final IntSList knToServe;// altri cavalieri: quelli che durante questo giro non sono stati passati dalla brocca
    private final IntSList servedKn;// lista di appoggio: sono i cavalieri a cui la brocca ha passato oltre

    /*COSTRUTTORI*/
    public RoundTable(int n) {// : RoundTable costruttore della disposizione iniziale con (int) n ≥ 2 cavalieri

        diners= n;
        knWjug= range (1, 2);
        knToServe= range(3, n);
        servedKn= IntSList.NULL_INTLIST;
    }

    private RoundTable(int n, IntSList jugs, IntSList ls1, IntSList ls2){

        diners= n;
        knWjug= jugs;
        knToServe= ls1;
        servedKn= ls2;
    }

    /*METODI*/
    public int numberOfKnights() {// : int numero di cavalieri a tavola

        return diners;
    }
    public IntSList servingKnights() {// : IntSList coppia (lista di due elementi) di cavalieri che servono il terzo se c’è

        return knWjug;
    }

    /*serveNeighbour: disposizione risultante dopo aver servito il cavaliere in testa
    1) Decrementa il numero di commensali "diners"
    2) Toglie il primo cavaliere in successione dalla lista di quelli che possono essere serviti
    */
    public RoundTable serveNeighbour() {

        //debug("servi");
        if (diners==2){//se ci sono 2 persone

            return this;
        }else if ( knToServe.isNull() ){// se la lista dei cav da servire è vuota inverte la lista dei cavalieri "serviti"

            IntSList reversedServedKn= servedKn.reverse();

            return new RoundTable(diners-1, knWjug, reversedServedKn.cdr(), IntSList.NULL_INTLIST);
        }else{

            return new RoundTable(diners-1, knWjug, knToServe.cdr(), servedKn);
        }

    }

    /*passJug: disposizione risultante dopo aver passato la brocca; sposta cavalieri in fondo
    Rimaneggia l' ordine dei cavalieri, non toglie nessuno!
    1) aggiorna i cavalieri con brocca (sono i primi 2 della lista dei cavalieri non passati dalla brocca)
    2) aggiunge i cavalieri che ora hanno servito in testa alla coda dei cavalieri a cui la brocca è "passata sopra"
    */
    public RoundTable passJug() {

        //debug("passa");
        if (diners==2){//se ci sono 2 persone

            return this;
        }else if ( knToServe.length() < 2){// caso "speciale"

            //rappresentazione "MOMENTANEA" dell'ordine dei cavalieri:
            //rappresenta la lista dei cavalieri a cui potrebbe essere servito da bere
            //cavalieri del giro precedente (ordine invertito!)+ cavalieri che ora hanno la brocca
            IntSList newKnToServe= ( servedKn.append(knToServe).reverse()).append(knWjug);

            // prende dalla lista precedente i cavalieri che avranno la coppa
            IntSList newKnWjug= new IntSList().cons(newKnToServe.cdr().car()).cons(newKnToServe.car());

            // elimina dalla lista precedente i cavalieri che avranno la coppa
            newKnToServe.cdr().cdr();

            // gestione dei casi: evita che la lista diventi _null_ togliendo troppo
            if (newKnToServe.length()== 1){
                newKnToServe= newKnToServe.cdr();
            }else{
                newKnToServe= newKnToServe.cdr().cdr();
            }


            return new RoundTable (diners, newKnWjug, newKnToServe, IntSList.NULL_INTLIST);
        }else{// caso normale:
            int first= knToServe.car();//cavaliere in prima posizione
            int second=  knToServe.cdr().car();//cavaliere in seconda posizione

            // i nuovi 2 cavalieri con la brocca
            IntSList newKnWjug= new IntSList().cons(second).cons(first);//lista:(first, second)

            IntSList newServed= servedKn.cons(knWjug.car()).cons(knWjug.cdr().car());//servedKn, second, first

            return new RoundTable(diners, newKnWjug, knToServe.cdr().cdr(), newServed  );
        }

    }


    //_-_-_ALTRE FUNZIONI STRUMENTALI_-_-_-_

    // crea una lista d'interi dal min al max
    private static IntSList range (int inf, int sup){

        if (inf > sup){
            return (new IntSList());// lista vuota
        }else{
            return range(inf+1, sup).cons(inf);
        }
    }

    private  void debug(String msg){
        // rappresentazione su cui sta lavorando il programma ora
        //NB: STATO INIZIALE
        System.out.println("________"+ msg +"_________");
        System.out.println("diners: " + this.diners);
        System.out.println("knWjug: " + this.knWjug );
        System.out.println("knToServe: "+ this.knToServe);
        System.out.println("servedKn: " + this.servedKn);
        System.out.println("_____________________________");
    }


}
