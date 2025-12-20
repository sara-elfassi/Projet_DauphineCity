package solveur.glouton;

import java.util.Comparator;

import sacADos.Objet;

public abstract class CompUtilite implements Comparator<Objet>{
	
	/** Compare deux objets en fonction de leur utilité
	* @param obj1 - un premier objet
	* @param obj2 - un deuxième objet
	* @return  1 si obj1 est moins intéressant que obj2,
	* 		  -1 si obj1 est plus intéressant que obj2,
	* 		   0 si obj1 est aussi intéressant que obj2
	*/
	@Override
	public int compare(Objet obj1, Objet obj2) {
		int utilite1 = obj1.getUtilite();
		int utilite2 = obj2.getUtilite();
		return (utilite1 < utilite2) ? 1:
		((utilite1 == utilite2) ? 0 : -1);
		}
}