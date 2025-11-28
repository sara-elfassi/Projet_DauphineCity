package solveur.glouton;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import sacADos.Objet;
import sacADos.SacADos;

public class Main {
	
	private static void solve_ajout(
            String comp_nom,
            List<Objet> objets,
            SacADos sac) {

        System.out.println("Résolution par méthode à ajout avec le critère d'ordre " + comp_nom);
        
        Comparator<Objet> comp;

        if ("somme".equals(comp_nom)) {
            comp = ComparateurObjets.comparateur_somme;
        } else if ("max".equals(comp_nom)) {
            comp = ComparateurObjets.comparateur_max;
        } else {
            throw new IllegalArgumentException("Comparateur inconnu : " + comp_nom);
        }
        
        GloutonAjoutSolver solver = new GloutonAjoutSolver(comp, objets, sac);        
        List<Objet> resultat = solver.objets_a_mettre();

        sac.afficherContenu(resultat);
        System.out.println();
    }
	
	
	public static void main(String[] args) {
		
		List<Objet> objets = new ArrayList<>();
        objets.add(new Objet("du dentifrice", 8, new int[]{1, 2}));
        objets.add(new Objet("une flûte", 4, new int[]{2, 4}));
        objets.add(new Objet("un arrosoir", 2, new int[]{1, 1}));
        objets.add(new Objet("un tire-bouchon", 1, new int[]{2, 2}));
        
        SacADos sac = new SacADos(2, new int[]{4, 5}, null);
        
        solve_ajout("somme", objets, sac);
        solve_ajout("max", objets, sac);
	}

}
