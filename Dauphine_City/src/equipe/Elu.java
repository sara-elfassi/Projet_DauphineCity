package equipe;

import java.time.LocalDate;

/**
 * Représente un.e élu.e dans la municipalité.
 * <p>
 * L'élu.e est chargé.e d'évaluer le bénéfice attendu d'un projet.s
 * L'évaluation est effectuée à l'aide d'un {@link GenerateurBenefice} 
 * qui encapsule la logique de génération.
 * </p>
 */
public class Elu extends Personne implements PeutEvaluerBenefice {

    private final GenerateurBenefice generateurBenefice;

    /**
     * Recense un nouvel élu avec son identité et son mode d'évaluation du bénéfice.
     *
     * @param nom                le nom de famille de l'élu.e (non nul)
     * @param prenom             le prénom de l'élu.e (non nul)
     * @param dateNaissance      la date de naissance de l'élu.e (non nulle)
     * @param generateurBenefice l'objet responsable de générer le bénéfice
     *                           associé aux projets évalués (non nul)
     */
    public Elu(String nom,
               String prenom,
               LocalDate dateNaissance,
               GenerateurBenefice generateurBenefice) {
        super(nom, prenom, dateNaissance);
        this.generateurBenefice = generateurBenefice;
    }

    /**
     * Évalue le bénéfice d'un projet pour la collectivité.
     * <p>
     * La méthode délègue le calcul du bénéfice au {@link GenerateurBenefice}
     * associé, puis enregistre ce bénéfice dans le projet passé en paramètre.
     * </p>
     *
     * @param projet le projet dont il faut évaluer le bénéfice (non nul)
     */
    @Override
    public void evaluerBenefice(Projet projet) {
        projet.setBenefice(generateurBenefice.genererBenefice(projet));
    }
}