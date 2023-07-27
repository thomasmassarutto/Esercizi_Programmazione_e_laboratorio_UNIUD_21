public class Main {
    public static void main(String[] args) {
        /*
        //area cilindro
        double res= MyFirstJavaProgram.areaCilindro(2.4, 5.1);
        System.out.println(res);
        //coniugazione
        String PlurMasch = MyFirstJavaProgram.pluraleSm("cane");
        System.out.println(PlurMasch);

        System.out.println(MyFirstJavaProgram.femminile("casa"));

        //pluralizzatore
        System.out.println(MyFirstJavaProgram.plurale("albero"));

        System.out.println(MyFirstJavaProgram.fibonacci(12));
        System.out.println(MyFirstJavaProgram.latoLungo(5));

        System.out.println(MyFirstJavaProgram.btrVal("+-+-."));
        */


        /*
        ********************ESEMPI IMPERATIVI********************
        */

        /*
        double[] v = new double[] {3.4, 2.1, 5.7, 4.0, 1.8 };
        ImperativeJava.insSort(v);
        for (int i=0; i< v.length;i++){
        System.out.println(v[i]);
        }
        */

        // IntSList
        IntSList L1 = new IntSList();
        L1= Test.range(1,4);
        IntSList L2 = new IntSList();
        L2= Test.range(8,9);
        //System.out.println(Test.range(1,8).toString()); //toSrtring è un override della procedura standard
        // x==x: l'uguale determina se sono lo stesso oggetto
        //possono esere di contenuto uguale, ma essere due
        //identità differenti

        System.out.println(L1.reverse());


    }


}

