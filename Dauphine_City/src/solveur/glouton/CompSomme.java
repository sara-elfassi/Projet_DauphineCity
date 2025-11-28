package solveur.glouton;

import sacADos.Objet;

public class CompSomme extends CompUtilite{
	
	@Override
	public int compare(Objet obj1, Objet obj2) {
		int somme1 = obj1.getUtilite() / obj1.getSommeCouts();
		int somme2 = obj2.getUtilite() / obj2.getSommeCouts();
		return (somme1 < somme2) ? -1:
		((somme1 == somme2) ? super.compare(obj1, obj2) : 1);
		}
	
	@Override
	public String toString() {
		return "comparateur avec somme";
	}
}