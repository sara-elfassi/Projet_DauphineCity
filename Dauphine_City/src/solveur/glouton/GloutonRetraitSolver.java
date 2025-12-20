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
	
    
    /** Construit un solveur glouton par retrait avec un comparateur pour le retrait, 
     * un autre pour l'ajout, une liste d'objets et un sac à dos.
     * 
     * @param comparateur_retrait - le comparateur utilisé pour classer les objets lors du retrait
     * @param comparateur_ajout - le comparateur utilisé pour classer les objets lors de l'ajout
     * @param objets - une liste d'objets
     * @param sac_a_dos - le sac à dos dans lequel on veut mettre les objets
     * 
     * @throws IllegalArgumentException si le comparateur_retrait fourni n’est pas un
     * CompSomme, un CompMax ou un CompMaxDepasse
     * @throws IllegalArgumentException si le comparateur_ajout fourni n’est pas un
     * CompSomme ou un CompMax
     */
	public GloutonRetraitSolver(
			Comparator<Objet> comparateur_retrait,
            Comparator<Objet> comparateur_ajout,
			List<Objet> objets,
			SacADos sac_a_dos) {
		
        if (!(comparateur_retrait instanceof CompSomme
            || comparateur_retrait instanceof CompMax
            || comparateur_retrait instanceof CompMaxDepasse)) {
              throw new IllegalArgumentException("Comparateur de retrait non autorisé");
        }
        
        if (!(comparateur_ajout instanceof CompSomme
            || comparateur_ajout instanceof CompMax)) {
              throw new IllegalArgumentException("Comparateur d'ajout non autorisé");
          }

        this.comparateur_retrait = comparateur_retrait;
        this.comparateur_ajout = comparateur_ajout;
		this.objets = objets;
		this.sac_a_dos = sac_a_dos;
		}

	
    /** Renvoie les objets à mettre dans le sac
     * @return S - la liste d'objets à mettre dans le sac
     */
	public List<Objet> objets_a_mettre() {
		List<Objet> S = new ArrayList<>(objets);
		
		// trier les objets du moins au plus intéressant
		List<Objet> objets_classes = new ArrayList<>(objets);
		objets_classes.sort(comparateur_retrait.reversed());
		
		for (Objet objet : objets_classes) {
			if (!sac_a_dos.respecte_budget(S)) {
				S.remove(objet);
			}
			else {
				// la liste des objets que l'on peut utiliser pour la phase d'ajout
				List<Objet> objets_dispos = new ArrayList<>(objets);
				objets_dispos.removeAll(S);
				
				// la place restante dans les budgets
				int[] nouv_budgets = sac_a_dos.getBudgets().clone();
				for (int d = 0; d < sac_a_dos.getDimension(); d++) {
					for (Objet obj : S) {
						nouv_budgets[d] -= obj.getCouts()[d];
					}
				}
				
				// méthode gloutonne par ajout sur un sac à dos avec les budgets restants
				SacADos petit_sac = new SacADos(sac_a_dos.getDimension(), nouv_budgets, new ArrayList<>());
				GloutonAjoutSolver glouton_ajout = new GloutonAjoutSolver(
						comparateur_ajout, objets_dispos, petit_sac);
				
				List<Objet> objets_rajoutes = glouton_ajout.objets_a_mettre();
				S.addAll(objets_rajoutes);
				break;
			}
		}
		return S;
	}
	
	
	/** Affiche le solveur utilisé, les comparateurs utilisés et 
	 * les objets qu'on a choisis de mettre dans le sac
	*/
	public void afficher_solution() {
	    System.out.println("Résolution par méthode à retrait, en utilisant un " + comparateur_retrait
	    		+ " pour le retrait, et un " + comparateur_ajout + " pour l'ajout :");
	    sac_a_dos.afficherContenu(objets_a_mettre());
	    System.out.println();
	}
			
}
