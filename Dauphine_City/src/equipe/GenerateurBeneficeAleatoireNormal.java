package equipe;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

/**
 * Implémentation de {@link GenerateurBenefice} basée sur une loi normale.
 * <p>
 * La moyenne et l'écart-type utilisés pour tirer le bénéfice dépendent
 * du secteur du projet. La valeur retournée est obtenue selon une loi normale (gaussienne), 
 * ajustée pour éviter les coûts négatifs et arrondie à deux décimales.
 * </p>
 */
public class GenerateurBeneficeAleatoireNormal implements GenerateurBenefice {

    private final Random random;

    /**
     * Construit un générateur de bénéfices basé sur un générateur
     * pseudo-aléatoire donné.
     *
     * @param random le générateur pseudo-aléatoire utilisé pour les tirages
     */
    public GenerateurBeneficeAleatoireNormal(Random random) {
        this.random = random;
    }

    /**
     * Génère un bénéfice pour un projet donné.
     * <p>
     * La valeur retournée est obtenue selon une loi normale de moyenne
     * et d'écart-type dépendant du secteur du projet. 
     * Le résultat est tronqué à zéro en cas de
     * valeur négative, puis arrondi à deux décimales.
     * </p>
     *
     * @param projet le projet pour lequel le bénéfice est estimé
     * @return le bénéfice estimé, arrondi à deux décimales
     */
    @Override
    public BigDecimal genererBenefice(Projet projet) {
        double moyenne;
        double ecartType;

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