package solveur.glouton;

import java.util.Comparator;

import sacADos.Objet;

public abstract class CompUtilite implements Comparator<Objet>{
	
	@Override
	public int compare(Objet obj1, Objet obj2) {
		int utilite1 = obj1.getUtilite();
		int utilite2 = obj2.getUtilite();
		return (utilite1 < utilite2) ? -1:
		((utilite1 == utilite2) ? 0 : 1);
		}
}