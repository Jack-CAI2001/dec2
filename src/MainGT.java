public class MainGT {
    public static void main(String[] args) {
        cryptoArith1();
    }

    static void cryptoArith1() {
        long debut = System.currentTimeMillis();
        for (int T = 1; T <= 9; T++) {
            for(int W = 0; W <= 9; W++) {
                if (W == T) continue;
                for (int O = 0; O <= 9; O++) {
                    if (O == T || O == W) continue;
                    for (int F = 1; F <= 9; F++) {
                        if (F == T || F == W || F == O) continue;
                        for (int U = 0; U <= 9; U++) {
                            if (U == T || U == W || U == O || U == F) continue;
                            for (int R = 0; R <= 9; R++) {
                                if (R == T || R == W || R == O || R == F || R == U) continue;
                                if (isSolution(T, W, O, F, U, R)) {
                                    System.out.println(T + "" + W + "" + O + "" + F + "" + "" + U + "" + R);
                                }
                            }
                        }
                    }

                }
            }
        }

        long fin = System.currentTimeMillis();
        System.out.println(fin-debut);
    }

    static boolean isSolution(int T, int W, int O, int F, int U, int R) {
        return digitsToInt(T, W, O) + digitsToInt(T, W, O) == digitsToInt(F, O, U, R);
    }

    static int digitsToInt(int... t) {
        int res = 0;
        for (int x:t) {
            res = res *10 + x;
        }
        return res;
    }
}

