public class esercizio {
    //somma 1 a rappresentazione 3naria, tradotto da scheme
    public static String btrSucc (String btr){
        int n = btr.length();
        char lsb = btr.charAt(n-1);

        if (n == 1){
            if (lsb == '+'){
                return "+-";
            }else{
                return "+";
            }
        } else{
            String pre = btr.substring(0, n-1);
            if (lsb == '+'){
                return btrSucc(pre) + '-';
            }else {
                return lsb == '-' ? pre + "." : pre + "+";
            }
        }
    }

    // complemento a 1 con variabile ausiliaria:
    // aggiunge alla stringa ausiliaria il complemento a 1
    public static String onesComplement (String btr) {

        String btrCpl="";
        int btrLength = btr.length();

        for (int i= 0; i < btrLength; i++){
            if (btr.charAt(i) == '1') {
                btrCpl= btrCpl + "0";
            }else{
                btrCpl= btrCpl + "1";
            }
        }

        return btrCpl;
    }
    // complemento a 1 senza variabile ausiliaria
    // cambia la stringa btr inserita
    public static String onesComplementV2 (String btr) {

        int btrLength = btr.length();

        for (int i= 0; i < btrLength; i++){
            if (btr.charAt(i) == '1') {
                btr= btrHead(btr, i)+ "0" + btrTail(btr, i);
            }else{
                btr= btrHead(btr, i)+ "1" + btrTail(btr, i);
            }
        }
        return btr;
    }
    private static String btrHead (String btr, int index ){

        String head= "";
        if (index== 0){
            return head;
        }else{
            return btr.substring(0, index);
        }
    }

    private static String btrTail (String btr, int index ){

        String tail= "";
        if (index== btr.length() ){
            return tail;
        }else{
            return btr.substring(index+1);
        }
    }
}
