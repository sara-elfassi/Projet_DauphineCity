package equipe;

/**
 * Interface représentant la capacité d'évaluer le bénéfice d'un projet pour la collectivité.
 */
public interface PeutEvaluerBenefice {

    /**
     * Évalue le bénéfice d'un projet.
     * <p>
     * L'implémentation typique met à jour directement
     * le bénéfice stocké dans le projet passé en paramètre.
     * </p>
     *
     * @param projet le projet dont il faut évaluer le bénéfice (non nul)
     */
    void evaluerBenefice(Projet projet);
}
