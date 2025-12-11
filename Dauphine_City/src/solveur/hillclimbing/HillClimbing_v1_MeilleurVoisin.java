

package solveur.hillclimbing;

import java.util.List;
import sacADos.Objet;
import sacADos.SacADos;

public class HillClimbing_v1_MeilleurVoisin extends HillCimbing {

    public HillClimbing_v1_MeilleurVoisin(Voisinage v) {
        super(v);
    }

    @Override
    protected boolean Meilleur(List<Objet> voisin, List<Objet> solution, SacADos sac) {
        return sac.utilite(voisin) > sac.utilite(solution);
    }
}
