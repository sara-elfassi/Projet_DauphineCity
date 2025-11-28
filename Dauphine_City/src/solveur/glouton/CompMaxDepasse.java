package solveur.glouton;

import java.util.List;

import sacADos.Objet;
import sacADos.SacADos;

public class CompMaxDepasse extends CompUtilite{
	private final SacADos sac_a_dos;
	
	public CompMaxDepasse(SacADos sac_a_dos) {
		this.sac_a_dos = sac_a_dos;
	}
	
	@Override
	public int compare(Objet obj1, Objet obj2) {
		List<Integer> liste_dimensions = sac_a_dos.getListeDimMaxDepass();
		int max_depasse1 = obj1.getUtilite() / obj1.getMaxCoutsRes(liste_dimensions);
		int max_depasse2 = obj2.getUtilite() / obj2.getMaxCoutsRes(liste_dimensions);
		
		return (max_depasse1 < max_depasse2) ? -1:
		((max_depasse1 == max_depasse2) ? super.compare(obj1, obj2) : 1);
	}
	
	@Override
	public String toString() {
		return "comparateur avec maximum du plus gros dépassement";
	}
}
