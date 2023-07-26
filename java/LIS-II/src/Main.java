import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        int[] s =  new int[]  {8, 9, 10, 11, 12, 4, 5, 6, 7, 1, 2, 3}  ;

        System.out.println("llis: " + BottomUpLIS.llisDP(s));
        System.out.println("LIS: " + Arrays.toString(BottomUpLIS.lisDP(s)));

    }
}