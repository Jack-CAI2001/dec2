package CSP;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.variables.IntVar;

public class TwoTwoFourCSP {
    public static void main(String[] args) {
        // CSP1
        // Variable: T,W,O,F,U,R
        // Domaines: 0...9 pour tous ( sauf T et F qui est 1...9)
        // Contraintes 100T + 10W + O + 100T + 10W + O = 1000F + 100O + 10U + R
        // allDifferent(T,W,O,F,U,R)


        // CSP2
        // Variable: T,W,O,F,U,R,C1,C2,C3
        // Domaines: 0...9 pour tous sauf C1,C2,C3 qui ont 0...1
        // T>0 F>0
        // allDifferent(T,W,O,F,U,R)
        // O + O = 10 C1 + R
        // C1+W+W = 10C2 + U
        // C2 + T + T = 10C3 + O
        // C3= F


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

        // Vending machine
        // Données A prix payé
        // D prix de la boisson
        // pièces de 200 100 50 20 10 5
        // N nombre d'occurences de chaque pièce
        // COnstante C = A - D
        // Variable P200,...,P5 Nb de pieces de 200 100 5 etc
        // Domaines 0...N pour tous ou 0...[N/200] 0...[N/100]
        // Contraintes
        // 200xP200+100xP100+...+5xP5 = C


        Model model = new Model("TWO+TWO=FOUR");
        IntVar T = model.intVar("T", 1, 9); // T != 0
        IntVar W = model.intVar("W", 0, 9);
        IntVar O = model.intVar("O", 0, 9);
        IntVar F = model.intVar("F", 1, 9); // F != 0
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
        // one solution
        System.out.println(solver.findSolution());

    }

}
