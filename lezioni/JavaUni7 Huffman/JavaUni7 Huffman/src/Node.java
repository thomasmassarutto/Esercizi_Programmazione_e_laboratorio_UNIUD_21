/*
* Nodi dell'albero di Huffman
*
* Un albero è fatto di nodi
* Un nodo:
* - foglia: associato a un carattere e a un peso
* - intermedi: associati a sottoalbero sx e dx, e a un peso
*
* servono 2 costruttori: nodo "foglia", nodo "intermedio"
* */

// implements Comparable <Node>: dice che i Node sono comparabili
// gli oggetti Comparable sono riconosciuti, ma bisogna dare un
// criterio per dire come comparare (sono comparabili, ma come?)
// per comparare bisogna introdurre il metodo compareTo(Node)
// sara confrontabile con PriorityQueque
public class Node implements Comparable <Node> {
    // variabili istanza
    // carattere
    private final char sym;
    // Peso
    private final int wgt;
    // Nodo SX
    private final Node lft;

    // Nodo DX
    private final Node rgt;

    //costruttore "foglia"
    public Node (char c, int w){ // c: carattere associato, w: weight associato a c

        sym= c;
        wgt= w;
        lft= null;
        rgt= null;
    }

    //costruttore "intermedio"
    public Node (Node l, Node r){ // l, r: nodi DISCENDENTI a sinistra e a destra

        sym= (char) 0;
        wgt= l.weight() + r.weight();
        lft= l;
        rgt= r;
    }

    // metodi
    public boolean isLeaf(){
        //se il nodo di destra non esiste,
        // allora è foglia (funziona anche con rgt)
        return (lft == null);
    }

    public char symbol(){
        return sym;
    }

    public  int weight(){
        return wgt;
    }

    // per sapere chi è il figlio sinistro o destro
    public Node left(){
        return lft;
    }

    public Node right(){
        return rgt;
    }

    // intestazione standard
    public int compareTo(Node other){
        // int: -1: destinatario minore dell'argomento mione, 0 ,1
        if (this.weight() < other.weight() ){
            // this è piu piccolo
            return -1;
        } else if (this.weight() > other.weight()){
            // this è piu grande
            return +1;
        } else {
            // this e other uguali
            return 0;
        }
    }
}
