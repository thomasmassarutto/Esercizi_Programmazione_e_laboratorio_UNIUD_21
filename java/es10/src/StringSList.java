
//Rapresenta lista di stringhe:
//StringSList = list (
//                      "ciao"
//                      "come"
//                       "va"
//                      )
public class StringSList {

    /* VARIABILI DI ISTANZA */

    private final boolean empty;// definisce se la lista è vuota

    private final String first;// rappresenta la prima stringa

    private final StringSList rest;// rappresenta la coda della lista

    /* COSTRUTTORI */

    //Costruttore di liste vuote
    public StringSList (){

        empty= true;
        first= "";
        rest= null;
    }
    //Costruttore di liste NON vuote
    public StringSList (String f, StringSList r){// f: stringa in testa; r: coda

        empty= false;
        first= f;
        rest= r;
    }

    /* METODI */

    // verifica se la lista è nulla
    public boolean isNull(){

        return this.empty;// ritorna la var. di istanza
    }

    //ritorna il primo elemento della stringa
    public String car(){

        return this.first;// variabile di istanza
    }

    //ritorna la coda della lista
    public StringSList cdr(){

        return this.rest;
    }

    // aggiunge in testa un nuovo elemento
    public StringSList cons(String s){

        return new StringSList(s, this);// usa il costruttore nr.2
    }

    //restituisce la lunghezza della stringa
    public int length(){

        if ( this.isNull() ){

            return 0;
        }else {

            return this.cdr().length() + 1;
        }
    }

    // ritorna stringa in posizione i
    public String listRef(int i){

        if (i==0){
            return this.car();
        }else {

            return this.cdr().listRef(i-1);
        }
    }

    //Confronta l'uguaglianza di due liste
    public boolean equals(StringSList s){

        if (isNull() || s.isNull()){

            return ( this.isNull() && s.isNull() );//vero solo se entrambe vere
        }else if(car() == s.car()){
            //confronto primo elemento
            return this.cdr().equals(s.cdr());
        }else{
            return false;
        }

    }

    //appende ad una lista un altra
    public StringSList append(StringSList ls){

    if ( isNull() ){

        return ls;
    }else{
        // appendo alla coda ls e aggiungo l'lelemento di testa
        return (this.cdr().append(ls)).cons(this.car());
    }

    }

    // inverte la lista
    public StringSList reverse(){

      return reverseRec(new StringSList());
    }

    private StringSList reverseRec(StringSList ls){

        if (isNull()){

            return ls;
        }else{
            //procedura ricorsiva: reverse della coda a cui aggiunge la testa: fa slittare
            return this.cdr().reverseRec( ls.cons(this.car()) );
        }

    }


    /* OVERRIDES */
    public String toString(){

        String s= "(";
        if ( !isNull() ){

            s= s+ this.car();
            StringSList r= this.cdr();

            while ( !(r.isNull()) ){

                s= s + ", " + r.car();
                r= r.cdr();
            }
        }
        return s + ")";
    }
}
