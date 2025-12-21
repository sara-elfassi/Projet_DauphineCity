package equipe;

/**
 * Interface représentant la capacité d'évaluer un coût pour un projet.
 * Une implémentation typique pour une équipe municipale ne renseigne
 * qu'un type de coût (économique, social ou environnemental)
 * et met à jour directement le projet passé en paramètre.
 * </p>
 */
public interface PeutEvaluerCout {

    /**
     * Évalue un aspect du coût d'un projet.
     * <p>
     * L'implémentation met généralement à jour l'un des champs
     * de coût du projet (économique, social ou environnemental),
     * en fonction de sa spécialisation.
     * </p>
     *
     * @param projet le projet dont il faut évaluer le coût (non nul)
     */
    void evaluerCout(Projet projet);
}
