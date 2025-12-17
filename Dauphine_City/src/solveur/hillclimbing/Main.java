package solveur.hillclimbing;


import sacADos.SacADos;
import sacADos.Objet;

import java.util.ArrayList;
import java.util.List;

public class Main{

    public static void main(String[] args ){

     
        List<Objet> objets = new ArrayList<>();
        objets.add(new Objet("Projet Sport",30, new int[]{16, 6, 9}));
        objets.add(new Objet("Projet Santé",60, new int[]{10, 3, 18}));
        objets.add(new Objet("Projet Éducation",80, new int[]{20, 12, 50}));

        int[] budgets = {80, 40, 60};

        SacADos sac = new SacADos(3, budgets, objets);

        Voisinage voisinage = new Voisinage(2);  

     
        boolean plateau= true;              
        boolean voisins_aleatoires = true;    
        int nb_iterations = 100;              
        int nb_redemarrage = 5;               
        int nb_voisins_alea = 10;           

      
        HillCimbing hc = new HillCimbing(voisinage, plateau, voisins_aleatoires, nb_iterations, nb_redemarrage, nb_voisins_alea);

      
        List<Objet> meilleure_solution = hc.resoudre_hill(sac);

      
        System.out.println(" Meilleure solution trouvée :");
        sac.afficherContenu(meilleure_solution );
        System.out.println(" Utilité totale: " + sac.utilite(meilleure_solution));
    }
}
