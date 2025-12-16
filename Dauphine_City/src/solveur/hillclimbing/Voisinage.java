
package solveur.hillclimbing;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import sacADos.Objet;
import sacADos.SacADos;

/**
 * Le voisinage pour le problème du sac à dos multidimensionnel pour l'algorithme de Hill Climbing.
 * Deux solutions admissibles sont voisines si l'on peut passer de l'une à l'autre en retirant et/ou ajoutant au plus t objets.
 * t = 1 : ajout ou retrait d'un seul objet
 * t = 2 : échange d'un objet (retrait + ajout)
 */
public class Voisinage {

    /**
     * Nombre maximum de modifications autorisées pour générer un voisin.
     * La valeur est fixée à 1 ou 2.
     */
    private final int t;

    
    /**
     * Construit un voisinage pour le Hill Climbing
     *
     * @param t taille du voisinage (1 ou 2)
     * @throws IllegalArgumentException si t n'est pas égal à 1 ou 2
     */
    public Voisinage(int t) {
        if (t <1 || t >2) {
            throw new  IllegalArgumentException("t doit être égal à 1 ou 2");
        }
        this.t = t;
    }

    /**
     * Retourne la taille du voisinage utilisée
     *
     * @return la valeur de t
     */
    public int getT() {
        return t;
    }

    /**
     * Génère l'ensemble des solutions voisines admissibles pour une solution 
     * Un voisin est généré en modifiant la solution courante et respecte les contraintes de budget du sac à dos.
     * @param solution la solution courante (liste d'objets sélectionnés)
     * @param sac le sac à dos contenant les objets et les budgets
     * @return une liste de solutions voisines possibles
     */
    public List<List<Objet>> nouveaux_voisins(List<Objet> solution, SacADos sac) {

        Set<List<Objet>> voisins =new HashSet<>();
        List<Objet> objets  = sac.getObjets();
        Set<Objet> solution_set =  new HashSet<>(solution);

        /* t = 1 */
        for (Objet obj : objets) {

            // ajout d'un objet
            if (!solution_set.contains(obj)) {
                List<Objet> voisin= new ArrayList<>(solution);
                voisin.add(obj);
                if (sac.respecte_budget( voisin) ) {
                    voisins.add(voisin );
                }
            }

            // retrait d'un objet
            if (solution_set.contains(obj)) {
                List<Objet> voisin= new ArrayList<>(solution) ;
                voisin.remove (obj);
                voisins.add (voisin);
            }
        }

        /* t = 2 */
        if (t == 2) {
            for (Objet retire: solution) {
                for (Objet ajout: objets) {
                    if (!solution_set.contains(ajout)) {
                        List<Objet> voisin = new ArrayList<>(solution);
                        voisin.remove (retire);
                        voisin.add (ajout);
                        if (sac.respecte_budget(voisin)) {
                            voisins.add(voisin);
                        }
                    }
                }
            }
        }

        return new  ArrayList<>(voisins);
    }
}
