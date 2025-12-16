package generer_instances;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import equipe.Projet;
import sacADos.Objet;
import sacADos.SacADos;


/**
 *Générer des instances de SacADos partir de projets municipaux ou de fichiers d'instances.
 */


public class VersSacADos {

    /**
     * Génère un SacADos à partir d'une liste de projets municipaux avec les 3 types de coûts : économique, social et environnemental.
     *
     * @param projets liste de projets
     * @param budgets tableau des budgets pour les trois coûts
     * @return instance de SacADos
     */
    public static SacADos generer_sac_3_couts(List<Projet>  projets,  int[] budgets) {
        List<Objet> objets = new   ArrayList<>();
        for (Projet p : projets) {
            if (!p.estComplet()) continue; // ignorer les projets incomplets
            int utilite = p.getBenefice().intValue();
            int[] couts =new int[]{
                p.getCoutEconomique().intValue(),
                p.getCoutSocial().intValue(),
                p.getCoutEnvironnemental().intValue()
            };
            objets.add(new Objet(p.getTitre(), utilite, couts));
        }
        return new SacADos(3, budgets, objets);
    }

    
    
    
    
    /**
     * Génère un SacADos à partir d'une liste de projets par secteur, en ne considérant que le coût économique.
     *
     * @param projets liste de projets
     * @param budgets_secteurs  tableau des budgets par secteur
     * @return instance de SacADos
     */
    public static SacADos generer_par_secteur(List<Projet> projets, int[] budgets_secteurs) {
        List<Objet> objets  = new ArrayList<>();
        for (Projet p : projets) {
            if (!p.estComplet()) continue;
            int utilite =  p.getBenefice().intValue();
            int[] couts = new int [budgets_secteurs.length];
            int secteur_index = p.getSecteur().ordinal();
            if (secteur_index >=  0 && secteur_index < budgets_secteurs.length) {
                couts[secteur_index] = p.getCoutEconomique().intValue();
            }
            objets.add(new Objet(p.getTitre(), utilite, couts));
        }
        return new SacADos(budgets_secteurs.length, budgets_secteurs, objets);
    }

    
    
    
    
    /**
     * Génère un SacADos à partir d'un fichier d'instance 
     *
     * @param chemin chemin vers le fichier
     * @return instance de SacADos
     * @throws IOException si le fichier n'est pas trouvé ou mal formé
     */
    public static SacADos generer_depuis_fichier(String chemin) throws  IOException  {
        BufferedReader br = new BufferedReader(new FileReader(chemin) );
        String[] ligne1 =br.readLine().trim().split("\\s+") ;
        int n =Integer.parseInt(ligne1[0]) ; // nombre d'objets
        int k = Integer.parseInt(ligne1[1] ); // nombre de budgets
        br.readLine(); // valeur optimale ignorée

        // Lecture des utilités
        String[] utilitesStr =br.readLine().trim().split("\\s+" );
        int[] utilites =new int [n];
        for (int i = 0; i < n; i++) {
        	if (i < utilitesStr.length) {
        	   utilites[i] = Integer.parseInt(utilitesStr[i]);
        	} else {
        	   utilites[i] = 0; // valeurs manquantes → 0
        	}
        	}

        // Lecture de la matrice de contraintes
        int[][] contraintes = new int[k][n];
        for (int i = 0; i < k; i++) {
        	String[] ligne = br.readLine().trim().split("\\s+");
        	for (int j = 0; j < n; j++) {
        	   if (j < ligne.length) {
        	       contraintes[i][j] = Integer.parseInt(ligne[j]);
        	   } else {
        	       contraintes[i][j] = 0; // valeurs manquantes → 0
        	   }
        	}
        	}

        // Lecture des budgets
        String[] budgetsStr = br.readLine().trim().split("\\s+");
        int[] budgets = new int[k];
        for (int i = 0; i < k;  i++) {
            budgets[i] = Integer.parseInt(budgetsStr[i ]);
        }

        br.close();

        // Création des objets
        List<Objet> objets = new ArrayList<>();
        for (int j = 0; j < n; j++) {
            int[] couts = new int[k];
            for (int i = 0; i < k; i++)  {
                couts[i] = contraintes[i][j] ;
            }
            objets.add(new Objet("Objet_" + (j + 1), utilites[j], couts) ) ;
        }

        return new SacADos(k, budgets,   objets);
    }
}



