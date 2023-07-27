/*
* Disporre il massimo numero di regine in una
* scacchiera senza che si minaccino
* max sono 8 con una scacchiera normale 8x8
* */

/*
* Date n caselle e n regine, quante disposizioni valide?
* le rotazioni non valgono
*
* 1 casella, 1 regina: 1 soluzione
* 2 caselle, 2 regine: 0 sol (max 1 regine)
* 3 caselle, 2 regine: 0 sol (max 2 regine)
* 4 caselle, 4 regine: 2 sol
* 5 caselle, 5 regine: 10 sol
* 6 caselle, 6 regine: 4 sol
* 7 caselle, 8 regine: 40 sol
* 8 caselle, 8 regine: 92 sol
*/

/*
* DATO ASTRATTO: SCACCHIERA
*
*
* */

public class Main {
    public static void main(String[] args) {

        System.out.println(Queens.listOfSolutions(5));
    }
}