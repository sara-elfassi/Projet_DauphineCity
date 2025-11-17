package equipe;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class GenerateurBeneficeAleatoireNormal implements GenerateurBenefice {

    private final Random random;

    public GenerateurBeneficeAleatoireNormal(Random random) {
        this.random = random;
    }

    @Override
    public BigDecimal genererBenefice(Projet projet) {
        double moyenne;
        double ecartType;

        // Bénéfice moyen en fonction du secteur
        switch (projet.getSecteur()) {
            case SANTE -> {
                moyenne = 800_000.0;
                ecartType = 200_000.0;
            }
            case EDUCATION -> {
                moyenne = 600_000.0;
                ecartType = 150_000.0;
            }
            case SPORT -> {
                moyenne = 300_000.0;
                ecartType = 100_000.0;
            }
            case CULTURE -> {
                moyenne = 250_000.0;
                ecartType = 80_000.0;
            }
            case ATTRACTIVITE_ECONOMIQUE -> {
                moyenne = 700_000.0;
                ecartType = 180_000.0;
            }
            default -> throw new IllegalArgumentException("Secteur inconnu");
        }

        double tirage = random.nextGaussian();
        double valeurDouble = moyenne + tirage * ecartType;
        if (valeurDouble < 0) {
            valeurDouble = 0;
        }

        return BigDecimal.valueOf(valeurDouble).setScale(2, RoundingMode.HALF_UP);
    }
}