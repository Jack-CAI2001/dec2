package CSP;

import Contraintes.MagicSquareAbstract;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.search.strategy.Search;
import org.chocosolver.solver.search.strategy.selectors.values.IntDomainMiddle;
import org.chocosolver.solver.search.strategy.selectors.values.IntDomainRandom;
import org.chocosolver.solver.search.strategy.selectors.variables.FirstFail;
import org.chocosolver.solver.search.strategy.selectors.variables.InputOrder;
import org.chocosolver.solver.variables.IntVar;

/**
 * Magic Square Skeleton
 */
public class MagicSquareSkeletonCSP extends MagicSquareAbstract {

    public static void main(String... args) {
        MagicSquareAbstract m = new MagicSquareSkeletonCSP(10);
        if (m.solveMagicSquare()) {
            m.printMagicSquare();
        }
    }

    public MagicSquareSkeletonCSP(int n){
        super(n);
    }
    
    @Override
    public boolean solveMagicSquare() {
        // Magic Square
        // Variables: Xi,j 1<= i et j<=n
        //Domaines: 1...n² pour toutes les variables
        //Constantes: s=n(n²+1)/2
        // Contraintes : pour tous 1<=i<=n sommes 1<=j<=n Xi,j=s // toute ligne vaut s
        // Pour tous 1<=j<=n sommes 1<=i<=n Xi,j=s // toute colonne vaut s
        // Sommes 1<=i<=n Xi,i =s // diagonal 1 vaut s
        // Somme 1<=i<=n Xi,n+1-i =s
        // allDiff(X 1<=i,j<=n)

        Model model = new Model("Magic Square");
        IntVar[][] v = model.intVarMatrix("v",n,n,1,n2);

        IntVar[] all = new IntVar[n2];
        for (int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                all[i*n+j] = v[i][j];
            }
        }
        model.allDifferent(all).post();

        for (int i = 0; i < n; i++) {
            model.sum(v[i],"=",sum).post();
        }

        for (int j = 0; j < n; j++) {
            IntVar[] col = new IntVar[n];
            for(int i = 0; i < n; i++) {
                col[i] = v[i][j];
            }
            model.sum(col,"=",sum).post();
        }

        IntVar[] d1 = new IntVar[n];
        for (int i = 0; i<n;i++) {
            d1[i] = v[i][i];
        }
        model.sum(d1,"=",sum).post();

        IntVar[] d2 = new IntVar[n];
        for (int i = 0; i<n;i++) {
            d2[i] = v[i][n - i - 1];
        }
        model.sum(d2,"=",sum).post();

        Solver solver = model.getSolver();
        solver.setSearch(Search.intVarSearch(new InputOrder<>(model),new IntDomainMiddle(true),d1),
                Search.intVarSearch(new InputOrder<>(model),new IntDomainRandom(1),all)
                );

        solver.setSearch(Search.intVarSearch(new InputOrder<>(model),new IntDomainRandom(1),d1),
                Search.intVarSearch(new FirstFail(model),new IntDomainRandom(1),all)
        );

        if (!solver.solve()) {
            return false;
        }
        for (int i=0; i<n;i++) {
            for (int j = 0; j<n;j++) {
                t[i][j] = v[i][j].getValue();
            }
        }

        return true;
    }
}
