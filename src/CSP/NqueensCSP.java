package CSP;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.variables.IntVar;

public class NqueensCSP {
    public static void main(String[] args) {
        // N-QUEENS
        // Variable: N variables Qi(i = n° de colonne)
        // Domaines: 1...n pour toutes les var Qi
        // Contraintes: allDiff(Q1,...,Qn)
        // Qi et Qj sur même diagonale |i-j|=|Qi-Qj|

        // Variables Qi,j 1<=i<=n 1<=j<=n
        // Domaine: 0...1 pour tous
        // Contraintes Pour tous 1<=j<=n somme 1<=i<=n Qi,j =1 // 1 reine par colonne
        // Contraintes Pour tous 1<=i<=n somme 1<=j<=n Qi,j =1 // 1 reine par ligne
        // Pour tous 2Sommes Qi,j <= 1 // au plus 1 diagonale
        int N = 30;
        Model model = new Model("NQUEENS");
        IntVar[] Qi = model.intVarArray("i",N,0, N-1);
        model.allDifferent(Qi).post();
        for (int i = 0; i < N; i++) {
            for (int j=i+1;j < N;j++) {
                model.arithm(Qi[i],"!=",Qi[j],"+",j-i).post();
                model.arithm(Qi[i],"!=",Qi[j],"-",j-i).post();

            }
        }
        Solver solver = model.getSolver();
        System.out.println(solver.findSolution());
        //System.out.println(solver.getSolutionCount());

    }
}
