package CSP;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.exception.ContradictionException;
import org.chocosolver.solver.variables.IntVar;

public class VendingMachineCSP {
    public static void main(String[] args) {
        Model model = new Model("VENDING MACHINE");
        int A = 1000;
        int B = 135;
        int N = 10;
        int C = A - B;
        // Données A prix payé
        // B prix de la boisson
        int[] valPieces = { 200, 100, 50, 20, 10, 5};
        IntVar[] v = model.intVarArray("v",valPieces.length,0, N);
        IntVar maxPiece = model.intVar("maxPieces", 0, N);
        IntVar nbPiece = model.intVar("nbPieces", 0, N*v.length);
        IntVar z = model.intVar(0,1000);
        model.max(maxPiece,v).post();
        model.sum(v,"=",nbPiece).post();
        model.arithm(nbPiece,"+",maxPiece,"=",z).post();

        model.setObjective(Model.MINIMIZE, nbPiece);
        model.scalar(v,valPieces,"=",C).post();
        Solver solver = model.getSolver();

        int meilleurSol = 0;
        int nombreSolution = 0;
        while (solver.solve()){
            nombreSolution++;
            System.out.print("Solution  " + nombreSolution + " : ");
            for (IntVar p : v){
                System.out.print(p.getValue() + " ");
            }
            System.out.println( " nbr pièce : " + nbPiece  +", " + maxPiece + " fois");
            meilleurSol = nbPiece.getValue();
        }
        System.out.println("best : " + meilleurSol);

    }

    }

