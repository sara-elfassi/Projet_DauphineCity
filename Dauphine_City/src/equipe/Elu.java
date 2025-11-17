package equipe;

import java.time.LocalDate;

public class Elu extends Personne implements PeutEvaluerBenefice {

    private final GenerateurBenefice generateurBenefice;

    public Elu(String nom,
               String prenom,
               LocalDate dateNaissance,
               GenerateurBenefice generateurBenefice) {
        super(nom, prenom, dateNaissance);
        this.generateurBenefice = generateurBenefice;
    }

    @Override
    public void evaluerBenefice(Projet projet) {
        projet.setBenefice(generateurBenefice.genererBenefice(projet));
    }
}