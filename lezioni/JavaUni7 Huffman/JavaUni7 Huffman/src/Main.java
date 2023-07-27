public class Main {
    public static void main(String[] args) {
        //int res= IOTest.copyFile("./src/IOTest.java", "IOTest.txt");
        //int [] res= Huffman.charFreq("./src/hellothere.txt");
        /*
        for (int i= 0; i<128 ;i++) {
            System.out.println( (char) (i) +  " : " + res[i]);
        }
        //

        int [] res= Huffman.charFreq("./src/hellothere.txt");
        Node tree= Huffman.huffmanTree(res);
        //System.out.println(tree);


        String[] tab= Huffman.huffmanCodes(tree);
        System.out.println();
        */


        HuffmanImperativo.compress("./src/hellothere.txt", "./src/hellothereCompressed.txt");
        HuffmanImperativo.decompress("./src/hellothereCompressed.txt", "./src/hellothereDecompressed.txt");


    }
}