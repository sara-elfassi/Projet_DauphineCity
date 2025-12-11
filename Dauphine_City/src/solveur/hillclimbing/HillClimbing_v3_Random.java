package solveur.hillclimbing;
import java.util.ArrayList;
import java.util.List;

import sacADos.Objet;
import sacADos.SacADos;

public class HillClimbing_v3_Random {

    private int nb_recommence;
    private HillCimbing max_global_solution;   // on prend  la v1 ou v2

    public HillClimbing_v3_Random(HillCimbing max_global_solution, int nb_recommence) {
        this.max_global_solution = max_global_solution;
        this.nb_recommence = nb_recommence;
    }

    public List<Objet> solve(SacADos sac) {

        List<Objet> meilleur_sol = new ArrayList<>();
        int meilleur_val = 0;

        for (int i = 0; i < nb_recommence; i++) {

            List<Objet> start = sac.solution_initiale_aleatoire();

            List<Objet> sol = max_global_solution.appliquerHillClimbing(sac, start);
            int val = sac.utilite(sol);

            if (val > meilleur_val) {
            	meilleur_val = val;
            	meilleur_sol = sol;
            }
        }

        return meilleur_sol;
    }
}
