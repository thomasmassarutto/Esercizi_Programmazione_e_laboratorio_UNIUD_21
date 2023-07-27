/*
* Realizza in modo imperativo Huffamn.java
* */

import huffman_toolkit.*;
import java.util.*;

// frame: una struttura per tenere e scrivere info
class Frame{

    public final Node node;
    public final String code;

    public Frame (Node node, String code){
        this.node= node;
        this.code= code;
    }//

}//Class Frame

class RecFrame{

    public Node lft, rgt;
    public int state; // 0,1,2

    public RecFrame (){
        state= 0;
        lft= null;
        rgt= null;
    }//

}//Class RecFrame

public class HuffmanImperativo {
    // i 128 chars disponibili
    public static final int CHARS= InputTextFile.CHARS;

    /* Conta le occorrenze di una lettera
     * array:
     * le posizioni sono i caratteri,
     * i valori il numero di occorrenza
     */
    public static int [] charFreq(String src){//src: nome di file
        // inizializzazione file
        int[] freq= new int[CHARS];
        for (int c=0;c<CHARS;c++){

            freq[c]= 0;
        }

        InputTextFile in = new InputTextFile(src);

        // scorro file e aggiorno contatore
        while (in.textAvailable()){
            char c= in.readChar();// il char si trasforma in int con Java
            freq[c]= freq[c] +1;
        }

        in.close();

        return freq;
    }//charFreq()

    /*RAPPRESENTAZIONE ALBERO HUFFMAN
     *
     * Un albero è fatto di nodi
     * Un nodo:
     * - foglia: associato a un carattere e a un peso
     * - intermedi: associati a sotto albero sx e dx, e a un peso
     *
     * servono 2 costruttori: nodo "foglia", nodo "intermedio"
     */

    public static Node huffmanTree(int[] freq){
        // PriorityQueue: vedi commenti classe Node
        PriorityQueue<Node> queue= new PriorityQueue<Node>();

        // Inserire gli alberelli per ogni carattere del testo
        for (int c=0; c<CHARS;c++){
            // vediamo se il carattere occorre nel testo
            if (freq [c]>0){
                // creo il nodo col costruttore "foglia"
                Node n= new Node((char) c, freq[c]);
                // inserisco il nodo in coda di priorità PriorityQueue
                queue.add(n);
            }
        }// for

        // finito il for si comincia a costruire alberi "intermedi"
        // utilizzando 2 a 2 i nodi foglia

        while (queue.size() > 1){
            // tolgo i 2 nodi a priorita alta:
            // primo nodo tolto:
            // poll(): toglie da coda priorità maggiore
            Node l= queue.poll();
            // secondo nodo tolto:
            Node r= queue.poll();

            // costruire albero che mette insieme i 2 nodi r e l
            Node n= new Node(l,r);

            // il nuovo alberello lo inserisco in coda
            queue.add(n);
        }
        // alla fine del while è rimasto un solo
        // elemento: l'albero di Huffman completo
        return queue.poll();
    }

    // ritorna la tabella con le corrispondenze dei codici di huffman
    // associa alle foglie il percorso
    public static String[] huffmanCodes(Node root){
        // stringhe che visualizzano le sequenze di bit
        String[] table= new String[CHARS];
        // creo stack per i Frame
        Stack<Frame> stack= new Stack<Frame>();
        // creo un frame iniziale
        stack.push( new Frame(root, "") );

        while ( !stack.empty() ) {
            // prende la cima dello stack
            Frame f= stack.pop();
            Node n= f.node;
            String code= f.code;

            if ( n.isLeaf() ){
                char c = n.symbol();
                table[c]= code;
            }else{
                // nb: ordine invertito in insrmento stack
                stack.push(new Frame( n.right(), code +"0"));
                stack.push(new Frame( n.left(), code +"0"));
            }
        }

        return table;
    }

    // String code: stringa che rappresenta le scelte di discesa a dx o sx dall'albero
    // 0: spostamento a sx
    // 1: spostamento a dx
    private static void buildTable(Node n, String code, String[] table){

        if ( n.isLeaf() ) {
            // se il nodo è una foglia, ho trovato un carattere
            char c= n.symbol();
            table[c]= code;// le indicazioni del percorso per arrivare alla foglia dalla radice
        } else{
            // visito albero sx
            buildTable(n.left(), code  + "0", table);
            // visito albero dx
            buildTable(n.right(), code  + "1", table);
        }
    }// buildTable

    // procedura imperativa che restituisce la codifica del
    // sotto albero radicato al nodo fornito
    public static String flatTree(Node root) {
        // rapresentazione albero
        String flat = "";
        // crea stack
        Stack<Node> stack = new Stack<Node>();
        // inserire in cima Node root, radice di albero
        stack.push(root);

        while (!stack.empty()) {
            // prende la cima dello stack
            Node n = stack.pop();// n: nodo generico della iterazione
            if (n.isLeaf()) {// foglia

                char c = n.symbol();
                if (c == '@' || c == '\\') {
                    flat = flat + "\\" + c;
                } else {
                    flat = flat + c;
                }
            }else {// nodo intermedio: scrivo @
                flat = flat + "@";
                // metto in coda i sotto albero dx e sx
                stack.push(n.right());
                stack.push(n.left());
            }
        }
        return flat;
    }


    public static void compress(String src, String dst){
        // occorrenze dei char
        int [] freq= charFreq(src);
        // costruire albero huffman
        Node root= huffmanTree(freq);
        // creare tabella
        String[] tab= huffmanCodes(root);


        InputTextFile in = new InputTextFile (src);
        OutputTextFile out = new OutputTextFile (dst);

        // intestazione: viene scritto il peso dell'albero
        // come prima riga del file compresso
        out.writeTextLine( "" +root.weight() );
        // stampo rappresentaziomne albero
        out.writeTextLine( flatTree(root) );

        // compressione vera
        while( in.textAvailable() ){

            char c= in.readChar();
            //
            out.writeCode( tab[c] );
        }

        in.close();
        out.close();
    }

    // ricostruisce un albero di Huffman
    public static Node restoreTree(InputTextFile in){

        // creo stack per i Frame
        Stack<RecFrame> stack= new Stack<RecFrame>();
        // creo un frame iniziale
        stack.push( new RecFrame() );

        Node n= null;

        while (!stack.empty()){
            // non lo tolgo, lo leggo e basta
            RecFrame rec= stack.peek();
            // devo capire in che stato sono

            if (rec.state == 0){
                char c= in.readChar();
                // se ha letto codifica nodo intermedio
                if (c == '@'){

                    rec.state=1;
                    stack.push(new RecFrame());
                }else {
                    if (c== '\\'){
                        c= in.readChar();
                    }
                    n= new Node(c, 0);
                    stack.pop();
                }
            } else if (rec.state==1) {

                rec.lft= n;
                rec.state=2;
                stack.push(new RecFrame());
            }else {// rec.state==2

                rec.rgt= n;
                n = new Node(rec.lft, rec.rgt);
                stack.pop();
            }
        }

        return n;
    }

    public static void decompress(String src, String dst){

        InputTextFile in = new InputTextFile (src);
        OutputTextFile out = new OutputTextFile (dst);

        // leggere la prima riga d intestazione del file d input
        int count= Integer.parseInt( in.readTextLine() );// forzo interpretazione stringa a int
        //leggo la rappresentazione dell'albero per ricostruirlo
        // pertendo dalla lettura del file di input
        Node root= restoreTree(in);
        // salto a capo
        String dummy= in.readTextLine();

        // leggo i bit della codifica huffman
        for( int i= 0; i<count; i++ ){

            Node n= root;
            // legge un codice e lo interpreta come le direzioni
            // per arrivare a foglia
            do {
                // fintanto che non è foglia, leggo un bit singolo da src
                int b= in.readBit();
                //
                if (b==0){//scendi a sx
                    n=n.left();// nb: n= node
                }else{// scendi a dx
                    n=n.right();
                }

            }while( !n.isLeaf() );

            char c=n.symbol();

            out.writeChar(c);
        }

        in.close();
        out.close();
    }
}// Class Huffman