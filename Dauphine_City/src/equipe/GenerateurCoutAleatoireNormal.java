package equipe;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

/**
 * Implémentation de {@link GenerateurCout} basée sur une loi normale.
 * <p>
 * Pour chaque type de coût, une moyenne et un écart-type sont définis.
 * La valeur retournée est obtenue selon une loi normale (gaussienne)
 * centrée sur cette moyenne, puis ajustée pour
 * éviter les coûts négatifs et arrondie à deux décimales.
 * Le secteur peut également être utilisé pour moduler les valeurs.
 * </p>
 */
public class GenerateurCoutAleatoireNormal implements GenerateurCout {

    private final Random random;

    /**
     * Construit un générateur de coûts basé sur un générateur
     * pseudo-aléatoire donné.
     *
     * @param random le générateur pseudo-aléatoire utilisé pour les tirages
     */
    public GenerateurCoutAleatoireNormal(Random random) {
        this.random = random;
    }

    /**
     * Génère un coût pour un type de coût et un secteur donnés.
     * <p>
     * La valeur retournée est obtenue selon une loi normale de moyenne
     * et d'écart-type dépendant du {@code typeCout}. La moyenne peut
     * ensuite être légèrement ajustée en fonction du {@code secteur}.
     * Le résultat est tronqué à zéro en cas de valeur négative, puis
     * arrondi à deux décimales.
     * </p>
     *
     * @param typeCout le type de coût à générer (économique, social, environnemental)
     * @param secteur  le secteur du projet pour lequel le coût est évalué
     * @return le coût, arrondi à deux décimales
     */
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

        if (secteur == Secteur.SANTE) {
            moyenne *= 1.3;
        } else if (secteur == Secteur.ATTRACTIVITE_ECONOMIQUE) {
            moyenne *= 1.2;
        }

        double tirage = random.nextGaussian();
        double valeurDouble = moyenne + tirage * ecartType;

        if (valeurDouble < 0) {
            valeurDouble = 0;
        }
        return BigDecimal.valueOf(valeurDouble).setScale(2, RoundingMode.HALF_UP);
    }
}
