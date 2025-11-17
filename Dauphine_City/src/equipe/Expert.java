package equipe;

import java.time.LocalDate;
import java.util.*;

public class Expert extends Personne implements PeutProposerProjets {

    private final Set<Secteur> competences;
    private final Random random;

    public Expert(String nom,
                  String prenom,
                  LocalDate dateNaissance,
                  Set<Secteur> competences,
                  Random random) {
        super(nom, prenom, dateNaissance);
        this.competences = new HashSet<>(competences);
        this.random = random;
    }

    public Set<Secteur> getCompetences() {
        return Collections.unmodifiableSet(competences);
    }

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