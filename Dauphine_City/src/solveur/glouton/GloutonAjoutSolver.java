package solveur.glouton;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import sacADos.Objet;
import sacADos.SacADos;

public class GloutonAjoutSolver {
	private final Comparator<Objet> comparateur;
	private final List<Objet> objets;
	private final SacADos sac_a_dos;
	
	
    /** Construit un solveur glouton par ajout avec un comparateur, 
     * une liste d'objets et un sac à dos.
     * 
     * @param comparateur - le comparateur utilisé pour classer les objets
     * @param objets - une liste d'objets
     * @param sac_a_dos - le sac à dos dans lequel on veut mettre les objets
     * 
     * @throws IllegalArgumentException si le comparateur fourni n’est pas un
     * CompSomme ou un CompMax
     */
	public GloutonAjoutSolver(
			Comparator<Objet> comparateur,
			List<Objet> objets,
			SacADos sac_a_dos) {
			
		if (!(comparateur instanceof CompSomme
			|| comparateur instanceof CompMax)) {
			throw new IllegalArgumentException("Comparateur non autorisé");
		}
	
		this.comparateur = comparateur;
		this.objets = objets;
		this.sac_a_dos = sac_a_dos;
		}

	
    /** Renvoie les objets à mettre dans le sac
     * @return S - la liste d'objets à mettre dans le sac
     */
	public List<Objet> objets_a_mettre() {
		List<Objet> S = new ArrayList<>();
		
		// trier les objets du plus au moins intéressant
		List<Objet> objets_classes = new ArrayList<>(objets);
		objets_classes.sort(comparateur);
		
		for (Objet objet : objets_classes) {
			S.add(objet);
			if (!sac_a_dos.respecte_budget(S))
				S.remove(objet);
		}
		return S;
	}
	
	
	/** Affiche le solveur utilisé, le comparateur utilisé et 
	 * les objets qu'on a choisis de mettre dans le sac
	*/
	public void afficher_solution() {
	    System.out.println("Résolution par méthode à ajout, en utilisant un " + comparateur + " :");
	    sac_a_dos.afficherContenu(objets_a_mettre());
	    System.out.println();
	}
			
}
