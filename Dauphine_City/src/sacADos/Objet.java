package sacADos;

import java.util.List;

public class Objet {
	private final String nom;
	private final int utilite;
	private final int[] couts;
	
	public Objet(String nom, int utilite, int[] couts){
		this.nom = nom;
		this.utilite = utilite;
		this.couts = couts;
	}
	
	@Override
	public String toString() {
	    return nom + " (utilité=" + utilite + ", couts=" + java.util.Arrays.toString(couts) + ")";
	}
	
	public String getNom() {
		return nom;
	}
	
	public int getUtilite() {
		return utilite;
	}
	
	public int[] getCouts() {
		return couts;
	}
	
	public int getSommeCouts() {
		int somme = 0;
		for (int cout : couts) {
			somme += cout;
		}
		return somme;
	}
	
	public int getMaxCouts() {
		int max = 0;
		for (int cout : couts) {
			if (cout > max)
				max = cout;
		}
		return max;
	}
	
	
	public int getMaxCoutsRes(List<Integer> liste_dimensions) {
		int max = 0;
		for (int l : liste_dimensions) {
			if (couts[l] > max)
				max = couts[l];
		}
		return max;
	}

}