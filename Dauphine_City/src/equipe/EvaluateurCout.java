package equipe;

import java.time.LocalDate;

/**
 * Représente une personne chargée d'évaluer un type de coût particulier
 * pour les projets de la municipalité.
 * <p>
 * Chaque évaluateur.rice est spécialisé.e sur un unique {@link TypeCout}
 * (économique, social ou environnemental) et utilise un
 * {@link GenerateurCout} pour produire des valeurs de coût plausibles
 * dans le cadre de la simulation.
 * </p>
 */
public class EvaluateurCout extends Personne implements PeutEvaluerCout {

    private final TypeCout specialisation;
    private final GenerateurCout generateurCout;

    /**
     * Constructeur : Recense un nouvel évaluateur avec son identité, 
     * le type de cout dans lequel il se spécialise et 
     * son mode d'evaluation du coût.
     *
     * @param nom            le nom de famille de l'évaluateur.rice (non nul)
     * @param prenom         le prénom de l'évaluateur.rice (non nul)
     * @param dateNaissance  la date de naissance de l'évaluateur.rice (non nulle)
     * @param specialisation le type de coût dans lequel cette personne est spécialisée
     *                       (économique, social ou environnemental) (non nul)
     * @param generateurCout l'objet responsable de générer des valeurs de coût
     *                       en fonction du type de coût et du secteur du projet (non nul)
     */
    public EvaluateurCout(String nom,
                          String prenom,
                          LocalDate dateNaissance,
                          TypeCout specialisation,
                          GenerateurCout generateurCout) {
        super(nom, prenom, dateNaissance);
        this.specialisation = specialisation;
        this.generateurCout = generateurCout;
    }

    /**
     * Renvoie le type de coût dans lequel l'évaluateur est spécialisé.
     *
     * @return la spécialisation de l'évaluateur
     */
    public TypeCout getSpecialisation() {
        return specialisation;
    }
    
    /**
     * Évalue le coût correspondant à la spécialisation de l'évaluateur
     * pour un projet donné.
     * <p>
     * La valeur du coût est calculée par le {@link GenerateurCout} associé,
     * puis enregistrée dans le projet sur le champ correspondant :
     * <ul>
     *     <li>coût économique si la spécialisation est {@link TypeCout#ECONOMIQUE},</li>
     *     <li>coût social si la spécialisation est {@link TypeCout#SOCIAL},</li>
     *     <li>coût environnemental si la spécialisation est {@link TypeCout#ENVIRONNEMENTAL}.</li>
     * </ul>
     * </p>
     *
     * @param projet le projet à évaluer (non nul)
     */
    @Override
    public void evaluerCout(Projet projet) {
        projet.setCoutEconomique(
                specialisation == TypeCout.ECONOMIQUE
                        ? generateurCout.genererCout(specialisation, projet.getSecteur())
                        : projet.getCoutEconomique()
        );
        projet.setCoutSocial(
                specialisation == TypeCout.SOCIAL
                        ? generateurCout.genererCout(specialisation, projet.getSecteur())
                        : projet.getCoutSocial()
        );
        projet.setCoutEnvironnemental(
                specialisation == TypeCout.ENVIRONNEMENTAL
                        ? generateurCout.genererCout(specialisation, projet.getSecteur())
                        : projet.getCoutEnvironnemental()
        );
    }
}