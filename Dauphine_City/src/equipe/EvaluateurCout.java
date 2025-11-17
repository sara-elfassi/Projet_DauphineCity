package equipe;

import java.time.LocalDate;

public class EvaluateurCout extends Personne implements PeutEvaluerCout {

    private final TypeCout specialisation;
    private final GenerateurCout generateurCout;

    public EvaluateurCout(String nom,
                          String prenom,
                          LocalDate dateNaissance,
                          TypeCout specialisation,
                          GenerateurCout generateurCout) {
        super(nom, prenom, dateNaissance);
        this.specialisation = specialisation;
        this.generateurCout = generateurCout;
    }

    public TypeCout getSpecialisation() {
        return specialisation;
    }

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