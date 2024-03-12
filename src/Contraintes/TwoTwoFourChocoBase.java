package Contraintes;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.exception.ContradictionException;
import org.chocosolver.solver.variables.IntVar;

/**
 *
 * @author Daniel Diaz {@code <daniel.diaz@univ-paris1.fr>}
 */
public class TwoTwoFourChocoBase {

    public static void main(String[] args) throws ContradictionException {
        Model model = new Model("TWO+TWO=FOUR");

        IntVar T = model.intVar("T", 1, 9);  // T != 0
        IntVar W = model.intVar("W", 0, 9);
        IntVar O = model.intVar("O", 0, 9);
        IntVar F = model.intVar("F", 1, 9);  // F != 0
        IntVar U = model.intVar("U", 0, 9);
        IntVar R = model.intVar("R", 0, 9);

        model.allDifferent(T, W, O, F, U, R).post();

        IntVar[] vars = new IntVar[]{
            T, W, O,
            T, W, O,
            F, O, U, R};
        int[] coeffs = new int[]{
            100, 10, 1,
            100, 10, 1,
            -1000, -100, -10, -1};
        model.scalar(vars, coeffs, "=", 0).post();

        Solver solver = model.getSolver();
        solver.propagate();
        solver.showShortStatistics();
        System.out.println(solver.findSolution());
    }
}
