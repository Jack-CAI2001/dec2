import Contraintes.Permutations;

import java.util.List;

public class MagicSquareGT {
    public static void main(String[] args) {
        long debut = System.currentTimeMillis();
        Permutations perm = new Permutations(16, 1);
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
        int n = t.size();
        int n2 = n*n;


        return true;
    }
}
