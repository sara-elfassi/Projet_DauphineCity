package solveur.glouton;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import sacADos.Objet;
import sacADos.SacADos;

public class GloutonAjoutSolver {
	private final Comparator<Objet> comparateur;
	private final List<Objet> objets;
	private final SacADos sac_a_dos;
	
	public GloutonAjoutSolver(
			Comparator<Objet> comparateur,
			List<Objet> objets,
			SacADos sac_a_dos)
			
		{if (comparateur != ComparateurObjets.comparateur_max &&
            comparateur != ComparateurObjets.comparateur_somme) {
            throw new IllegalArgumentException(
                "Le comparateur doit être comparateur_max ou comparateur_somme");
			}
	
		this.comparateur = comparateur;
		this.objets = objets;
		this.sac_a_dos = sac_a_dos;
		}

	
	public List<Objet> objets_a_mettre() {
		List<Objet> S = new ArrayList<>();
		
		List<Objet> objets_classes = new ArrayList<>(objets);
		objets_classes.sort(comparateur);
		
		for (Objet objet : objets_classes) {
			S.add(objet);
			if (!sac_a_dos.respecte_budget(S))
				S.remove(objet);
		}
		return S;
	}	
}
