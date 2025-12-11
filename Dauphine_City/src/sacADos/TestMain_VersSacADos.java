
package sacADos;

import java.math.BigDecimal;
import java.util.*;
import equipe.Projet;
import equipe.Secteur;

public class TestMain_VersSacADos {

    public static void main(String[] args) {

        List<Projet> projets = new ArrayList<>();

        projets.add(new Projet("Stade Municipal", "Construction d'un stade", Secteur.SPORT));
        projets.add(new Projet("Nouvel Hôpital", "Extension du centre hospitalier", Secteur.SANTE));
        projets.add(new Projet("Ecole Primaire", "Rénover l'école primaire", Secteur.EDUCATION));
        projets.add(new Projet("Festival Culturel", "Organisation annuelle", Secteur.CULTURE));
        projets.add(new Projet("Zone Industrielle", "Créer une zone d'attractivité économique", Secteur.ATTRACTIVITE_ECONOMIQUE));

        // Attribution des bénéfices et coûts 
        projets.get(0).setBenefice(new BigDecimal(100));
        projets.get(0).setCoutEconomique(new BigDecimal(50));
        projets.get(0).setCoutSocial(new BigDecimal(10));
        projets.get(0).setCoutEnvironnemental(new BigDecimal(5));

        projets.get(1).setBenefice(new BigDecimal(200));
        projets.get(1).setCoutEconomique(new BigDecimal(150));
        projets.get(1).setCoutSocial(new BigDecimal(20));
        projets.get(1).setCoutEnvironnemental(new BigDecimal(10));

        projets.get(2).setBenefice(new BigDecimal(80));
        projets.get(2).setCoutEconomique(new BigDecimal(40));
        projets.get(2).setCoutSocial(new BigDecimal(5));
        projets.get(2).setCoutEnvironnemental(new BigDecimal(3));

        projets.get(3).setBenefice(new BigDecimal(120));
        projets.get(3).setCoutEconomique(new BigDecimal(30));
        projets.get(3).setCoutSocial(new BigDecimal(15));
        projets.get(3).setCoutEnvironnemental(new BigDecimal(8));

        projets.get(4).setBenefice(new BigDecimal(180));
        projets.get(4).setCoutEconomique(new BigDecimal(100));
        projets.get(4).setCoutSocial(new BigDecimal(10));
        projets.get(4).setCoutEnvironnemental(new BigDecimal(5));

        //Test avec 3 budgets : eco, social, environnemental 
        int budgetEco = 200;
        int budgetSoc = 30;
        int budgetEnv = 10;

        SacADos sac3Budgets = VersSacADos.depuisProjets_3Budgets(projets, budgetEco, budgetSoc, budgetEnv);
        sac3Budgets.afficherContenu(sac3Budgets.solution_initiale_aleatoire());

        //Test avec 5 budgets par secteur 
        Map<Secteur, Integer> budgetsParSecteur = new HashMap<>();
        budgetsParSecteur.put(Secteur.SPORT, 60);
        budgetsParSecteur.put(Secteur.SANTE, 150);
        budgetsParSecteur.put(Secteur.EDUCATION, 50);
        budgetsParSecteur.put(Secteur.CULTURE, 40);
        budgetsParSecteur.put(Secteur.ATTRACTIVITE_ECONOMIQUE, 120);

        SacADos sac5Secteurs = VersSacADos.depuisProjets_Secteurs(projets, budgetsParSecteur);
        sac5Secteurs.afficherContenu(sac5Secteurs.solution_initiale_aleatoire());
    }
}
