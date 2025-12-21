package equipe;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Permet de mettre en oeuvre une simulation d'équipe municipale.
 * <p>
 * Cette classe construit les différents acteurs (élu.e, évaluateurs,
 * expert.e.s), les générateurs de coûts et de bénéfices, puis instancie
 * une {@link EquipeMunicipale}. Elle exécute ensuite quelques cycles de
 * simulation et affiche les projets étudiés.
 * </p>
 */
public class Main_Equipe {

    /**
     * Méthode principale lançant la simulation.
     * <p>
     * Le paramètre {@code args} est requis par la signature standard
     * de la méthode {@code main} en Java, mais n'est pas utilisé dans
     * cette application.
     * </p>
     *
     * @param args arguments de la ligne de commande (ignorés)
     */
    public static void main(String[] args) {

        Random randomGlobal = new Random(100);

        GenerateurCout generateurCout =
                new GenerateurCoutAleatoireNormal(randomGlobal);
        GenerateurBenefice generateurBenefice =
                new GenerateurBeneficeAleatoireNormal(randomGlobal);

        Elu elu = new Elu("Goldman", "Jean Jaques",
                LocalDate.of(1980, 5, 10),
                generateurBenefice);

        EvaluateurCout evalEco = new EvaluateurCout("Aznavour", "Charles",
                LocalDate.of(1985, 3, 20),
                TypeCout.ECONOMIQUE,
                generateurCout);

        EvaluateurCout evalSocial = new EvaluateurCout("Paradis", "Vanessa",
                LocalDate.of(1982, 7, 15),
                TypeCout.SOCIAL,
                generateurCout);

        EvaluateurCout evalEnv = new EvaluateurCout("Brassens", "Georges",
                LocalDate.of(1990, 1, 5),
                TypeCout.ENVIRONNEMENTAL,
                generateurCout);

        Expert expert1 = new Expert("Brel", "Jacques",
                LocalDate.of(1970, 9, 30),
                Set.of(Secteur.SPORT, Secteur.SANTE),
                randomGlobal);

        Expert expert2 = new Expert("Bruni", "Carla",
                LocalDate.of(1968, 11, 12),
                Set.of(Secteur.CULTURE, Secteur.EDUCATION, Secteur.ATTRACTIVITE_ECONOMIQUE),
                randomGlobal);

        EquipeMunicipale equipe = new EquipeMunicipale(
                elu,
                evalEco,
                evalSocial,
                evalEnv,
                List.of(expert1, expert2),
                randomGlobal
        );

        equipe.executerNCycles(5);

        for (Projet p : equipe.getProjetsEtudies()) {
            System.out.println(p);
        }
    }
}
