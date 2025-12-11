
package solveur.hillclimbing;

import java.util.List;
import sacADos.Objet;
import sacADos.SacADos;

public class HillClimbing_v2_Plateau extends HillCimbing {

    public HillClimbing_v2_Plateau(Voisinage v) {
        super(v);
    }

    @Override
    protected boolean Meilleur(List<Objet> voisin, List<Objet> solution, SacADos sac) {
        return sac.utilite(voisin) >= sac.utilite(solution);
    }
}
