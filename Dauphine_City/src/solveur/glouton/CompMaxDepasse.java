package solveur.glouton;

import java.util.List;

import sacADos.Objet;
import sacADos.SacADos;

public class CompMaxDepasse extends CompUtilite{
	private final SacADos sac_a_dos;
	
    /** Construit un comparateur qui prend en paramètre un sac à dos.
     * @param sac_a_dos - un sac à dos
     */
	public CompMaxDepasse(SacADos sac_a_dos) {
		this.sac_a_dos = sac_a_dos;
	}
	
	
	/** Compare deux objets en fonction du critère : 
	 * utilité / le maximum des coûts correspondant au plus grand dépassement de budget
	* @param obj1 - un premier objet
	* @param obj2 - un deuxième objet
	* @return  1 si obj1 est moins intéressant que obj2,
	* 		  -1 si obj1 est plus intéressant que obj2
	*/
	@Override
	public int compare(Objet obj1, Objet obj2) {
		List<Integer> liste_dimensions = sac_a_dos.getListeDimMaxDepass();
		double max_depasse1 = (double) obj1.getUtilite() / obj1.getMaxCoutsRes(liste_dimensions);
		double max_depasse2 = (double) obj2.getUtilite() / obj2.getMaxCoutsRes(liste_dimensions);
		
		return (max_depasse1 < max_depasse2) ? 1:
		((max_depasse1 == max_depasse2) ? super.compare(obj1, obj2) : -1);
	}
	
	
	/** Renvoie le nom du comparateur
	* @return une chaîne précisant le critère d'ordre utilisé
	*/
	@Override
	public String toString() {
		return "comparateur avec maximum du plus gros dépassement";
	}
}
