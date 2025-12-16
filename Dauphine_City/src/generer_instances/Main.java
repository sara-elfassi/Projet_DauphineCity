package generer_instances;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import equipe.Projet;
import equipe.Secteur;
import sacADos.SacADos;


public class Main {
	  public static void main(String[] args) {
	        // exemple 1 : Sac à dos à partir de projets municipaux
	    	List<Projet> projets = new ArrayList<>();
	    	// Projet 1 : Bibliothèque, secteur EDUCATION 
	    	Projet p1 = new Projet("Bibliothèque", "Création d'une bibliothèque", Secteur.EDUCATION);
	    	p1.setBenefice(new BigDecimal(50));
	    	p1.setCoutEconomique(new BigDecimal(20));
	    	p1.setCoutSocial(new BigDecimal(10));
	    	p1.setCoutEnvironnemental(new BigDecimal(5));
	    	// Projet 2 : Hôpital, secteur SANTE 
	    	Projet p2 = new Projet("Hôpital", "Construction d'un hôpital", Secteur.SANTE);
	    	p2.setBenefice( new BigDecimal(80));
	    	p2.setCoutEconomique (new BigDecimal(40));
	    	p2.setCoutSocial(new BigDecimal(20));
	    	p2.setCoutEnvironnemental(new  BigDecimal(10));
	    	projets.add(p1);
	    	projets.add(p2);
	       
	       
	       
	        // exemple 2 : Sac à dos par secteur
	    	int[] budgets_secteurs = {30, 50, 40, 25, 60}; // 5 secteurs
	    	SacADos sac_secteurs = VersSacADos.generer_par_secteur(projets, budgets_secteurs);
	    	System.out.println("Sac à dos par secteur (coût économique uniquement) :");
	    	sac_secteurs.afficherContenu(sac_secteurs.getObjets());
	    	
	    	// exemple 3 : Sac à dos par fichier
	    	String chemin = "/Users/macbookpro/Downloads/All-MKP-Instances/sac94/pet/pet3.dat";
	    	try {
	    	    SacADos sac_fichier = VersSacADos.generer_depuis_fichier(chemin);
	    	    System.out.println("\nSac à dos depuis fichier :");
	    	    sac_fichier.afficherContenu(sac_fichier.getObjets());
	    	} catch (IOException e) {
	    	    System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
	    	}

	    }
}

