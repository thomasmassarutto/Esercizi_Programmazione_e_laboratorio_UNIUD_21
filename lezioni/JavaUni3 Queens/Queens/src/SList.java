/*
* Procedura generalizzata per manipolare liste
* con lo stile di scheme
*
*
* */

public class SList<T> {

    /* VARIABILI DI ISTANZA */
    private final boolean empty;
    private final T first;
    private final SList<T> rest;

    /* COSTRUTTORI */

    public SList (){

        empty= true;
        first= null;
        rest= null;
    }

    public SList (T item, SList<T> s){

        empty= false;
        first= item;
        rest= s;
    }

    /* METODI */

    // verifica se la board è nulla
    public boolean isNull(){

        return this.empty;// Ritorna la var. d' istanza
    }

    //ritorna il primo elemento della stringa
    public T car(){

        return this.first;// variabile d' istanza
    }

    //ritorna la coda della board
    public SList<T> cdr(){

        return this.rest;
    }

    // aggiunge in testa un nuovo elemento
    public SList<T> cons(T item){

        return new SList<T>(item, this);// usa il costruttore nr.2
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
    public T listRef(int i){

        if (i==0){
            return this.car();
        }else {

            return this.cdr().listRef(i-1);
        }
    }

    //Confronta l'uguaglianza di due liste
    public boolean equals(SList<T> s){

        if (isNull() || s.isNull()){

            return ( this.isNull() && s.isNull() );//vero solo se entrambe vere
        }else if(car() == s.car()){
            //confronto primo elemento
            return this.cdr().equals(s.cdr());
        }else{
            return false;
        }

    }

    //appende aduna board un altra
    public SList<T> append(SList<T> ls){

        if ( isNull() ){

            return ls;
        }else{
            // appendo alla coda ls e aggiungo l'elemento di testa
            return (this.cdr().append(ls)).cons(this.car());
        }

    }

    // inverte la board
    public SList<T> reverse(){

        return reverseRec(new SList<T>());
    }

    private SList<T> reverseRec(SList<T> ls){

        if (isNull()){

            return ls;
        }else{
            //procedura ricorsiva: reverse della coda a cui aggiunge la testa: fa slittare
            return this.cdr().reverseRec( ls.cons(this.car()) );
        }

    }


    /* OVERRIDES */
    public String toString(){

        if ( isNull() ){
            return "()";
        }else{
            String rep= "(" + car();
            SList<T> r= cdr();
            while ( !r.isNull() ){
                rep= rep + ", " + r.car();
                r= r.cdr();
            }

            return (rep + ")");
        }

    }
}