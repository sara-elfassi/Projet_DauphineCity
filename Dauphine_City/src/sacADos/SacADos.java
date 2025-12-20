package sacADos;

import java.util.ArrayList;
import java.util.List;

public class SacADos {
	private final int dimension;
	private final int[] budgets;
	private List<Objet> objets;
	
    /** Construit un sac à dos avec une dimension, un tableau de coûts et une liste d'objets.
     * @param dimension - la dimension du sac
     * @param budgets - un tableau contenant les budgets du sac pour chaque dimension
     * @param objets - une liste contenant des objets
     */
	public SacADos(int dimension, int[] budgets, List<Objet> objets){
		this.dimension = dimension;
		this.budgets = budgets;
		this.objets = objets;
	}
	
	
	/** Affiche la liste des objets que l'on va mettre dans le sac
	* @param objets_choisis - la liste des objets sélectionnés
	*/
	public void afficherContenu(List<Objet> objets_choisis) {
	    if (objets_choisis.isEmpty()) {
	        System.out.println("Le sac à dos est vide.");
	    } else {
	        System.out.println("Le sac à dos contient :");
	        for (Objet objet : objets_choisis) {
	            System.out.println("- " + objet);
	        }
	    }
	}
	
	
	/** Renvoie la liste des dimensions pour lesquelles
	* il y a le plus gros dépassement de budget
	* @return liste_dimensions - la liste des dimensions avec le plus gros dépassement
	*/
	public List<Integer> getListeDimMaxDepass() {
		List<Integer> liste_dimensions = new ArrayList<>();
		int max_depass = 0;
		for (int l = 0; l < dimension; l++) {
			int somme = 0;
			for (Objet objet : objets) {
				somme += objet.getCouts()[l];
			}
			int depassement = somme - budgets[l];
			
			if (depassement > max_depass) {
				max_depass = depassement;
				liste_dimensions.clear();
				liste_dimensions.add(l);
			}
			else if (depassement == max_depass) {
				liste_dimensions.add(l);
			}
		}
		return liste_dimensions;
	}
	
	
	/** Renvoie true si les coûts des objets de la liste ne dépassent pas les budgets du sac
	* @param liste_objets - une liste d'objets
	* @return true si aucun budget n'a été dépassé
	*/
	public boolean respecte_budget(List<Objet> liste_objets) {
		for (int l = 0; l < dimension; l++) {
			int somme = 0;
			for (Objet objet : liste_objets) {
				somme += objet.getCouts()[l];
			}
			if (somme > budgets[l])
				return false;
		}
		return true;
	}
	
	
	/** Renvoie la dimension du sac
	* @return dimension - la dimension du sac
	*/
	public int getDimension() {
		return dimension;
	}
	
	
	/** Renvoie les budgets du sac
	* @return budgets - un tableau des budgets du sac
	*/
	public int[] getBudgets() {
		return budgets;
	}
	
	
	/** Renvoie les objets du sac
	* @return objets - une liste contenant les objets du sac
	*/
	public List<Objet> getObjets() {
		return objets;
	}
	
	
	/** Renvoie la somme des utilités d'une liste d'objets
	* @param solution - une liste d'objets
	* @return total - la somme des utilités de la liste d'objets
	*/
	public int utilite(List<Objet> solution) {
	    int total = 0;
	    for (Objet o : solution) {
	        total += o.getUtilite();
	    }
	    return total;
	}

}