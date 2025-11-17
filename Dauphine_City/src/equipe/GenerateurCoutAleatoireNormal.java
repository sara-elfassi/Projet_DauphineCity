package equipe;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class GenerateurCoutAleatoireNormal implements GenerateurCout {

    private final Random random;

    public GenerateurCoutAleatoireNormal(Random random) {
        this.random = random;
    }

    @Override
    public BigDecimal genererCout(TypeCout typeCout, Secteur secteur) {
        double moyenne;
        double ecartType;

        switch (typeCout) {
            case ECONOMIQUE -> {
                moyenne = 500_000.0;
                ecartType = 150_000.0;
            }
            case SOCIAL -> {
                moyenne = 200_000.0;
                ecartType = 80_000.0;
            }
            case ENVIRONNEMENTAL -> {
                moyenne = 150_000.0;
                ecartType = 60_000.0;
            }
            default -> throw new IllegalArgumentException("Type de coût inconnu : " + typeCout);
        }

        // Si on souhaite moduler la moyenne selon le secteur:
        if (secteur == Secteur.SANTE) {
            moyenne *= 1.3;
        } else if (secteur == Secteur.ATTRACTIVITE_ECONOMIQUE) {
            moyenne *= 1.2;
        }

        double tirage = random.nextGaussian(); // loi normale (0,1)
        double valeurDouble = moyenne + tirage * ecartType;

        if (valeurDouble < 0) {
            valeurDouble = 0;
        }
        return BigDecimal.valueOf(valeurDouble).setScale(2, RoundingMode.HALF_UP);
    }
}
