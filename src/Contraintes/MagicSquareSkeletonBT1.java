package Contraintes;

import java.util.List;

/**
 * Magic Square Skeleton
 */
public class MagicSquareSkeletonBT1 extends MagicSquareAbstract {

    private final boolean[] taken;

    public static void main(String... args) {
        long debut = System.currentTimeMillis();
        MagicSquareAbstract m = new MagicSquareSkeletonBT1(4);
        if (m.solveMagicSquare()) {
            m.printMagicSquare();
        }
        long fin = System.currentTimeMillis();
        System.out.println(fin-debut+ " milliseconds");
    }

    public MagicSquareSkeletonBT1(int n){
        super(n);
        taken = new boolean[n2 + 1];
    }
    
    @Override
    public boolean solveMagicSquare() {
        return solveMagicSquare(0,0);
    }

     boolean solveMagicSquare(int i, int j) {
        if (j >= n) {
            i++;
            j=0;
        }
        if (i>=n) {
            return true;
        }

        for (int v=1; v <= n2; v++) {
            t[i][j] = v;
            if (possibleValue(i,j,v)) {
                taken[v] = true;
                if (solveMagicSquare(i, j+1)) {
                    return true;
                }
                taken[v] = false;
            }
            t[i][j] = 0;
        }
        return false;
     }

     boolean possibleValue(int i, int j, int v) {
        if (taken[v]) return false;
        if (j==n-1 && sumLine(i) != sum) return false;
        if (i==n-1 && sumColumn(j) != sum) return false;
        if (i==n-1 && j==n-1 && sumDiagonal1()!= sum) return false;
        if (i==n-1 && j==0 && sumDiagonal2()!= sum) return false;
        return true;
     }
}
