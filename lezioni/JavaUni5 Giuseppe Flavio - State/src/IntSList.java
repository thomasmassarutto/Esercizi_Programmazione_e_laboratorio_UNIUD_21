
//Integer Schemelike List
public class IntSList {
    //lista vuota immutabile
    public static final IntSList NULL_INTSLIST = new IntSList();


    //VARIABILI DI ISTANZA
    private final boolean empty;// indica se la lista è vuota
    private final int first;//indica il primo elemento della lista
    private final IntSList rest;//indica la restante parte della lista

    public IntSList(){//primo costruttore crea liste vuote

        empty= true;
        first= 0;
        rest= null;//oggetto indefinito
    }


    public IntSList(int n, IntSList s){// secondo costruttore: crea liste non vuote

        empty= false;
        first= n;
        rest= s;//oggetto indefinito
    }

    public boolean isNull(){
        return this.empty;
    }

    public int car(){
        return this.first;
    }
    public IntSList cdr(){
        return rest;
    }

    public IntSList cons(int n){

        return new IntSList(n, this);
    }

    //length
    public int length (){

        if (this.isNull()){

            return 0;
        }else {

            return this.cdr().length() + 1;
        }

    }

    //list-ref
    public int listRef (int i){
        if (i==0){
        return car();
        }else{
            return cdr().listRef(i-1);
        }
    }

    //equal
    public boolean equals (IntSList s){

        if (isNull() || s.isNull()){

            return (isNull() && s.isNull()) ;
        }else if (car() == s.car() ){

            return cdr().equals(s.cdr());
        }else{

            return false;
        }


    }

    //append
    public IntSList append (IntSList s){

        if ( isNull() ){
            return s;
        }else {

            return (this.cdr().append(s)).cons(this.car()); //appende s alla coda di this e poi aggiunge in testa il car
        }
    }

    //reverse
    public IntSList reverse(){

        //return reverseRec(NULL_INTSLIST);
        return reverseRec(new IntSList());
    }

    private IntSList reverseRec(IntSList r){

        if ( isNull() ){
            return r;
        }else {

            return cdr().reverseRec( r.cons(car()) );
        }
    }

    public String toString (){
        String desc ="(";
        if (!isNull() ){

            desc = desc + car();
            IntSList resto = cdr();

            while (!resto.isNull()){
            desc = desc + ", " + resto.car();
            resto= resto.cdr();
            }
        }

        return desc + ")";
    }

}
