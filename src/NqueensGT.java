import Contraintes.Permutations;

import java.util.List;

public class NqueensGT {
    public static void main(String[] args) {
        // combinaison Cpn = n! / (p! (n-p)!)

        long debut = System.currentTimeMillis();
        Permutations perm = new Permutations(5);

        int nbPerm = 0;
        int compteur = 0;
        for (List<Integer> t : perm) {
            nbPerm++;
            if (isSolution(t)) {
                compteur++;
                System.out.println(t);
            }
        }
        System.out.println(compteur);
        System.out.println("nbPerm =" + nbPerm);
        long fin = System.currentTimeMillis();
        System.out.println(fin-debut+ " milliseconds");
    }

    static boolean isSolution(List<Integer> t) {
        int n = t.size();
        for (int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                if (Math.abs(i-j) == Math.abs(t.get(i)-t.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

}