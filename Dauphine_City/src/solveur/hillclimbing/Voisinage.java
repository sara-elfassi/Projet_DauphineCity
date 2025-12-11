

package solveur.hillclimbing;

import java.util.ArrayList;
import java.util.List;

import sacADos.Objet;
import sacADos.SacADos;

public class Voisinage {

    private int t;

    public Voisinage(int t) {
        this.t = t;
    }

    public int getT() { return t; }

    public List<List<Objet>> nouveaux_voisins(List<Objet> solution, SacADos sac) {
        List<List<Objet>> voisins = new ArrayList<>();

        // Pour chaque objet possible du sac
        for (Objet o : sac.getObjets()) {

            //  Ajouter o
            if (!solution.contains(o)) {
                List<Objet> voisin = new ArrayList<>(solution);
                voisin.add(o);

                if (sac.respecte_budget(voisin))
                    voisins.add(voisin);
            }

            //  Retirer o
            if (solution.contains(o)) {
                List<Objet> voisin = new ArrayList<>(solution);
                voisin.remove(o);

                if (sac.respecte_budget(voisin))
                    voisins.add(voisin);
            }

            //  Retirer puis ajouter
            if (t >= 1) {
                for (Objet o2 : sac.getObjets()) {
                    if (solution.contains(o) && !solution.contains(o2)) {

                        List<Objet> voisin = new ArrayList<>(solution);
                        voisin.remove(o);
                        voisin.add(o2);

                        if (sac.respecte_budget(voisin))
                            voisins.add(voisin);
                    }
                }
            }
        }
        return voisins;
    }
}
