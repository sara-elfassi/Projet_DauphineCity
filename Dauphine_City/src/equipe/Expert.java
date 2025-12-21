package equipe;

import java.time.LocalDate;
import java.util.*;

/**
 * Représente un.e expert.e rattaché.e à la municipalité.
 * <p>
 * Un.e expert.e possède une liste de secteurs de compétence et
 * est capable de proposer de nouveaux projets dans ces secteurs.
 * </p>
 */
public class Expert extends Personne implements PeutProposerProjets {

    private final Set<Secteur> competences;
    private final Random random;

    /**
     * Constructeur : rencense un.e nouvel.le expert.e avec ses secteurs de compétence.
     *
     * @param nom          le nom de famille de l'expert.e (non nul)
     * @param prenom       le prénom de l'expert.e (non nul)
     * @param dateNaissance la date de naissance de l'expert.e (non nulle)
     * @param competences l'ensemble des secteurs dans lesquels cette personne
     *                    possède des compétences et peut proposer des projets
     *                    (non vide)
     * @param random       générateur pseudo-aléatoire utilisé pour choisir un
     *                    secteur et pour générer des éléments aléatoires
     *                    (comme un identifiant de projet)
     */
    public Expert(String nom,
                  String prenom,
                  LocalDate dateNaissance,
                  Set<Secteur> competences,
                  Random random) {
        super(nom, prenom, dateNaissance);
        this.competences = new HashSet<>(competences);
        this.random = random;
    }

    /**
     * Renvoie l'ensemble des secteurs de compétence de cet expert.
     * <p>
     * L'ensemble retourné est non modifiable : toute tentative de modification
     * provoquera une {@link UnsupportedOperationException}. 
     * Pour étendre ou modifier les compétences, il faut une nouvelle
     * instance d'{@code Expert}.
     * </p>
     *
     * @return l'ensemble des secteurs de compétence de l'expert 
     * sous forme d'ensemble non modifiable
     */
    public Set<Secteur> getCompetences() {
        return Collections.unmodifiableSet(competences);
    }


    /**
     * Propose un nouveau projet dans l'un des secteurs de compétence de l'expert.
     * <p>
     * Le secteur est choisi aléatoirement parmi l'ensemble des compétences.
     * Un titre et une description sont générés à partir du secteur
     * et du nom de l'expert. 
     * Le projet retourné n'a encore subi aucune évaluation de coûts ou de bénéfice.
     * </p>
     *
     * @return un nouveau projet proposé l'expert
     * @throws IllegalStateException si l'expert ne possède aucune compétence
     */
    @Override
    public Projet proposerProjet() {
        if (competences.isEmpty()) {
            throw new IllegalStateException("L'expert n'a aucun secteur de compétence.");
        }

        int index = random.nextInt(competences.size());
        Secteur secteur = competences.stream().skip(index).findFirst().orElseThrow();

        String titre = "Projet " + secteur + " #" + random.nextInt(1000);
        String description = "Projet proposé dans le secteur " + secteur +
                             " par " + getNomComplet();

        return new Projet(titre, description, secteur);
    }
}