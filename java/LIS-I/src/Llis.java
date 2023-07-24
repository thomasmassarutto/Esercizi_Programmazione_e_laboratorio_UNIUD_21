public class Llis {
    // PARTE I
    // Top Down

    private final static int UNKNOWN= -1;

    public static int llisTD1(int[] arr){
        // tutti s[i] sono <= n con n: lengt(s)

        int n = arr.length+1;
        // inizializzazione array
        int[][] mem = new int[n][n];
        for (int i =0; i<arr.length; i++ ) {
            for(int j = 0; j<arr.length; j++){
                mem[i][j]= UNKNOWN;
            }
        }

        return llisTD1Rec(arr, mem, 0, UNKNOWN);
    }

    private static int llisTD1Rec(int[] arr, int[][] mem, int currentIdx, int prevIdx) {
        if (currentIdx == arr.length) {
            return 0;
        }

        if (mem[currentIdx][prevIdx + 1] != UNKNOWN) {
            return mem[currentIdx][prevIdx + 1];
        }

        int seleziona = 0;
        if (prevIdx == UNKNOWN || arr[currentIdx] > arr[prevIdx]) {
            seleziona = 1 + llisTD1Rec(arr, mem, currentIdx + 1, currentIdx);
        }

        int salta = llisTD1Rec(arr, mem, currentIdx + 1, prevIdx);

        int maxLIS = Math.max(seleziona, salta);
        mem[currentIdx][prevIdx + 1] = maxLIS;

        return maxLIS;
    }

    // parte II
    //Bottom Up

}
