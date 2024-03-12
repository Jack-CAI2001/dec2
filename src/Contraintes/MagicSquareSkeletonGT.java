package Contraintes;

import java.util.List;

/**
 * Magic Square Skeleton
 */
public class MagicSquareSkeletonGT extends MagicSquareAbstract {

    public static void main(String... args) {
        MagicSquareAbstract m = new MagicSquareSkeletonGT(4);
        if (m.solveMagicSquare()) {
            m.printMagicSquare();
        }
    }

    public MagicSquareSkeletonGT(int n){
        super(n);
    }
    
    @Override
    public boolean solveMagicSquare() {

        long debut = System.currentTimeMillis();
        Permutations perm = new Permutations(n2, 1);

        int nbPerm = 0;
        for (List<Integer> t : perm) {
            nbPerm++;
            if (isSolution(t)) {
                return true;
            }
        }
        System.out.println("nbPerm =" + nbPerm);
        long fin = System.currentTimeMillis();
        System.out.println(fin-debut+ " milliseconds");
        return false;
    }

    boolean isSolution(List<Integer> t) {
        for (int i = 0; i < this.n; i++) {
            for(int j = 0; j < this.n; j++) {
                this.t[i][j] = t.get(i * this.n + j);
            }
        }

        for (int i = 0; i < this.n; i++) {
            if (sumLine(i) != sum || sumColumn(i) != sum) {
                return false;
            }
        }

        return sumDiagonal1() == sum && sumDiagonal2() == sum;
    }
}
