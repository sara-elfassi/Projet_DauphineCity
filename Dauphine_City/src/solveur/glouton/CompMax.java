package solveur.glouton;

import sacADos.Objet;

public class CompMax extends CompUtilite{
	
	/** Compare deux objets en fonction du critère : 
	 * utilité / le maximum des coûts
	* @param obj1 - un premier objet
	* @param obj2 - un deuxième objet
	* @return  1 si obj1 est moins intéressant que obj2,
	* 		  -1 si obj1 est plus intéressant que obj2
	*/
	@Override
	public int compare(Objet obj1, Objet obj2) {
		double max1 = (double) obj1.getUtilite() / obj1.getMaxCouts();
		double max2 = (double) obj2.getUtilite() / obj2.getMaxCouts();
		return (max1 < max2) ? 1:
		((max1 == max2) ? super.compare(obj1, obj2) : -1);
		}
	
	/** Renvoie le nom du comparateur
	* @return une chaîne précisant le critère d'ordre utilisé
	*/
	@Override
	public String toString() {
		return "comparateur avec maximum";
	}
}