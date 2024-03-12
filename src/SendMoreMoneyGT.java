import Contraintes.KPermutations;

import java.util.List;

public class SendMoreMoneyGT {
    public static void main(String[] args) {
        // On utilise arrangement Apn = n! / (n-p)!
        long debut = System.currentTimeMillis();
        int variable = 8;
        int nbPossibilite = 10;
        KPermutations perm = new KPermutations(variable, nbPossibilite);

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
        int S = t.get(0);
        int E = t.get(1);
        int N = t.get(2);
        int D = t.get(3);
        int M = t.get(4);
        int O = t.get(5);
        int R = t.get(6);
        int Y = t.get(7);
        if (S != 0 && M != 0) {
            return MainGT.digitsToInt(S, E,N,D) + MainGT.digitsToInt(M, O,R,E) == MainGT.digitsToInt(M,O,N,E,Y);
        }
        return false;
    }
}
