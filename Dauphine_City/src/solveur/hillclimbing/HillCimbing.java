package solveur.hillclimbing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sacADos.Objet;
import sacADos.SacADos;



/**
 * Implémentation de l'algorithme Hill Climbing pour le problème du sac à dos multidimensionnel
 * L'algorithme explore  le voisinage d'une solution possible pour améliorer l'utilité totale avec les contraintes de budget.
 */
public class HillCimbing {

    /** Voisinage utilisé pour générer les solutions voisines */
    private final Voisinage voisinage;

    /** Permet les déplacements sur plateau */
    private final boolean plateau;

    /** Permet la sélection aléatoire des voisins */
    private final boolean voisins_aleatoires;

    /** Nombre maximal d'itérations par recherche locale */
    private final int nb_iterations;

    /** Nombre de redémarrages aléatoires */
    private final int nb_redemarrage;

    /** Nombre maximal de voisins aléatoire possible */
    private final int nb_voisins_alea_max;

    /**
     * Constructeur du Hill Climbing.
     *
     * @param voisinage voisinage utilisé (t =1 ou t =2)
     * @param plateau autorise ou non les déplacements sur plateau
     * @param voisins_aleatoires permet la sélection aléatoire des voisins
     * @param nb_iterations nombre maximal d'itérations
     * @param nb_redemarrage nombre de redémarrages aléatoires
     * @param nb_voisins_alea_max nombre maximal de voisins aléatoire possible
     */
    public HillCimbing(Voisinage voisinage, boolean plateau,boolean voisins_aleatoires, int nb_iterations, int nb_redemarrage,int nb_voisins_alea_max) {
        this.voisinage = voisinage ;
        this.plateau = plateau ; 
        this.voisins_aleatoires = voisins_aleatoires;
        this.nb_iterations = nb_iterations;
        this.nb_redemarrage = nb_redemarrage;
        this.nb_voisins_alea_max = nb_voisins_alea_max;
    }
    
    
    

    /**
     * Compare une solution voisine à la solution courante.
     *
     * @param voisin solution voisine
     * @param solution solution courante
     * @param sac instance du sac à dos
     * @return true si le voisin améliore la solution (ou égal si plateau autorisé)
     */
    private boolean meilleur_voisin(List<Objet> voisin,List<Objet> solution, SacADos sac) {
        int util_voisin = sac.utilite(voisin);
        int util_solution = sac.utilite(solution);
        return plateau ? util_voisin >= util_solution : util_voisin > util_solution;
    }
    
    
    

    /**
     * Applique Hill Climbing à partir d'une solution initiale possible
     * @param sac instance du sac à dos
     * @param solutionInitiale solution de départ
     * @return solution localement optimale
     */
    public List<Objet> hillClimb(SacADos sac , List<Objet> solutionInitiale) {

        List<Objet> solution = new ArrayList<>(solutionInitiale);
        int i = 0;

        while (i < nb_iterations) {
            i++;
            List<List<Objet>> voisins = voisinage.nouveaux_voisins(solution, sac);
            if (voisins_aleatoires) {
                Collections.shuffle(voisins);
            }

            boolean amelioration = false;
            int limite = voisins_aleatoires? Math.min(nb_voisins_alea_max, voisins.size()): voisins.size();

            for (int k = 0; k < limite; k++) {
                List<Objet> voisin = voisins.get(k);
                if (meilleur_voisin(voisin, solution, sac)) {
                    solution = voisin;
                    amelioration = true;
                    break;
                }
            }

            if (!amelioration) {
                break; // optimum local atteint
            }
        }

        return solution;
    }

    /**
     * Exécute Hill Climbing avec plusieurs redémarrages aléatoires.
     *
     * @param sac instance du sac à dos
     * @return meilleure solution trouvée
     */
    public List<Objet> resoudre_hill(SacADos sac) {

        List<Objet> meilleure_solution = new ArrayList<>();
        int meilleure_utilite = 0;

        for (int i = 0; i < nb_redemarrage; i++) {

            // génère une solution initiale possible
            List<Objet> solution_initiale = new ArrayList<>();
            List<Objet> objets = new ArrayList<>(sac.getObjets());
            Collections.shuffle(objets);

            for (Objet obj : objets) {
            	solution_initiale.add(obj);
                if (!sac.respecte_budget(solution_initiale)) {
                	solution_initiale.remove(obj);
                }
            }
            List<Objet> solution = hillClimb(sac, solution_initiale);
            int utilite = sac.utilite(solution);

            if (utilite > meilleure_utilite) {
            	meilleure_utilite = utilite;
                meilleure_solution = solution;
            }
        }

        return meilleure_solution;
    }
}


