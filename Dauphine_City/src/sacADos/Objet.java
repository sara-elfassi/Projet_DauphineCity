package sacADos;

import java.util.List;

public class Objet {
	private final String nom;
	private final int utilite;
	private final int[] couts;
	
    /** Construit un nouvel objet avec un nom, une utilité et un tableau de coûts.
     * @param nom - le nom de l'objet
     * @param utilite - l'utilité de l'objet
     * @param couts - un tableau contenant les coûts de l'objet pour chaque dimension
     */
	public Objet(String nom, int utilite, int[] couts){
		this.nom = nom;
		this.utilite = utilite;
		this.couts = couts;
	}
	
	/** Renvoie une description de l'objet
	* @return une chaîne décrivant l'objet
	*/
	@Override
	public String toString() {
	    return nom + " (utilité=" + utilite + ", couts=" + java.util.Arrays.toString(couts) + ")";
	}
	
	/** Renvoie le nom de l'objet
	* @return nom - le nom de l'objet
	*/
	public String getNom() {
		return nom;
	}
	
	/** Renvoie l'utilité de l'objet
	* @return utilite - l'utilité de l'objet
	*/
	public int getUtilite() {
		return utilite;
	}
	
	/** Renvoie les coûts de l'objet
	* @return couts - le tableau des coûts de l'objet
	*/
	public int[] getCouts() {
		return couts;
	}
	
	/** Renvoie la somme des coûts de l'objet
	* @return somme - la somme des coûts de l'objet
	*/
	public int getSommeCouts() {
		int somme = 0;
		for (int cout : couts) {
			somme += cout;
		}
		return somme;
	}
	
	/** Renvoie le maximum parmi les coûts de l'objet
	* @return max - le maximum des coûts de l'objet
	*/
	public int getMaxCouts() {
		int max = 0;
		for (int cout : couts) {
			if (cout > max)
				max = cout;
		}
		return max;
	}
	
	/** Renvoie le maximum parmi certains coûts de l'objet
	* @param liste_dimensions - une liste de dimensions
	* @return max - le maximum parmi les coûts correspondant aux dimensions données
	*/
	public int getMaxCoutsRes(List<Integer> liste_dimensions) {
		int max = 0;
		for (int l : liste_dimensions) {
			if (couts[l] > max)
				max = couts[l];
		}
		return max;
	}

}