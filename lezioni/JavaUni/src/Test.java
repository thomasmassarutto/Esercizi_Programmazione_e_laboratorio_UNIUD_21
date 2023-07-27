public class Test {
    public static IntSList range (int inf, int sup){

        if (inf > sup){
            return (new IntSList());
        }else{
            return range(inf+1, sup).cons(inf);
        }
    }

    public static void main (String[] args){

        IntSList list = range (1, 5);
        IntSList list2 = range (6, 10);

        System.out.println(list.append(list2));
    }
}
