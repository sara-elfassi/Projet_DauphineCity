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
import solveur.glouton.CompSomme;
import solveur.glouton.GloutonAjoutSolver;

class GloutonAjoutSolverTest {
	private Comparator<Objet> comp;
    private SacADos  sac ;
    private List<Objet> objets = new ArrayList<>();
    
    private Objet kiwi = new Objet("un kiwi", 4, new int[]{3, 3});
    private Objet abricot = new Objet("un abricot", 4, new int[]{4, 1});
    private Objet mangoustan = new Objet("un mangoustan", 3, new int[]{1, 1});
	
	@BeforeEach
    public void initSac(){
		objets.clear();
        objets.add(kiwi);
        objets.add(abricot);
        objets.add(mangoustan);
        
        sac = new SacADos(2, new int[]{6, 4}, objets);
    }
	

	@Test
	void test_solution_compSomme() {
		comp = new CompSomme();
        GloutonAjoutSolver solver = new GloutonAjoutSolver(comp, objets, sac);        
        List<Objet> S = solver.objets_a_mettre();
        
        List<Objet> res = new ArrayList<>();
        res.add(mangoustan);
        res.add(abricot);
        
        assertEquals(res, S);
	}
	
	
	@Test
	void test_solution_compMax() {
		comp = new CompMax();
        GloutonAjoutSolver solver = new GloutonAjoutSolver(comp, objets, sac);        
        List<Objet> S = solver.objets_a_mettre();
        
        List<Objet> res = new ArrayList<>();
        res.add(mangoustan);
        res.add(kiwi);
        
        assertEquals(res, S);
	}

}
