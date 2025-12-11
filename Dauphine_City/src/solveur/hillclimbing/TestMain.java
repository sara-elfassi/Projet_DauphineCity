package solveur.hillclimbing;

import java.util.Arrays;
import java.util.List;

import sacADos.Objet;
import sacADos.SacADos;


public class TestMain {

    public static void main(String[] args) {

   
     
        Objet o1 = new Objet("Obj1", 10, new int[]{4, 3, 2});
        Objet o2 = new Objet("Obj2", 7,  new int[]{2, 5, 3});
        Objet o3 = new Objet("Obj3", 15, new int[]{6, 2, 4});
        Objet o4 = new Objet("Obj4", 4,  new int[]{1, 1, 1});
        Objet o5 = new Objet("Obj5", 12, new int[]{5, 4, 2});

        List<Objet> objets = Arrays.asList(o1, o2, o3, o4, o5);

 
        // Création du Sac à Dos

        int[] budgets = {10, 10, 10};   // 3 dimensions

        SacADos sac = new SacADos(3, budgets, objets);


        // Solution initiale aléatoire

        List<Objet> init = sac.solution_initiale_aleatoire();
        sac.afficherContenu(init);
        System.out.println("Utilité : " + sac.utilite(init));
        System.out.println();

    
        // Hill Climbing V1
  
        System.out.println(" Hill climbing version 1 Strictement Meilleur");

        Voisinage voisinage = new Voisinage(1); 

        HillClimbing_v1_MeilleurVoisin hc1 = new HillClimbing_v1_MeilleurVoisin(voisinage);

        List<Objet> solution_v1 = hc1.appliquerHillClimbing(sac, init);

        sac.afficherContenu(solution_v1);
        System.out.println("Utilité : " + sac.utilite(solution_v1));
        System.out.println();

        
        // Hill Climbing V2 
     
        System.out.println("Hill Climbing version 2 Plateau autorisé");

        HillClimbing_v2_Plateau hc2 = new HillClimbing_v2_Plateau(voisinage);
        List<Objet> solution_v2 = hc2.appliquerHillClimbing(sac, init);

        sac.afficherContenu(solution_v2);
        System.out.println("Utilité : " + sac.utilite(solution_v2));
        System.out.println();

       
        //  Hill Climbing V3
       
        System.out.println("Hill Climbing version 3 Random recommencement");

        HillClimbing_v3_Random hc3 = new HillClimbing_v3_Random(hc2, 10); 

        List<Objet> solution_v3 = hc3.solve(sac);

        sac.afficherContenu(solution_v3);
        System.out.println("Utilité : " + sac.utilite(solution_v3));
        System.out.println();
        
        
        //  Hill Climbing V4
        try {
            System.out.println("Hill Climbing version Voisins Random ");
            HillClimbing_v4_Voisin_Aleatoires hc4 = new HillClimbing_v4_Voisin_Aleatoires(voisinage);
            List<Objet> solution_v4 = hc4.appliquerHillClimbing(sac, init);
            sac.afficherContenu(solution_v4);
            System.out.println("Utilité : " + sac.utilite(solution_v4));
            System.out.println();
        } catch (Exception e) {
            System.out.println("HillClimbing V4 non implémenté ou erreur : " + e.getMessage());
        }
        
      
      

    }
}



