package solveur.glouton;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import sacADos.Objet;
import sacADos.SacADos;

public class GloutonRetraitSolver {
    private final Comparator<Objet> comparateur_retrait;
    private final Comparator<Objet> comparateur_ajout;
    private final List<Objet> objets;
    private final SacADos sac_a_dos;
	
	public GloutonRetraitSolver(
			Comparator<Objet> comparateur_retrait,
            Comparator<Objet> comparateur_ajout,
			List<Objet> objets,
			SacADos sac_a_dos) {
		
        if (!(comparateur_retrait instanceof CompSomme
            || comparateur_retrait instanceof CompMax
            || comparateur_retrait instanceof CompMaxDepasse)) {
              throw new IllegalArgumentException("Comparateur de reatrait non autorisé");
        }
        
        if (!(comparateur_ajout instanceof CompSomme
            || comparateur_ajout instanceof CompMax
            || comparateur_ajout instanceof CompMaxDepasse)) {
              throw new IllegalArgumentException("Comparateur d'ajout non autorisé");
          }

        this.comparateur_retrait = comparateur_retrait;
        this.comparateur_ajout = comparateur_ajout;
		this.objets = objets;
		this.sac_a_dos = sac_a_dos;
		}

	
	public List<Objet> objets_a_mettre() {
		List<Objet> S = new ArrayList<>(objets);
		
		List<Objet> objets_classes = new ArrayList<>(objets);
		objets_classes.sort(comparateur_retrait.reversed());
		
		for (Objet objet : objets_classes) {
			if (!sac_a_dos.respecte_budget(S)) {
				S.remove(objet);
			}
			else {
				List<Objet> objets_dispos = new ArrayList<>(objets);
				objets_dispos.removeAll(S);
				
				GloutonAjoutSolver glouton_ajout = new GloutonAjoutSolver(
						comparateur_ajout, objets_dispos, sac_a_dos);
				
				List<Objet> objets_rajoutes = glouton_ajout.objets_a_mettre();
				S.addAll(objets_rajoutes);
				break;
			}
		}
		return S;
	}
	
	
	public void afficher_solution() {
	    System.out.println("Résolution par méthode à retrait, en utilisant un " + comparateur_retrait
	    		+ " pour le retrait, et un " + comparateur_ajout + " pour l'ajout :");
	    sac_a_dos.afficherContenu(objets_a_mettre());
	    System.out.println();
	}
			
}
