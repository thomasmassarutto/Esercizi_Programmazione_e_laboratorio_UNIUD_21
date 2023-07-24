/*
 * Procedura generalizzata per manipolare
 * liste di elementi generici e omogenei
 * con lo stile di scheme
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

    // verifica se la lista sia vuota
    public boolean isNull(){

        return this.empty; // Ritorna la var. d'istanza corrispondente
    }

    //ritorna il primo elemento della lista
    public T car(){

        return this.first; // Ritorna la var. d'istanza corrispondente
    }

    //ritorna la coda della lista
    public SList<T> cdr(){

        return this.rest; // Ritorna la var. d'istanza corrispondente
    }

    // aggiunge in testa un nuovo elemento
    public SList<T> cons(T item){

        return new SList<T>(item, this); // usa il costruttore nr.2
    }

    //restituisce la lunghezza della lista
    public int length(){

        if ( this.isNull() ){

            return 0;
        }else {

            return this.cdr().length() + 1;// toglie un elemento alla volta dalla coda
        }
    }

    // ritorna l'elemento della lista in posizione i
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

    // appende ad una lista un altra in coda: ls1.append(ls2) = ls1 + ls2
    public SList<T> append(SList<T> ls){

        if ( isNull() ){

            return ls;
        }else{

            // appendo alla coda ls e aggiungo l'elemento di testa
            return (this.cdr().append(ls)).cons(this.car());
        }

    }

    // inverte la lista
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

    // serve a stampare una lista formattata in modo differente
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