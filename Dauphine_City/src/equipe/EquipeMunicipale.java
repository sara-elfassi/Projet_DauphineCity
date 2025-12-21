package equipe;

import java.util.*;

/**
 * Regroupe l'ensemble des rôles nécessaires pour proposer et évaluer les projets
 * d'une municipalité et permet d'exécuter des cycles de simulation.
 * <p>
 * Une équipe municipale est composée d'un.e {@link Elu}, de trois
 * {@link EvaluateurCout} (un par type de coût : économique, social,
 * environnemental) et d'une liste d'{@link Expert} capables de proposer
 * de nouveaux projets. L'équipe conserve également la liste des
 * projets déjà étudiés.
 * </p>
 */
public class EquipeMunicipale {

    private final Elu elu;
    private final Map<TypeCout, EvaluateurCout> evaluateurs;
    private final List<Expert> experts;
    private final List<Projet> projetsEtudies;

    private final Random random;

    /**
     * Construit une équipe municipale complète.
     *
     * @param elu       l'élu.e chargé.e d'évaluer le bénéfice des projets (non nul)
     * @param evalEco   l'évaluateur.rice spécialisé.e en coût économique (non nul)
     * @param evalSocial l'évaluateur.rice spécialisé.e en coût social (non nul)
     * @param evalEnv   l'évaluateur.rice spécialisé.e en coût environnemental (non nul)
     * @param experts   la liste des expert.e.s de l'équipe, qui peuvent proposer des projets
     * @param random    générateur pseudo-aléatoire utilisé pour le choix de l'expert qui proposera un projet
     */
    public EquipeMunicipale(Elu elu,
                            EvaluateurCout evalEco,
                            EvaluateurCout evalSocial,
                            EvaluateurCout evalEnv,
                            List<Expert> experts,
                            Random random) {
        this.elu = elu;

        this.evaluateurs = new EnumMap<>(TypeCout.class);
        this.evaluateurs.put(TypeCout.ECONOMIQUE, evalEco);
        this.evaluateurs.put(TypeCout.SOCIAL, evalSocial);
        this.evaluateurs.put(TypeCout.ENVIRONNEMENTAL, evalEnv);

        this.experts = new ArrayList<>(experts);
        this.projetsEtudies = new ArrayList<>();
        this.random = random;
    }

    /**
    * Renvoie la liste des projets déjà étudiés par l'équipe.
    * <p>
    * La liste retournée est une copie de la liste interne afin de
    * préserver l'encapsulation.
    * </p>
    *
    * @return une nouvelle liste contenant les projets étudiés
    */
    public List<Projet> getProjetsEtudies() {
        return new ArrayList<>(projetsEtudies);
    }
    
    /**
     * Exécute un cycle complet de simulation.
     * <p>
     * Le déroulement est le suivant :
     * <ol>
     *     <li>un expert est choisi aléatoirement dans la liste ;</li>
     *     <li>cet expert propose un nouveau projet ;</li>
     *     <li>chacun des trois évaluateurs renseigne son type de coût pour ce projet ;</li>
     *     <li>l'élu.e évalue le bénéfice du projet ;</li>
     *     <li>si le projet est complet, il est ajouté à la liste des projets étudiés.</li>
     * </ol>
     * </p>
     *
     * @return le projet nouvellement créé et entièrement évalué
     * @throws IllegalStateException si l'équipe ne contient aucun expert
     *                               ou si le projet n'est pas complet à l'issue du cycle
     */
    public Projet executerCycleSimulation() {
        if (experts.isEmpty()) {
            throw new IllegalStateException("Aucun expert dans l'équipe.");
        }

        int index = random.nextInt(experts.size());
        Expert expertChoisi = experts.get(index);

        Projet projet = expertChoisi.proposerProjet();

        for (EvaluateurCout evaluateur : evaluateurs.values()) {
            evaluateur.evaluerCout(projet);
        }

        elu.evaluerBenefice(projet);

        if (!projet.estComplet()) {
            throw new IllegalStateException("Projet non complet après le cycle de simulation.");
        }
        projetsEtudies.add(projet);

        return projet;
    }

    /**
     * Exécute plusieurs cycles de simulation successifs.
     * <p>
     * Cette méthode appelle {@link #executerCycleSimulation()} autant
     * de fois que nécessaire et enrichit ainsi progressivement la liste
     * des projets étudiés.
     * </p>
     *
     * @param n le nombre de cycles de simulation à exécuter (doit être positif ou nul)
     */
    public void executerNCycles(int n) {
        for (int i = 0; i < n; i++) {
            executerCycleSimulation();
        }
    }
}