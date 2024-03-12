import Contraintes.Permutations;

import java.util.List;

public class Apha12GT {
    public static void main(String[] args) {

//        [3, 8, 10, 4, 12, 2, 1, 11, 6, 7, 5, 9]
//        nbPerm =479001600
//        38825 milliseconds
        // On a utilisé la permutation

        long debut = System.currentTimeMillis();
        Permutations perm = new Permutations(13, 1);

        int nbPerm = 0;
        for (List<Integer> t : perm) {
            nbPerm++;
            if (isSolution(t)) {
                System.out.println(t);
            }
        }
        System.out.println("nbPerm =" + nbPerm);
        long fin = System.currentTimeMillis();
        System.out.println(fin-debut+ " milliseconds");

    }

    static boolean isSolution(List<Integer> t) {
        int A = t.get(0);
        int B = t.get(1);
        int C = t.get(2);
        int E = t.get(3);
        int F = t.get(4);
        int L = t.get(5);
        int N = t.get(6);
        int O = t.get(7);
        int Q = t.get(8);
        int R = t.get(9);
        int T = t.get(10);
        int U = t.get(11);
        int S = t.get(12);

        return (B+A+L+L+E+T == 24 && C+O+N+C+E+R+T == 48 && O+B+O+E == 34 && C+E+L+L+O == 29 && F+L+U+T+E == 32 && Q+U+A+R+T+E+T == 39 && S+O+L+O == 37);
    }
}
