
/*
*Definisci in Java una classe StringSList per rappresentare liste di stringhe nello stile di Scheme.
*Analogamente alla classe introdotta a lezione per le liste di interi, il protocollo deve prevedere
*  i seguenti costruttori e metodi:
*
*public StringSList() // null

 public StringSList( String e, StringSList sl ) // cons

 public boolean isNull() // null?
 public String car() // car

 public StringSList cdr() // cdr

 public StringSList cons( String e ) // cons (modalità alternativa)

 public int length() // length

 public String listRef( int k ) // list-ref

 public boolean equals( StringSList sl ) // equal?

 public StringSList append( StringSList sl ) // append

 public StringSList reverse() // reverse

 public String toString() // visualizzazione testuale
* */
public class Main {
    public static void main(String[] args) {

        StringSList L1= new StringSList();
        StringSList L2= new StringSList();
        L1= range(1,3).cons("cane");
        L2= range(4,5).cons("gatto");

        System.out.println(L1.reverse());



        StringSList L3= nextBtr("+-", 5);
        System.out.print(L3.toString());
    }

    public static StringSList range(int inf, int sup){

        String nbr=Integer.toString(inf);

        if (inf > sup){
            return (new StringSList());
        }else{
            return range(inf+1, sup).cons(nbr);
        }

    }

    public static StringSList nextBtr(String btrString, int x){
        StringSList res= new StringSList(btrString, new StringSList());

        for (int i=0; i<x;i++){
            res= res.cons(Btr.btrSucc( res.car() ) );
        }
        res= res.reverse();
        return res;
    }




}