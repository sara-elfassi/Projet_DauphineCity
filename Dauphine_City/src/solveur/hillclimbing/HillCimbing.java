package solveur.hillclimbing;

import java.util.ArrayList;
import java.util.List;

import sacADos.Objet;
import sacADos.SacADos;



public abstract class HillCimbing {

    protected Voisinage v;

    public HillCimbing(Voisinage v) {
        this.v = v;
    }

    protected abstract boolean Meilleur(List<Objet> voisin, List<Objet> solution, SacADos sac);

    public List<Objet> appliquerHillClimbing(SacADos sac, List<Objet> SolutionInitiale) {

        List<Objet> solution = new ArrayList<>(SolutionInitiale);
        boolean mieux = true;

        while (mieux) {
            mieux = false;
            List<List<Objet>> voisins = v.nouveaux_voisins(solution, sac);

            for (List<Objet> voisin : voisins) {
                if (Meilleur(voisin, solution, sac)) {
                    solution = voisin;
                    mieux = true;
                    break;
                }
            }
        }
        return solution;
    }
}
