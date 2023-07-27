/*
* la prima cosa da fare: determinare il numero delle occorrenze
*
*
* */
/*
* Huffman: libreria
* legge un testo e calcola le occorrenze dei
* caratteri nel testo
* */

import huffman_toolkit.*;

import java.util.*;

public class Huffman {
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
        String [] table= new String[CHARS];

        //compila ricorsivamente la tabella
        buildTable(root, "", table);

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

    // file destinazione, file compresso v1
    public static Node compressI(String src, String dst){
        // occorrenze dei char
        int [] freq= charFreq(src);
        // costruire albero huffman
        Node root= huffmanTree(freq);
        // creare tabella
        String[] tab= huffmanCodes(root);

        // scrivere file compresso
        // aprire src in lettura
        // aprire dst in scrittura

        InputTextFile in = new InputTextFile (src);
        OutputTextFile out = new OutputTextFile (dst);

        while( in.textAvailable() ){

            char c= in.readChar();
            //
            out.writeCode( tab[c] );
        }

        in.close();
        out.close();

        return root;
    }

    // decompressione primo metodo: non serve intestazione nel file compresso
    // accede al file non compresso e lo "usa come guida"
    public static void decompressI(String src, String dst, Node root){

        InputTextFile in = new InputTextFile (src);
        OutputTextFile out = new OutputTextFile (dst);

        int count= root.weight();

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

    /*RAPPRESENTAZIONE ALBERO HUFFMAN
    * Visita top down di albero
    *          (G,A,C,T)
    *           /     \
    *       (G,A,C)     T
    *       /   \
    *    (G,A)   C
    *     / \
    *    G   A
    *
    * @     : rappresenta nodo/radice
    * ACGT  : foglie
    * @ [ @ [ @ G A ] C ] T
    *
    * La rappresentazione diventa:
    * @@@GACT
    *
    * per interpretare albero
    *
    * Il secondo albero dell'esempio diventa
    * @ [ @ T [ @ R E ] ] [@ A [@ [ @ [ @ [ @ O S ] [I] ] [ L ]]]
    * @@T@RE@A@@@OSIL
    * */
    // procedura ricorsiva che restituisce la codifica del
    // sotto albero radicato al nodo fornito
    public static String flatTree(Node n){

        if (n.isLeaf()){// foglia
            char c= n.symbol();
            // controllo i caratteri particolari utilizzati
            // internamente per disambiguarli
            if(c == '@' || c == '\\'){
               return "\\" + c;
            }
            return "" +c;
        }else{// nodo intermedio
            // devo allora decodificare sottoalbero dx e sx

           return "@"+flatTree(n.left()) + flatTree(n.right());
        }
    }
    /* compressione file v2: prima di transcodificare i caratteri
     viene scritta un intestazione.
     il file diventa:

     [ INTESTAZIONE
      * peso *
      * rappresentazione albero *
     ]
     [ CONTENUTO FILE
      * CONTENUTO FILE *
     ]
    */

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

        char c= in.readChar();

        if (c == '@'){// se @ allora -> nodo intermedio
            // chiamo ricorsivamente la funzioen per dx e sx
            Node l= restoreTree(in);
            Node r= restoreTree(in);

            // ritorno il nodo corrispondente:
            // associato alla @ ha come sottoalbero dx Node r
            // e come sottoalbero sx Node l
            return new Node(l,r);
        }else{// allora foglia
            if (c == '\\'){
                c= in.readChar();
            }
            return new Node(c, 0);  // nuovo nodo con peso 0,
                                        // il peso è inutile saperlo
        }
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