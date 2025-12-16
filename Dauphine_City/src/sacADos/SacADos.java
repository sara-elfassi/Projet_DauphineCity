package sacADos;

import java.util.ArrayList;
import java.util.List;


public class SacADos {
	private final int dimension;
	private final int[] budgets;
	private List<Objet> objets;
	
	public SacADos(int dimension, int[] budgets, List<Objet> objets){
		this.dimension = dimension;
		this.budgets = budgets;
		this.objets = objets;
	}
	
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
	
	public List<Integer>  getListeDimMaxDepass() {
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
	
	
	public int getDimension() {
		return dimension;
	}
	
	public int[] getBudgets() {
		return budgets;
	}
	
	public List<Objet> getObjets() {
		return objets;
	}
	
	public int utilite(List<Objet> solution) {
	    int total = 0;
	    for (Objet o : solution) {
	        total += o.getUtilite();
	    }
	    return total;
	}
	



}