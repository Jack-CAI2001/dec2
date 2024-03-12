package Contraintes;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Magic Square Skeleton
 */
public class MagicSquareSkeletonBT2 extends MagicSquareAbstract {


    // MagicSquareSkeletonBT3 crée une grande arraylist de coordonnées avec comme taille n2
    // et mettre les coordonnées de la 1er diagonale
    // solveMagicSquare(int k)
    // if (n>= n2)
    // return true
    // int i = vars.get(k).i;
    // int j = vars.get(k).j;

    private final boolean[] taken;
    private ArrayList<Integer> vals = new ArrayList<>();

    public static void main(String... args) {
        long debut = System.currentTimeMillis();
        MagicSquareAbstract m = new MagicSquareSkeletonBT2(5);
        if (m.solveMagicSquare()) {
            m.printMagicSquare();
        }
        long fin = System.currentTimeMillis();
        System.out.println(fin-debut+ " milliseconds");
    }

    public MagicSquareSkeletonBT2(int n){
        super(n);
        taken = new boolean[n2 + 1];
        for (int v=1; v <= n2; v++) {
            vals.add(v);
        }
        Collections.shuffle(vals);
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

         for (int v: vals
              ) {
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
