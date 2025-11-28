package solveur.glouton;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import sacADos.Objet;
import sacADos.SacADos;

public class Main {
	
	public static void main(String[] args) {
		
		List<Objet> objets = new ArrayList<>();
        objets.add(new Objet("du dentifrice", 8, new int[]{1, 2}));
        objets.add(new Objet("une flûte", 4, new int[]{2, 4}));
        objets.add(new Objet("un arrosoir", 2, new int[]{1, 1}));
        objets.add(new Objet("un tire-bouchon", 1, new int[]{2, 2}));
        
        SacADos sac = new SacADos(2, new int[]{4, 5}, objets);
        
        Comparator<Objet> comp1 = new CompSomme();
        Comparator<Objet> comp2 = new CompMax();
        Comparator<Objet> comp3 = new CompMaxDepasse(sac);
        
        GloutonAjoutSolver solver1 = new GloutonAjoutSolver(comp1, objets, sac);        
        solver1.afficher_solution();
        
        GloutonAjoutSolver solver2 = new GloutonAjoutSolver(comp2, objets, sac);        
        solver2.afficher_solution();
        
        GloutonRetraitSolver solver3 = new GloutonRetraitSolver(comp3, comp1, objets, sac);        
        solver3.afficher_solution();
        
        GloutonRetraitSolver solver4 = new GloutonRetraitSolver(comp3, comp2, objets, sac);        
        solver4.afficher_solution();
        
        GloutonRetraitSolver solver5 = new GloutonRetraitSolver(comp1, comp2, objets, sac);        
        solver5.afficher_solution();
	}

}
