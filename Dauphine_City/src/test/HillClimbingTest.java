package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sacADos.Objet;
import sacADos.SacADos;
import solveur.hillclimbing.HillCimbing;
import solveur.hillclimbing.Voisinage;

public class HillClimbing_test {
    private SacADos  sac ;
    private List<Objet> objets;
    @BeforeEach
    public void initSac(){
        objets = new ArrayList<>();
        objets.add(new Objet("1", 13, new int[]{6}));
        objets.add(new Objet("2", 55, new int[]{11}));
        objets.add(new Objet("3", 8, new int[]{7}));

        int[] budgets = {18};
        sac = new SacADos(1, budgets, objets);
    }

    @Test
    public void hillClimb_test(){
        Voisinage voisinage = new Voisinage(1);
        HillCimbing hc = new HillCimbing(voisinage, true, false, 100, 1, 2);
        List<Objet> solution_initiale = new ArrayList<>() ;
        List<Objet> solution_optimale = hc.hillClimb(sac,solution_initiale);
        int util_initiale = sac.utilite(solution_initiale);
        int util_optimale = sac.utilite(solution_optimale);
        assertTrue(util_optimale>= util_initiale);
    }

    @Test
    public void nouveaux_voisins_test(){
        Voisinage voisinage = new Voisinage(1);
        List<Objet> solution = new ArrayList<>();
        solution.add(objets.get(0));
        List<List<Objet>> voisins= voisinage.nouveaux_voisins(solution, sac);
        for (List<Objet> voisin : voisins) {
            assertTrue(sac.respecte_budget(voisin));
        }
        assertTrue(voisins.size() > 0);
    }
}

