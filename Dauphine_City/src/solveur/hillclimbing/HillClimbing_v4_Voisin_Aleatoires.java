
package solveur.hillclimbing;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import sacADos.Objet;
import sacADos.SacADos;

public class HillClimbing_v4_Voisin_Aleatoires extends HillCimbing {

    public HillClimbing_v4_Voisin_Aleatoires(Voisinage v) {
        super(v);
    }
	@Override
	protected boolean Meilleur(List<Objet> voisin, List<Objet> solution, SacADos sac) {
		// TODO Auto-generated method stub
		return v != null && voisin != null && sac.utilite(voisin) >= sac.utilite(solution);
	}
    @Override
    public List<Objet> appliquerHillClimbing(SacADos sac, List<Objet> solutionInitiale) {
        List<Objet> solution = new ArrayList<>(solutionInitiale);
        boolean trouveMeilleur = true;

        while (trouveMeilleur) {
            trouveMeilleur = false;
            List<List<Objet>> voisins = v.nouveaux_voisins(solution, sac);
            Collections.shuffle(voisins); // voisins aléatoires
            for (List<Objet> voisin : voisins) {
                if (Meilleur(voisin, solution, sac)) {
                    solution = voisin;
                    trouveMeilleur = true;
                    break;
                }
            }
        }
        return solution;
    }

}
