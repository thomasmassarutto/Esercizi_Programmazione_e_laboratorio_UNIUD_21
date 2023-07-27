import huffman_toolkit.*;

public class IOTest {

    public static int copyFile(String src, String dst){

        InputTextFile in = new InputTextFile (src);
        OutputTextFile out = new OutputTextFile (dst);
        int count=0;

        //while( in.textAvailable() ){
        while (in.bitsAvailable()){

            // String s= in.readTextLine();
            //char c= in.readChar();
            int b= in.readBit();

            //out.writeTextLine(s);
            //out.writeChar(c);
            out.writeBit(b);

            count= count +1;
        }

        in.close();
        out.close();

        return count;
    }
}// class IOTest