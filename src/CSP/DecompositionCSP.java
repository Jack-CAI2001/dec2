package CSP;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.constraints.nary.automata.FA.FiniteAutomaton;
import org.chocosolver.solver.search.strategy.Search;
import org.chocosolver.solver.variables.BoolVar;
import org.chocosolver.solver.variables.IntVar;

public class DecompositionCSP {
    public static void main(String... args) {
        // Données:
        // n: nb à décomposer
        // CSP
        // Variables : Xi tableau de n variables
        // Domaines 0...n pour tout Xi
        // Contraintes Somme Xi=n  0<=i<n
        int n = 10;
        Model model = new Model("Decomposition");
        IntVar[]xi = model.intVarArray("v",n,0,n);
        model.sum(xi,"=",n).post();
        avoidDuplicate2(model, xi ,n);
        model.regular(xi,new FiniteAutomaton("[1-<"+n+">]+0*")).post();
        Solver solver = model.getSolver();
        solver.setSearch(Search.inputOrderLBSearch(xi));
        while (solver.solve()) {
            for (IntVar v : xi) {
                if (v.getValue() != 0) {
                    System.out.print(v.getValue() + " ");
                }
            }
            System.out.println();
        }
        System.out.println(solver.getSolutionCount());


    }

    private static void avoidDuplicate(Model model,IntVar[] xi, int n) {
        model.regular(xi,new FiniteAutomaton("[1-<"+n+">]+0*")).post();

    }

    private static void avoidDuplicate2(Model model,IntVar[] xi, int n) {
        BoolVar[] b = model.boolVarArray(n);
        for (int i=0; i<n; i++) {
            model.reifyXeqC(xi[i], 0, b[i]);
            if (i>0) {
                model.arithm(b[i-1],"<=",b[i]).post();
            }
        }

    }


}
