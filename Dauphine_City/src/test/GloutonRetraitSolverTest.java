package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sacADos.Objet;
import sacADos.SacADos;
import solveur.glouton.CompMax;
import solveur.glouton.CompMaxDepasse;
import solveur.glouton.CompSomme;
import solveur.glouton.GloutonRetraitSolver;

class GloutonRetraitSolverTest {
	
	private Comparator<Objet> comp_retrait;
	private Comparator<Objet> comp_ajout;
    private SacADos  sac ;
    private List<Objet> objets = new ArrayList<>();
    
    private Objet kiwi = new Objet("un kiwi", 9, new int[]{3, 3});
    private Objet abricot = new Objet("un abricot", 8, new int[]{4, 0});
    private Objet mangoustan = new Objet("un mangoustan", 1, new int[]{1, 1});
	
	@BeforeEach
    public void initSac(){
		objets.clear();
        objets.add(kiwi);
        objets.add(abricot);
        objets.add(mangoustan);
        
        sac = new SacADos(2, new int[]{6, 4}, objets);
    }
	
	
	@Test
	void test_solution_compSomme_compMax() {
		comp_retrait = new CompSomme();
		comp_ajout = new CompMax();
        GloutonRetraitSolver solver = new GloutonRetraitSolver(comp_retrait, comp_ajout, objets, sac);        
        List<Objet> S = solver.objets_a_mettre();

        
        List<Objet> res = new ArrayList<>();
        res.add(abricot);
        res.add(mangoustan);
        
        assertEquals(res, S);
	}
	
	
	@Test
	void test_solution_compMaxDepasse_compMax() {
		comp_retrait = new CompMaxDepasse(sac);
		comp_ajout = new CompMax();
        GloutonRetraitSolver solver = new GloutonRetraitSolver(comp_retrait, comp_ajout, objets, sac);        
        List<Objet> S = solver.objets_a_mettre();
        
        List<Objet> res = new ArrayList<>();
        res.add(kiwi);
        res.add(mangoustan);
        
        assertEquals(res, S);
	}

}
