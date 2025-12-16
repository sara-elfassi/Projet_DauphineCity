package test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sacADos.Objet;
import sacADos.SacADos;
import solveur.hillclimbing.HillCimbing;
import solveur.hillclimbing.Voisinage;

class HillClimbingTest {

    private SacADos sac;
    private List<Objet> objets;

    @BeforeEach
    void setUp() {

        objets = new ArrayList<>();
        objets.add(new Objet("A", 10, new int[]{5}));
        objets.add(new Objet("B", 20, new int[]{10}));
        objets.add(new Objet("C", 15, new int[]{7}));
        int[] budgets = {15};
        sac = new SacADos(1, budgets, objets);
    }

    @Test
    void testHillClimbAmelioration() {
        Voisinage voisinage = new Voisinage(1);
        HillCimbing hc = new HillCimbing(voisinage, true, false,  100, 1, 2);

        List<Objet> solutionInitiale =new ArrayList<>();
        List<Objet>solutionOptimale = hc.hillClimb(sac, solutionInitiale);

        int utilInitiale = sac.utilite(solutionInitiale);
        int utilOptimale = sac.utilite(solutionOptimale);
        assertTrue(utilOptimale>= utilInitiale ,  "La solution doit avoir une utilité au moins égale à l'initiale");
    }

    @Test
    void testNouveauxVoisins() {
        Voisinage voisinage = new Voisinage(1);
        List<Objet> solution =new ArrayList<>();
        solution.add(objets.get(0)); 

        List<List<Objet>> voisins = voisinage.nouveaux_voisins(solution, sac);

       
        for (List<Objet> voisin : voisins)  {
            assertTrue(sac.respecte_budget(voisin), "Chaque voisin doit respecter le budget");
        }
        assertFalse(voisins.isEmpty(), "Il  doit y avoir au moins un voisin généré");
    }
}
