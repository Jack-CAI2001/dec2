package CSP;

import Contraintes.SquarePackingAbstract;
import Contraintes.SquarePackingInstance;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.search.strategy.Search;
import org.chocosolver.solver.search.strategy.selectors.values.IntDomainMin;
import org.chocosolver.solver.search.strategy.selectors.variables.Smallest;
import org.chocosolver.solver.variables.BoolVar;
import org.chocosolver.solver.variables.IntVar;

/*
 * Perfect Square Packing
 *
 */
/**
 * @author Daniel Diaz {@code <daniel.diaz@univ-paris1.fr>}
 */
public class SquarePackingSkeleton extends SquarePackingAbstract {

    public SquarePackingSkeleton(SquarePackingInstance pData) {
        this(pData.toString(), pData.getMasterSize(), pData.getSquaresSize());
    }

    public SquarePackingSkeleton(String problemName, int masterSize, int... squaresSize) {
        super(problemName, masterSize, squaresSize);
    }

    @Override
    public boolean  solvePacking() {
        Model model = new Model("Square Packing: " + problemName);

        IntVar[] x = new IntVar[nbSquares];
        IntVar[] y = new IntVar[nbSquares];

        for (int i = 0; i < nbSquares; i++) {
            x[i] = model.intVar("x" + i, 0, masterSize - squaresSize[i]);
            y[i] = model.intVar("y" + i, 0, masterSize - squaresSize[i]);
        }
        IntVar[] sz = new IntVar[nbSquares];

        for (int i=0; i<nbSquares;i++) {
            sz[i] = model.intVar(squaresSize[i]);
        }
        model.diffN(x,y, sz, sz, true).post();

        redondantConstraint(model, x);
        redondantConstraint(model, y);

        // *** SOLVE ***
        Solver solver = model.getSolver();
        solver.setSearch(
                Search.intVarSearch(new Smallest(),new IntDomainMin(),x),
                Search.intVarSearch(new Smallest(),new IntDomainMin(),y)
        );
        if (!solver.solve()) {
            return false;
        }

        // *** STORE SOLUTION AS INTEGER VALUES ***
        for (int i = 0; i < nbSquares; i++) {
            solX[i] = x[i].getValue();
            solY[i] = y[i].getValue();
            solSize[i] = squaresSize[i];
        }
        return true;
    }

    private void redondantConstraint(Model model, IntVar[] x) {
        for (int k = 0; k<masterSize;k++) {
            BoolVar []b = new BoolVar[nbSquares];
            for (int i = 0; i<nbSquares; i++) {
                b[i] = model.member(x[i], k-squaresSize[i]+1,k).reify();
            }
            model.scalar(b,squaresSize,"=",masterSize).post();
        }
    }


    //  *** A MAIN TO TEST ***
    public static void main(String... args) {
        new SquarePackingSkeleton(SquarePackingInstance.PS_1).solve("Choco");
    }
}
