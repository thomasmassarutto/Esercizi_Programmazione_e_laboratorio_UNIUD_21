/*
*
josephus(2) → (1, 2)
josephus(3) → (1, 2)
josephus(4) → (4, 1)
josephus(5) → (2, 4)
josephus(6) → (5, 1)
josephus(7) → (1, 4)
josephus(8) → (4, 7)
josephus(12) → (5, 10)
josephus(1500) → (338, 905)
*
* */


public class Main {
    public static void main(String[] args) {

        IntSList winners= josephus(4);
        System.out.println(winners);
        // test
        System.out.println(
        "test: \n"+
        josephus(2) + "\n" +
        josephus(3) + "\n" +
        josephus(4) + "\n" +
        josephus(5) + "\n" +
        josephus(6) + "\n" +
        josephus(7) + "\n" +
        josephus(8) + "\n" +
        josephus(12) + "\n" +
        josephus(1500) + "\n"
        );

    }

    public static IntSList josephus (int n){
        RoundTable tav= new RoundTable(n);

        while ( tav.numberOfKnights() > 2){

            tav= tav.serveNeighbour();
            tav= tav.passJug();
        }
        return tav.servingKnights();
    }
}