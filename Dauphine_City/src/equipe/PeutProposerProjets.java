package equipe;

/**
 * Interface représentant la capacité de proposer de nouveaux projets pour la municipalité.
 */
public interface PeutProposerProjets {

    /**
     * Propose un nouveau projet.
     * <p>
     * Le projet renvoyé est généralement non évalué (aucun coût ni bénéfice renseigné) 
     * et sera ensuite complété par d'autres rôles (évaluateurs, élu.e).
     * </p>
     *
     * @return un nouveau projet proposé
     */
    Projet proposerProjet();
}
