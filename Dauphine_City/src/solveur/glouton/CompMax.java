package solveur.glouton;

import sacADos.Objet;

public class CompMax extends CompUtilite{
	
	@Override
	public int compare(Objet obj1, Objet obj2) {
		double max1 = obj1.getUtilite() / obj1.getMaxCouts();
		double max2 = obj2.getUtilite() / obj2.getMaxCouts();
		return (max1 < max2) ? -1:
		((max1 == max2) ? super.compare(obj1, obj2) : 1);
		}
	
	@Override
	public String toString() {
		return "comparateur avec maximum";
	}
}