package solveur.glouton;

import java.util.Comparator;
import java.util.List;

import sacADos.Objet;
import sacADos.SacADos;

public final class ComparateurObjets {

    // Comparateur selon utilité / somme des coûts
	public static final Comparator<Objet> comparateur_somme =
		    (obj1, obj2) -> {
		        int comp = Double.compare(
		            (double) obj2.getUtilite() / obj2.getSommeCouts(),
		            (double) obj1.getUtilite() / obj1.getSommeCouts()
		        );
		        if (comp != 0) 
		        	return comp;
		        // en cas d'égalité, faire en fonction de l'utilité
		        return Integer.compare(obj2.getUtilite(), obj1.getUtilite());
		    };

		    
    // Comparateur selon utilité / coût maximum
    public static final Comparator<Objet> comparateur_max =
		    (obj1, obj2) -> {
		        int comp = Double.compare(
		            (double) obj2.getUtilite() / obj2.getMaxCouts(),
		            (double) obj1.getUtilite() / obj1.getMaxCouts()
		        );
		        if (comp != 0) 
		        	return comp;
		        // en cas d'égalité, faire en fonction de l'utilité
		        return Integer.compare(obj2.getUtilite(), obj1.getUtilite());
    		};
		    
    		
	// Comparateur selon utilité / coût maximum parmi les coûts ayant les dimensions avec le plus gros dépassement de budget
	public static final Comparator<Objet> comparateur_max_depasse(SacADos sac) {			
			return (obj1, obj2) -> {
				  List<Integer> liste_dimensions = sac.getListeDimMaxDepass();
				  
				  int comp = Double.compare(
				      (double) obj2.getUtilite() / obj2.getMaxCoutsRes(liste_dimensions),
				      (double) obj1.getUtilite() / obj1.getMaxCoutsRes(liste_dimensions)
				  );
				  if (comp != 0) 
				      return comp;
				  // en cas d'égalité, faire en fonction de l'utilité
				  return Integer.compare(obj2.getUtilite(), obj1.getUtilite());
			};
	}
	
}
