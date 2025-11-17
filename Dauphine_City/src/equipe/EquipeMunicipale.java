package equipe;

import java.util.*;

public class EquipeMunicipale {

    private final Elu elu;
    private final Map<TypeCout, EvaluateurCout> evaluateurs;
    private final List<Expert> experts;
    private final List<Projet> projetsEtudies;

    private final Random random;

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

    public List<Projet> getProjetsEtudies() {
        return new ArrayList<>(projetsEtudies);
    }

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

    public void executerNCycles(int n) {
        for (int i = 0; i < n; i++) {
            executerCycleSimulation();
        }
    }
}